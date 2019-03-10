package com.shop.service;

import com.shop.entities.Commodity;
//import com.shop.entities.OrderLog;
import com.shop.entities.OrderLog;
import com.shop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MainService {
    public List<Commodity> getCommodities();
    public List<Commodity> getCommoditiesWithFilter(Double startPrice,Double endPrice,String searchName);
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
}
