package com.shop.entities;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="username",nullable = false)
    private User user;

    @Column(name="role",nullable = false)
    private String role;

    public UserRole(){
    }

    public UserRole(String role){
        this.role=role;
        this.user=null;
    }

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
