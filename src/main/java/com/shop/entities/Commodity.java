package com.shop.entities;

import javax.persistence.*;
import java.security.AlgorithmParameterGenerator;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name="commodity")
public class Commodity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @Column(name="creation_date")
    private Date creationDate;

    @OneToMany(mappedBy = "commodity",orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments=new ArrayList<Comment>();

    public Commodity(String name, double price, Date creationDate,List<Comment> comments) {
        this.name = name;
        this.price = price;
        setCreationDate(creationDate);
        this.comments=comments;
    }

    public Commodity(){

    }

    public void addComment(Comment comment){
        comments.add(comment);
        comment.setCommodity(this);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }
//set time to 12AM to get rid of timezone conversions
// (in views date time could be shifted by several hours and it caused increasing/decreasing
// the whole days)
    public void setCreationDate(Date creationDate) {
        final long hours12 = 12L * 60L * 60L * 1000L;
        this.creationDate=creationDate;
        this.creationDate.setTime(this.creationDate.getTime()+hours12); //set time at 12 AM
                                                                        //to get rid of timezone conversions
    }
}
