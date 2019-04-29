package com.shop.dao;

import com.shop.entities.Commodity;

import java.util.ArrayList;
import java.util.List;

public class CommodityPaginator {

    private List<Commodity> commodityList=new ArrayList<Commodity>();
    private int pageIndex=1;
    private int itemsPerPage=2;

    public CommodityPaginator(List<Commodity> inititalList){
        this.commodityList=inititalList;
    }

    public int getSize(){
        return commodityList.size();
    }

    public List<Commodity> getPage(){
        int startingIndex=(pageIndex-1)*itemsPerPage;
        if(startingIndex>=getSize())
            return null;

        List<Commodity> result=new ArrayList<Commodity>();
        for(int i=startingIndex;i<startingIndex+itemsPerPage && i<getSize();i++){
            result.add(commodityList.get(i));
        }
        return result;
    }

    public int getTotalPages(){
        int result=commodityList.size()/itemsPerPage;
        if(commodityList.size()%itemsPerPage!=0)
            result++;
        return result;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        if(pageIndex==null)
            throw new NullPointerException("curPage cannot be equal to null");

        this.pageIndex = pageIndex;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        if(itemsPerPage==null)
            throw new NullPointerException("itemsPerPage cannot be equal to null");

        this.itemsPerPage = itemsPerPage;
    }
}
