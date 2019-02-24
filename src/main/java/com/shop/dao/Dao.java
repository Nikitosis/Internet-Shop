package com.shop.dao;

import com.shop.entities.Commodity;
//import com.shop.entities.OrderLog;
import com.shop.entities.OrderLog;
import com.shop.entities.User;

import java.util.List;

public interface Dao {
    public List<Commodity> getCommodities();
    public User findByUsername(String username);
    public List<OrderLog> getUserOrders(String username);
    public List<Commodity> getUserCommodities(String username);

    public boolean isUserExist(User user);
}
