package com.shop.entities;



import com.sun.istack.Nullable;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name="username",nullable = false,length = 32)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles;

    public User(String username, String password, Set<UserRole> userRoles) {
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
    }

    public User(){

    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
