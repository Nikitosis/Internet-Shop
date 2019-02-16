package com.shop.config;

import com.shop.dao.DaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.shop.dao","com.shop.service"})
public class SpringConfig {

    @Bean
    public DaoImpl getDao(){
        return new DaoImpl();
    }
}
