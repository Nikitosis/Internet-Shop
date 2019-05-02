package com.shop.service;

import com.shop.dao.CommodityFilter;
import com.shop.entities.*;
//import com.shop.entities.OrderLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MainService {
    public List<Commodity> getCommodities(CommodityFilter commodityFilter);
    public Map<String,List<Category>> getGroupedCategories(CommodityFilter commodityFilter);
    public List<Category> getUniqueCategories(CommodityFilter commodityFilter);
    public User getUser(String username);
    public List<OrderLog> getUserOrders(String username);
    public List<Commodity> getUserCommodities(String username);
    public Commodity getCommodityById(int id);

    public void addCommodityToBasket(int commodityId, HttpSession session);
    public void removeCommodityFromBasket(int commodityId,HttpSession session);
    public List<Commodity> getCommoditiesFromBasket(HttpSession session);
    public void confirmBasketBuy(HttpSession session);

    public void registerUser(User user);

    public void addCommodityToDb(Commodity commodity);
    public void modifyCommodity(Commodity commodity);
    public void deleteCommodityById(int id);

    public void addComment(Comment comment);
    public void addComment(String content,Commodity commodity,UserDetails userDetails);
}
