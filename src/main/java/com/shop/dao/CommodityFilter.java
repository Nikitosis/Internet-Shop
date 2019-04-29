package com.shop.dao;

import com.shop.entities.Commodity;
import com.shop.entities.Tag;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommodityFilter {
    private Integer id=null;
    private Integer minPrice=null;
    private Integer maxPrice=null;
    private String namePattern=null;
    private List<Tag> tags=null;


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

    public List<Tag> getTags() {
        return tags;
    }

    public CommodityFilter setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public CommodityFilter setId(Integer id) {
        this.id = id;
        return this;
    }

    public CriteriaQuery<Commodity> getCriteriaQuery(CriteriaBuilder criteriaBuilder){
        CriteriaQuery<Commodity> criteria=criteriaBuilder.createQuery(Commodity.class);
        Root<Commodity> commodityRoot=criteria.from(Commodity.class);
        criteria.select(commodityRoot);
        if(id!=null){
            criteria.where(criteriaBuilder.equal(commodityRoot.get("id"),id));
        }
        if(minPrice!=null){
            criteria.where(criteriaBuilder.gt(commodityRoot.get("price"),minPrice));
            //criteria.add(Restrictions.gt("price",minPrice));
        }
        if(maxPrice!=null){
            criteria.where(criteriaBuilder.lt(commodityRoot.get("price"),maxPrice));
            //criteria.add(Restrictions.lt("price",maxPrice));
        }
        if(namePattern!=null){
            criteria.where(criteriaBuilder.like(commodityRoot.get("name"),"%"+namePattern+"%"));
            //criteria.add(Restrictions.like("name","%"+namePattern + "%"));
        }
        if(tags!=null){  //where at leas one tag from tags is in Commodity.tags
            List<Predicate> predicates=new ArrayList<Predicate>();
            for(Tag tag:tags){
                predicates.add(criteriaBuilder.isMember(tag,commodityRoot.get("tags")));
            }
            Predicate conjunction=criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            criteria.where(conjunction);
            //commodityRoot.join(tags);
            //criteria.add(Restrictions.in("tags",tags));
        }
        return criteria;
    }
}
