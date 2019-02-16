package com.shop.dao;

import com.shop.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DaoImpl implements Dao {
    @Autowired
    SessionFactory sessionFactory;

    public User getUser(int id) {
        return new User();
    }
}
