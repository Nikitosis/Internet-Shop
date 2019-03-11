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

    @OneToMany(mappedBy = "user", orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<UserRole> userRoles=new HashSet<UserRole>();

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Comment> comments=new HashSet<Comment>();

    public User(String username, String password, Set<UserRole> userRoles,Set<Comment> comments) {
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
        this.comments=comments;
    }

    public User(){

    }

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setUser(this);
    }

    public void addUserRole(UserRole role){
        this.userRoles.add(role);
        role.setUser(this);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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
