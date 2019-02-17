package com.shop.dao;

import com.shop.entities.Commodity;
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

    public User getUser(int id) {
        Session session=sessionFactory.openSession();
        User user=null;
        try{
            Query query=session.createQuery("FROM User WHERE id=:id");
            query.setParameter("id",id);

            user=(User)query.getSingleResult();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public List<OrderLog> getUserOrders(int userId) {
        Session session=sessionFactory.openSession();
        List<OrderLog> orders=null;
        try{
            Query query=session.createQuery("FROM OrderLog INNER JOIN OrderLog.user");

            orders=query.list();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return orders;
    }

    public List<Commodity> getUserCommodities(int userId) {
        Session session=sessionFactory.openSession();
        List<Commodity> commodities=null;
        try{
            Query query=session.createQuery("SELECT OrderLog.commodity FROM OrderLog INNER JOIN OrderLog.user");

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
            String name=user.getUsername();
            String pass=user.getPassword();
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
}
