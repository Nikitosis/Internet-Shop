package com.shop.service;

import com.shop.dao.Dao;
import com.shop.entities.Commodity;
import com.shop.entities.OrderLog;
import com.shop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    Dao dao;

    public List<Commodity> getCommodities() {
        return dao.getCommodities();
    }

    public User getUser(int id) {
        return dao.getUser(id);
    }

    public List<OrderLog> getUserOrders(int userId) {
        return dao.getUserOrders(userId);
    }

    public List<Commodity> getUserCommodities(int userId) {
        return dao.getUserCommodities(userId);
    }

    public void loginUser(User user) {
        if(dao.isUserExist(user)){
            System.out.println("User logged!");
        }
        else{
            System.out.println("User not logged!");
        }
    }
}
