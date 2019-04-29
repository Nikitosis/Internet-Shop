package com.shop.dao;

import com.shop.entities.Tag;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Set;

public class CommodityFilter {
    private Integer minPrice=null;
    private Integer maxPrice=null;
    private String namePattern=null;
    private Set<Tag> tags=null;

    public void CommodityFilter(){

    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public CommodityFilter setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public CommodityFilter setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public String getNamePattern() {
        return namePattern;
    }

    public CommodityFilter setNamePattern(String namePattern) {
        this.namePattern = namePattern;
        return this;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public CommodityFilter setTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Criteria applyToCriteria(Criteria criteria){
        if(minPrice!=null){
            criteria.add(Restrictions.gt("price",minPrice));
        }
        if(maxPrice!=null){
            criteria.add(Restrictions.lt("price",maxPrice));
        }
        if(namePattern!=null){
            criteria.add(Restrictions.like("name","%"+namePattern + "%"));
        }
        if(tags!=null){
            criteria.add(Restrictions.in("tags",tags));
        }
        return criteria;
    }
}
