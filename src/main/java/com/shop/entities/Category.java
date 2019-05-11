package com.shop.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

@Entity
@Table(name="category")
public class Category implements Comparable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    //name is like Company,Size,Type
    @Column(name="name",nullable = false,length = 32)
    private String name;

    //value is like Samsung,100x100,phone
    @Column(name="value",nullable = false)
    private String value;

    @ManyToMany(mappedBy = "categories")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Commodity> commodities=new HashSet<Commodity>();

    public Category(String categoryName,String categoryValue){
        this.name=categoryName;
        this.value=categoryValue;
    }
    //separated by __
    public Category(String nameWithValue){
        try {
            this.name = nameWithValue.split("__")[0];
            this.value = nameWithValue.split("__")[1];
        }
        catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            this.id=-1;
            this.name="";
            this.value="";
        }
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


    @Override
    public int hashCode() {
        return Objects.hash(name,value);
    }

    @Override
    public boolean equals(Object obj) {
        Category category=(Category)obj;
        return category.hashCode()==hashCode();
    }

    @Override
    public int compareTo(Object o) {
        Category category=(Category)o;
        if(category.hashCode()<hashCode())
            return -1;
        if(category.hashCode()>hashCode())
            return 1;
        return 0;
    }
}
