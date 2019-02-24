package com.shop.dao;

import com.shop.entities.Commodity;
//import com.shop.entities.OrderLog;
import com.shop.entities.OrderLog;
import com.shop.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoImpl implements Dao {
    @Autowired
    SessionFactory sessionFactory;

    public List<Commodity> getCommodities() {
        Session session=sessionFactory.openSession();
        List<Commodity> commodities=null;
        try{
            Query query=session.createQuery("FROM Commodity");
            commodities=query.list();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return commodities;
    }

    public User getUser(String username) {
        Session session=sessionFactory.openSession();
        User user=null;
        try{
            user=(User)session.createQuery("FROM User WHERE username=:username")
                    .setParameter("username",username)
                    .getSingleResult();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public List<OrderLog> getUserOrders(String username) {
        Session session=sessionFactory.openSession();
        List<OrderLog> orders=null;
        try{
            Query query=session.createQuery("FROM OrderLog log WHERE log.user.username=:username")
                    .setParameter("username",username);

            orders=query.list();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return orders;
    }

    public List<Commodity> getUserCommodities(String username) {
        Session session=sessionFactory.openSession();
        List<Commodity> commodities=null;
        try{
            Query query=session.createQuery("SELECT log.commodity FROM OrderLog log WHERE log.user.username=:username")
                    .setParameter("username",username);

            commodities=query.list();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return commodities;
    }

    public boolean isUserExist(User user) {
        Session session=sessionFactory.openSession();
        Object findUser=null;
        try{
            findUser=session.createQuery("FROM User WHERE username=:username AND password=:password")
                    .setParameter("username",user.getUsername())
                    .setParameter("password",user.getPassword())
                    .getSingleResult();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return findUser!=null;
    }

    public User findByUsername(String username) {
        Session session=sessionFactory.openSession();
        User user=null;
        try{
            user=(User)session.createQuery("FROM User WHERE username=:username")
                    .setParameter("username",username)
                    .getSingleResult();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
