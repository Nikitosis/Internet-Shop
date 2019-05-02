package com.shop.dao;

import com.shop.entities.Commodity;
import com.shop.entities.Category;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public CommodityFilter addCategory(Category category){
        if(this.categories==null)
            this.categories=new ArrayList<Category>();

        this.categories.add(category);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public CommodityFilter setId(Integer id) {
        this.id = id;
        return this;
    }

    public Criteria addFilterToCriteria(Criteria criteria,String commodityAlias,String categoryAlias){
        if(id!=null){
            criteria.add(Restrictions.eq(commodityAlias+".id",id));
        }
        if(minPrice!=null){
            criteria.add(Restrictions.gt(commodityAlias+".price",minPrice));
        }
        if(maxPrice!=null){
            criteria.add(Restrictions.lt(commodityAlias+".price",maxPrice));
        }
        if(namePattern!=null){
            criteria.add(Restrictions.like(commodityAlias+".name","%"+namePattern + "%"));
        }
        //when certain categories with the same names are set,
        //we filter commodities, that have at least one of the set categories for every unique category name
        if(categories!=null){
            Map<String,List<Category>> mapNameToCategory=new HashMap<String,List<Category>>();
            for(Category category:categories){
                if(!mapNameToCategory.containsKey(category.getName())) {
                    mapNameToCategory.put(category.getName(), new ArrayList<Category>());
                }
                mapNameToCategory.get(category.getName()).add(category);
            }
            Conjunction mainConjunction=Restrictions.conjunction();
            for(Map.Entry<String, List<Category>> entry: mapNameToCategory.entrySet()){
                List<Category> categoryList=entry.getValue();
                Disjunction categoryNameDisjunction = Restrictions.disjunction();
                for(Category category:categoryList){
                    categoryNameDisjunction.add(Restrictions.and(
                            Restrictions.eq(categoryAlias+".name",category.getName()),
                            Restrictions.eq(categoryAlias+".value",category.getValue()))
                    );
                }
                mainConjunction.add(categoryNameDisjunction);
            }
            criteria.add(mainConjunction);
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
            String column=commodityAlias+".id";
            switch(sortBy){
                case ID:
                {
                    column=commodityAlias+".id";
                    break;
                }
                case NAME:
                {
                    column=commodityAlias+".name";
                    break;
                }
                case PRICE:
                {
                    column=commodityAlias+".price";
                    break;
                }
                case CREATION_DATE:
                {
                    column=commodityAlias+".creationDate";
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
