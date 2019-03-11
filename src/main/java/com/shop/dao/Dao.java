package com.shop.dao;

import com.shop.entities.Comment;
import com.shop.entities.Commodity;
//import com.shop.entities.OrderLog;
import com.shop.entities.OrderLog;
import com.shop.entities.User;

import java.util.List;

public interface Dao {
    public List<Commodity> getCommodities();
    public List<Commodity> getCommoditiesWithFilter(Double startPrice, Double endPrice, String searchName);
    public User getUserByUsername(String username);
    public List<OrderLog> getUserOrders(String username);
    public List<Commodity> getUserCommodities(String username);
    public Commodity getCommodityById(int id);

    public void buyCommodity(Commodity commodity,User user);

    public boolean isUserExist(User user);

    public void addNewUser(User user);

    public void addCommodity(Commodity commodity);
    public void modifyCommodity(Commodity commodity);
    public void deleteCommodityById(int id);

    public void addComment(Comment comment);
}
