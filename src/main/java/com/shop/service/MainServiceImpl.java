package com.shop.service;

import com.shop.dao.Dao;
import com.shop.entities.Comment;
import com.shop.entities.Commodity;
//import com.shop.entities.OrderLog;
import com.shop.entities.OrderLog;
import com.shop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    Dao dao;

    @Autowired
    PasswordEncoder encoder;

    public List<Commodity> getCommodities() {
        return dao.getCommodities();
    }

    public List<Commodity> getCommoditiesWithFilter(Double startPrice, Double endPrice, String searchName) {
        if(startPrice==null)
            startPrice=0.0;
        if(endPrice==null)
            endPrice=100000000.0;

        return dao.getCommoditiesWithFilter(startPrice,endPrice,searchName);
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

        Commodity commodity=dao.getCommodityById(commodityId);
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
        return dao.getCommodityById(id);
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
