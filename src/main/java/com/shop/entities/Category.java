package com.shop.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    //name is like Company,Size,Type
    @Column(name="name",nullable = false,length = 32)
    private String name;

    //value is like Samsung,100x100,phone
    @Column(name="value",nullable = false)
    private String value;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name="commodity_category",
            joinColumns = {@JoinColumn(name="category_id")},
            inverseJoinColumns ={@JoinColumn(name="commodity_id")}
    )
    private Set<Commodity> commodities=new HashSet<Commodity>();

    public Category(String categoryName,String categoryValue){
        this.name=categoryName;
        this.value=categoryValue;
    }

    public Category(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(Set<Commodity> commodities) {
        this.commodities = commodities;
    }
}
