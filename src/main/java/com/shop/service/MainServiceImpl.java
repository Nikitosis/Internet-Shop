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
import sun.applet.Main;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.*;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    Dao dao;

    @Autowired
    PasswordEncoder encoder;

    public List<Commodity> getCommodities(CommodityFilter commodityFilter) {
        return dao.getCommodities(commodityFilter);
    }

    @Override
    public Map<String,List<Category>> getGroupedCategories(CommodityFilter commodityFilter) {
        List<Category> uniqueCategories=dao.getUniqueCategories(commodityFilter);
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
    public List<Category> getCategories(CommodityFilter commodityFilter) {
        return dao.getUniqueCategories(commodityFilter);
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

    public void addCommodityToDb(Commodity commodity) {
        dao.addCommodity(commodity);
    }


    public void modifyCommodity(Commodity commodity){
        dao.modifyCommodity(commodity);
    }

    public void deleteCommodityById(int id) {
        dao.deleteCommodityById(id);
    }

    public void addComment(Comment comment) {
        dao.addComment(comment);
    }

    public void addComment(String content, Commodity commodity,UserDetails userDetails) {
        User user=getUser(userDetails.getUsername());
        Comment comment=new Comment(content,
                new Date(Calendar.getInstance().getTime().getTime()),
                user,
                commodity);

        dao.addComment(comment);

        commodity.addComment(comment);
        user.addComment(comment);
    }
}
