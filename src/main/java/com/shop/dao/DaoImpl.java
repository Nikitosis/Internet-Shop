package com.shop.dao;

import com.shop.entities.*;
//import com.shop.entities.OrderLog;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class DaoImpl implements Dao {
    @Autowired
    SessionFactory sessionFactory;

    public List<Commodity> getCommodities(CommodityFilter commodityFilter) {
        Session session=sessionFactory.openSession();
        List<Commodity> commodities=null;
        try{
            Transaction tx=session.beginTransaction();
            Criteria criteria=session.createCriteria(Commodity.class,"commodity");
            criteria.createAlias("commodity.categories","category");
            criteria=commodityFilter.addFilterToCriteria(criteria,"commodity","category");
            commodities=criteria.list();
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return commodities;
    }

    @Override
    public List<Category> getUniqueCategories(CommodityFilter commodityFilter) {
        Session session=sessionFactory.openSession();
        List<Category> categories=null;
        try{
            Transaction tx=session.beginTransaction();
            Criteria criteria=session.createCriteria(Category.class,"category");
            criteria.createAlias("category.commodities","commodity");

            criteria=commodityFilter.addFilterToCriteria(criteria,"commodity","category");

            criteria.setProjection(Projections.projectionList()
                    //.add(Projections.distinct(Projections.property("category.name")))
                    .add(Projections.groupProperty("category.name"))
                    .add(Projections.groupProperty("category.value")));

            categories=criteria.list();
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return categories;
    }

    public User getUser(String username) {
        Session session=sessionFactory.openSession();
        User user=null;
        try{
            Transaction tx=session.beginTransaction();
            user=(User)session.createQuery("FROM User WHERE username=:username")
                    .setParameter("username",username)
                    .getSingleResult();

            //Hibernate.initialize(user.getUserRoles());
            //Hibernate.initialize(user.getComments());
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user;
    }

    public List<OrderLog> getUserOrders(String username) {
        Session session=sessionFactory.openSession();
        List<OrderLog> orders=null;
        try{
            Transaction tx=session.beginTransaction();
            Query query=session.createQuery("FROM OrderLog log WHERE log.user.username=:username")
                    .setParameter("username",username);

            orders=query.list();
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return orders;
    }

    public List<Commodity> getUserCommodities(String username) {
        Session session=sessionFactory.openSession();
        List<Commodity> commodities=null;
        try{
            Transaction tx=session.beginTransaction();
            Query query=session.createQuery("SELECT log.commodity FROM OrderLog log WHERE log.user.username=:username")
                    .setParameter("username",username);

            commodities=query.list();
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return commodities;
    }

    public boolean isUserExist(User user) {
        Session session=sessionFactory.openSession();
        Object findUser=null;
        try{
            Transaction tx=session.beginTransaction();
            findUser=session.createQuery("FROM User WHERE username=:username AND password=:password")
                    .setParameter("username",user.getUsername())
                    .setParameter("password",user.getPassword())
                    .getSingleResult();
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return findUser!=null;
    }

    public void buyCommodity(Commodity commodity, User user) {
        Session session=sessionFactory.openSession();
        OrderLog orderLog=null;
        try{
            Transaction tx=session.beginTransaction();
            orderLog=new OrderLog(
                    commodity,
                    user,
                    new Date(new java.util.Date().getTime())
            );

            session.save(orderLog);

            System.out.println("Buy confirmed!");
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void addNewUser(User user) {
        Session session=sessionFactory.openSession();
        try{
            Transaction tx=session.beginTransaction();
            session.save(user);
            System.out.println("User "+user.getUsername()+" registered!");
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void addCommodity(Commodity commodity) {
        Session session=sessionFactory.openSession();
        try{
            Transaction tx=session.beginTransaction();
            session.save(commodity);
            System.out.println("Commodity added: "+commodity.getName());
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
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
        finally {
            session.close();
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
        finally {
            session.close();
        }
    }

    public void addComment(Comment comment) {
        Session session=sessionFactory.openSession();
        try{
            Transaction tx=session.beginTransaction();
            session.save(comment);
            System.out.println("Comment added "+comment.getContent());
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
