package com.shop.service;

import com.shop.dao.Dao;
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
import java.util.ArrayList;
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

    public User getUser(String username) {
        return dao.getUserByUsername(username);
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

    public List<Commodity> getCommoditiesFromBasket(HttpSession session) {
        if(session.getAttribute("commoditiesInBasket")!=null)
            return (List<Commodity>)session.getAttribute("commoditiesInBasket");

        return new ArrayList<Commodity>();
    }

    public void confirmBasketBuy(HttpSession session) {
        List<Commodity> commoditiesInBasket=(List<Commodity>) session.getAttribute("commoditiesInBasket");
        UserDetails principal=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User curUser=dao.getUserByUsername(principal.getUsername());

        for(Commodity commodity:commoditiesInBasket){
            dao.buyCommodity(commodity,curUser);
        }

        session.removeAttribute("commoditiesInBasket");
    }

    public void registerUser(User user) {
        User encodedUser=new User(user.getUsername(),encoder.encode(user.getPassword()),user.getUserRoles());
        dao.addNewUser(encodedUser);
    }
}
