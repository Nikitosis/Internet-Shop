package com.shop.dao;

import com.shop.entities.*;
//import com.shop.entities.OrderLog;

import java.util.List;
import java.util.Set;

public interface Dao {
    public List<Commodity> getCommodities();
    public List<Commodity> getCommoditiesWithFilter(Double startPrice, Double endPrice, String searchName);
    public User getUser(String username);
    public List<OrderLog> getUserOrders(String username);
    public List<Commodity> getUserCommodities(String username);
    public Commodity getCommodityById(int id);

    public List<Commodity> getCommoditiesByTags(List<Tag> tags);

    public void buyCommodity(Commodity commodity,User user);

    public boolean isUserExist(User user);

    public void addNewUser(User user);

    public void addCommodity(Commodity commodity);
    public void modifyCommodity(Commodity commodity);
    public void deleteCommodityById(int id);

    public void addComment(Comment comment);
}
