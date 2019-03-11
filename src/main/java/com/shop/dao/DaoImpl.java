package com.shop.dao;

import com.shop.entities.Comment;
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

import java.sql.Date;
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

    public List<Commodity> getCommoditiesWithFilter(Double startPrice, Double endPrice, String searchName) {
        Session session=sessionFactory.openSession();
        List<Commodity> commodities=null;
        try{
            commodities=session.createQuery("FROM Commodity WHERE price>=:startPrice AND price<=:endPrice AND name LIKE :searchName")
                        .setParameter("startPrice",startPrice)
                        .setParameter("endPrice",endPrice)
                        .setParameter("searchName","%"+searchName+"%")
                        .list();
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

    public User getUserByUsername(String username) {
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

    public Commodity getCommodityById(int id) {
        Session session=sessionFactory.openSession();
        Commodity commodity=null;
        try{
            commodity=(Commodity)session.createQuery("FROM Commodity WHERE id=:id")
                    .setParameter("id",id)
                    .getSingleResult();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return commodity;
    }

    public void buyCommodity(Commodity commodity,User user) {
        Session session=sessionFactory.openSession();
        OrderLog orderLog=null;
        try{
            orderLog=new OrderLog(
                    commodity,
                    user,
                    new Date(new java.util.Date().getTime())
            );

            session.save(orderLog);

            System.out.println("Buy confirmed!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addNewUser(User user) {
        Session session=sessionFactory.openSession();
        try{
            session.save(user);
            System.out.println("User "+user.getUsername()+" registered!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addCommodity(Commodity commodity) {
        Session session=sessionFactory.openSession();
        try{
            session.save(commodity);
            System.out.println("Commodity added: "+commodity.getName());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void modifyCommodity(Commodity commodity) {
        Session session=sessionFactory.openSession();
        try{
            Transaction tx=session.beginTransaction();
            session.merge(commodity);
            System.out.println("Commodity modified: "+commodity.getName());
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteCommodityById(int id) {
        Session session=sessionFactory.openSession();
        try{
            Transaction tx=session.beginTransaction();

            Commodity commodity=session.get(Commodity.class,id);
            session.delete(commodity);
            System.out.println("Delete commodity: "+commodity.getName());

            tx.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addComment(Comment comment) {
        Session session=sessionFactory.openSession();
        try{
            session.save(comment);
            System.out.println("Comment added "+comment.getContent());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
