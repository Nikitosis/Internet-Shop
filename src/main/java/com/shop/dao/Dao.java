package com.shop.dao;

import com.shop.entities.Commodity;
import com.shop.entities.OrderLog;
import com.shop.entities.User;

import java.util.List;

public interface Dao {
    public List<Commodity> getCommodities();
    public User getUser(int id);
    public List<OrderLog> getUserOrders(int userId);
    public List<Commodity> getUserCommodities(int userId);

    public boolean isUserExist(User user);
}
