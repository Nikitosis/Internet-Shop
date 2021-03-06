package com.shop.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="order_log")
public class OrderLog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="commodity_id")
    private Commodity commodity;

    @ManyToOne
    @JoinColumn(name="user_name")
    private User user;

    @Column(name="date")
    private Date date;

    public OrderLog(Commodity commodity, User user, Date date) {
        this.commodity = commodity;
        this.user = user;
        this.date = date;
    }

    public OrderLog(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
