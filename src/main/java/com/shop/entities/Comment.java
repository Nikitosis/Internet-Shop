package com.shop.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="comment")
public class Comment {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="content")
    private String content;

    @Column(name="date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="user")
    private User user;

    @ManyToOne
    @JoinColumn(name="commodity")
    private Commodity commodity;

    public Comment(){

    }

    public Comment(String content, Date date, User user, Commodity commodity) {
        this.content = content;
        this.date = date;
        this.user = user;
        this.commodity = commodity;
    }

    public Comment(String content){
        this.content=content;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}
