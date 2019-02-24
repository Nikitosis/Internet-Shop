package com.shop.service;

import com.shop.entities.Commodity;
//import com.shop.entities.OrderLog;
import com.shop.entities.OrderLog;
import com.shop.entities.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MainService {
    public List<Commodity> getCommodities();
    public User getUser(String username);
    public List<OrderLog> getUserOrders(String username);
    public List<Commodity> getUserCommodities(String username);

    public void addCommodityToBasket(int commodityId, HttpSession session);
    public List<Commodity> getCommoditiesFromBasket(HttpSession session);
    public void confirmBasketBuy(HttpSession session);
}
