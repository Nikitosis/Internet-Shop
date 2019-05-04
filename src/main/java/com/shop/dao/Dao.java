package com.shop.dao;

import com.shop.entities.*;
//import com.shop.entities.OrderLog;

import java.util.List;
import java.util.Set;

public interface Dao {
    public List<Commodity> getCommodities(CommodityFilter commodityFilter);
    public Category getCategory(String name,String value);
    //public List<Category> getUniqueCategories(CommodityFilter commodityFilter);
    public User getUser(String username);
    public List<OrderLog> getUserOrders(String username);
    public List<Commodity> getUserCommodities(String username);

    public void buyCommodity(Commodity commodity,User user);

    public boolean isUserExist(User user);

    public void addNewUser(User user);

    public void addCommodity(Commodity commodity);
    public void modifyCommodity(Commodity commodity);
    public void deleteCommodityById(int id);

    public void addComment(Comment comment);

}
