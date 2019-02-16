package com.shop.entities;



import javax.persistence.*;


@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
