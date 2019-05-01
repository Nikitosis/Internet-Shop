package com.shop.dao;

import com.shop.entities.Commodity;
import com.shop.entities.Category;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class CommodityFilter {
    public enum SortingColumn{
        NONE,
        ID,
        NAME,
        PRICE,
        CREATION_DATE
    }
    public enum SortingOrder{
        ASC,
        DESC
    }

    private Integer id=null;
    private Integer minPrice=null;
    private Integer maxPrice=null;
    private String namePattern=null;
    private List<Category> categories=null;
    private SortingColumn sortBy=SortingColumn.NONE;
    private SortingOrder order=SortingOrder.ASC;



    public void CommodityFilter(){

    }

    public SortingOrder getOrder() {
        return order;
    }

    public CommodityFilter setOrder(SortingOrder order) {
        this.order = order;
        return this;
    }

    public SortingColumn getSortBy() {
        return sortBy;
    }

    public CommodityFilter setSortBy(SortingColumn sortBy) {
        this.sortBy = sortBy;
        return this;
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

    public List<Category> getCategories() {
        return categories;
    }

    public CommodityFilter setCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public CommodityFilter setId(Integer id) {
        this.id = id;
        return this;
    }

    public Criteria addFilterToCriteria(Criteria criteria){
        if(id!=null){
            criteria.add(Restrictions.eq("commodity.id",id));
        }
        if(minPrice!=null){
            criteria.add(Restrictions.gt("commodity.price",minPrice));
        }
        if(maxPrice!=null){
            criteria.add(Restrictions.lt("commodity.price",maxPrice));
        }
        if(namePattern!=null){
            criteria.add(Restrictions.like("commodity.name","%"+namePattern + "%"));
        }
//        if(categories!=null){  //where at leas one tag from tags is in Commodity.tags
//            List<Predicate> predicates=new ArrayList<Predicate>();
//            for(Category category:categories){
//                predicates.add(criteriaBuilder.isMember(category,commodityRoot.get("categories")));
//            }
//            Predicate conjunction=criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
//            criteria.where(conjunction);
//            //commodityRoot.join(tags);
//            //criteria.add(Restrictions.in("tags",tags));
//        }

        if(sortBy!=SortingColumn.NONE){
            String column="commodity.id";
            switch(sortBy){
                case ID:
                {
                    column="commodity.id";
                    break;
                }
                case NAME:
                {
                    column="commodity.name";
                    break;
                }
                case PRICE:
                {
                    column="commodity.price";
                    break;
                }
                case CREATION_DATE:
                {
                    column="commodity.creation_date";
                    break;
                }
            }
            if(order==SortingOrder.ASC){
                criteria.addOrder(Order.asc(column));
            }
            else{
                criteria.addOrder(Order.desc(column));
            }
        }



        return criteria;
    }
}
