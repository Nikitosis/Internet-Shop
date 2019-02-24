package com.shop.config;

import com.shop.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.shop.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        System.out.println("Admin pass: "+passwordEncoder().encode("admin"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/commodities").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/userOrders").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/userInfo/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/basket").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder;
    }
}
