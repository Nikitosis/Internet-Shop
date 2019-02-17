package com.shop.entities;

import javax.persistence.*;
import java.sql.Date;

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

    public Commodity(String name, double price, Date creationDate) {
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
    }

    public Commodity(){

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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
