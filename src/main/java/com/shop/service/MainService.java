package com.shop.service;

import com.shop.entities.Commodity;
//import com.shop.entities.OrderLog;
import com.shop.entities.OrderLog;
import com.shop.entities.User;

import java.util.List;

public interface MainService {
    public List<Commodity> getCommodities();
    public User getUser(String username);
    public List<OrderLog> getUserOrders(String username);
    public List<Commodity> getUserCommodities(String username);
}
