package com.shop.service;

import com.shop.dao.CommodityFilter;
import com.shop.dao.Dao;
import com.shop.entities.*;
//import com.shop.entities.OrderLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.applet.Main;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    Dao dao;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public List<Commodity> getCommodities(CommodityFilter commodityFilter) {
        return dao.getCommodities(commodityFilter);
    }

    @Override
    public Category getCategory(String name, String value) {
        return dao.getCategory(name,value);
    }

    @Override
    public Map<String,List<Category>> getGroupedCategories(CommodityFilter commodityFilter) {

        List<Category> uniqueCategories=getUniqueCategories(commodityFilter);

        Map<String,List<Category>> mapNameToCategory=new HashMap<String,List<Category>>();

        for(Category category:uniqueCategories){
            if(!mapNameToCategory.containsKey(category.getName())) {
                mapNameToCategory.put(category.getName(), new ArrayList<Category>());
            }
            mapNameToCategory.get(category.getName()).add(category);
        }

        return mapNameToCategory;
    }

    @Override
    public List<Category> getUniqueCategories(CommodityFilter commodityFilter) {
        List<Commodity> commodities=dao.getCommodities(commodityFilter);
        Set<Category> categorySet=new TreeSet<Category>();
        for(Commodity commodity:commodities){
            for(Category category:commodity.getCategories()){
                categorySet.add(category);
            }
        }
        return new ArrayList<Category>(categorySet);
    }

    public User getUser(String username) {
        return dao.getUser(username);
    }

    public List<OrderLog> getUserOrders(String username) {
        return dao.getUserOrders(username);
    }

    public List<Commodity> getUserCommodities(String username) {
        return dao.getUserCommodities(username);
    }

    public void addCommodityToBasket(int commodityId, HttpSession session) {
        List<Commodity> commoditiesInBasket=(List<Commodity>)session.getAttribute("commoditiesInBasket");
        if(commoditiesInBasket==null){
            commoditiesInBasket=new ArrayList<Commodity>();
        }

        Commodity commodity=getCommodityById(commodityId);
        commoditiesInBasket.add(commodity);

        session.setAttribute("commoditiesInBasket",commoditiesInBasket);
    }

    public void removeCommodityFromBasket(int commodityId,HttpSession session) {
        List<Commodity> commoditiesInBasket=(List<Commodity>) session.getAttribute("commoditiesInBasket");

        for(Commodity commodity:commoditiesInBasket){
            if(commodity.getId()==commodityId) {
                commoditiesInBasket.remove(commodity);
                break;
            }
        }

        session.setAttribute("commoditiesInBasket",commoditiesInBasket);
    }

    public List<Commodity> getCommoditiesFromBasket(HttpSession session) {
        if(session.getAttribute("commoditiesInBasket")!=null)
            return (List<Commodity>)session.getAttribute("commoditiesInBasket");

        return new ArrayList<Commodity>();
    }

    public Commodity getCommodityById(int id) {
        try{
            return getCommodities(new CommodityFilter().setId(id)).get(0);
        }
        catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return null;
    }

    public void confirmBasketBuy(HttpSession session) {
        List<Commodity> commoditiesInBasket=(List<Commodity>) session.getAttribute("commoditiesInBasket");
        UserDetails principal=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User curUser=dao.getUser(principal.getUsername());

        for(Commodity commodity:commoditiesInBasket){
            dao.buyCommodity(commodity,curUser);
        }

        session.removeAttribute("commoditiesInBasket");
    }

    public void registerUser(User user) {
        User encodedUser=new User(user.getUsername(),encoder.encode(user.getPassword()),user.getUserRoles(),user.getComments());
        dao.addNewUser(encodedUser);
    }

    public Commodity createCommodity(MultipartFile mainImage,
                                 List<MultipartFile> images,
                                 String[] tagNames,
                                 String[] tagValues,
                                 String name,
                                 String description,
                                 Double price,
                                     Integer id) throws IOException {
        Commodity commodity=new Commodity();

        setupCommodityFields(mainImage,images,tagNames,tagValues,name,description,price,id,new java.util.Date(),commodity);

        return commodity;
    }

    @Override
    public Commodity setupCommodityFields(MultipartFile mainImage, List<MultipartFile> images, String[] tagNames, String[] tagValues, String name, String description, Double price, Integer id, java.util.Date creationDate, Commodity commodity) throws IOException {
        if(id!=null){
            commodity.setId(id);
        }

        if(name!=null) {
            commodity.setName(name);
        }

        if(price!=null) {
            commodity.setPrice(price);
        }

        if(description!=null) {
            commodity.setDescription(description);
        }

        if(mainImage!=null && mainImage.getSize()>0) {
            commodity.setMainImage(new Image(mainImage.getBytes()));
        }

        if(images!=null) {
            List<Image> newImages=new ArrayList<Image>();
            for (MultipartFile image : images) {
                if(image.getSize()==0)
                    continue;
                byte[] b = image.getBytes();
                Image imageObj = new Image(image.getBytes());
                newImages.add(imageObj);
            }
            if(!newImages.isEmpty())
                commodity.setImages(newImages);
        }

        if(tagNames!=null) {
            Set<Category> newCategories=new HashSet<Category>();
            for (int i = 0; i < tagNames.length; i++) {

                if("".equals(tagNames[i]) || "".equals(tagValues[i]))
                    continue;
                Category curCategory = getCategory(tagNames[i], tagValues[i]);
                if (curCategory == null) {
                    curCategory = new Category(tagNames[i], tagValues[i]);
                }
                newCategories.add(curCategory);
            }
            commodity.setCategories(newCategories);
        }

        if(creationDate!=null) {
            commodity.setCreationDate(new java.sql.Date(creationDate.getTime()));
        }
        return commodity;
    }

    public void addCommodityToDb(Commodity commodity) {
        dao.addCommodity(commodity);
    }


    public void modifyCommodity(Commodity commodity){
        dao.modifyCommodity(commodity);
    }

    public void deleteCommodityById(int id) {
        dao.deleteCommodityById(id);
    }

    /*public void addComment(Comment comment) {
        dao.addComment(comment);
    }*/

    public void addComment(String content, Commodity commodity,UserDetails userDetails) {
        User user=getUser(userDetails.getUsername());
        Comment comment=new Comment(content,
                new Date(Calendar.getInstance().getTime().getTime()),
                user,
                commodity);

        dao.addComment(comment);


//        commodity.addComment(comment);
//        user.addComment(comment);
//
//        dao.addCommodity(commodity);
//        dao.addNewUser(user);

        //commodity.addComment(comment);
        //user.addComment(comment);

        //modifyCommodity(commodity);
    }
}
