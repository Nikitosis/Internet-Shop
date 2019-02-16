package com.shop.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class HibernateConfig {
    @Bean
    public SessionFactory sessionFactory(){
        SessionFactory sessionFactory=new org.hibernate.cfg.Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .buildSessionFactory();
        return sessionFactory;
    }
}
