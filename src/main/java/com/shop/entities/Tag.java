package com.shop.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="tag")
public class Tag {
    @Id
    @Column(name="tag",nullable = false,length = 32)
    private String tag;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name="commodity_tag",
            joinColumns = {@JoinColumn(name="tag")},
            inverseJoinColumns ={@JoinColumn(name="commodity_id")}
    )
    private Set<Commodity> commodities=new HashSet<Commodity>();

    public Tag(String tag){
        this.tag=tag;
    }

    public Tag(){

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(Set<Commodity> commodities) {
        this.commodities = commodities;
    }
}
