package com.shop.service;

import com.shop.dao.Dao;
import com.shop.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private Dao dao;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.shop.entities.User user= dao.getUserByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("Cannot find user with such username");

        List<GrantedAuthority> authorities=buildUserAuthority(user.getUserRoles());
        return buildUserForAuthentication(user,authorities);
    }

    private User buildUserForAuthentication(com.shop.entities.User user,List<GrantedAuthority> authorities){
        return new User(user.getUsername(),user.getPassword(),authorities);  //it is not an entities user, but spring one!
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles){
        List<GrantedAuthority> listAuth=new ArrayList<GrantedAuthority>();

        for(UserRole userRole:userRoles){
            listAuth.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return listAuth;
    }
}
