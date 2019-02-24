package com.shop.service;

import com.shop.dao.Dao;
import com.shop.entities.Commodity;
//import com.shop.entities.OrderLog;
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

    public User getUser(String username) {
        return dao.findByUsername(username);
    }

    public List<OrderLog> getUserOrders(String username) {
        return dao.getUserOrders(username);
    }

    public List<Commodity> getUserCommodities(String username) {
        return dao.getUserCommodities(username);
    }
}
