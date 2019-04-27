package com.shop.dao;

import com.shop.entities.Commodity;

import java.util.ArrayList;
import java.util.List;

public class CommodityPaginator {

    private List<Commodity> commodityList=new ArrayList<Commodity>();
    private int curPage=1;
    private int itemsPerPage=10;

    public CommodityPaginator(List<Commodity> inititalList){
        this.commodityList=inititalList;
    }

    public CommodityPaginator filterMinPrice(int startPrice){
        for(int i=0;i<commodityList.size();i++)
            if(commodityList.get(i).getPrice()<startPrice)
                commodityList.remove(i);
        return this;
    }

    public CommodityPaginator filterMaxPrice(int endPrice){
        for(int i=0;i<commodityList.size();i++)
            if(commodityList.get(i).getPrice()>endPrice)
                commodityList.remove(i);
        return this;
    }

    public CommodityPaginator filterNamePattern(String namePattern){
        for(int i=0;i<commodityList.size();i++)
            if(!commodityList.get(i).getName().contains(namePattern))
                commodityList.remove(i);
        return this;
    }

    public int getSize(){
        return commodityList.size();
    }

    public List<Commodity> getPage(){
        int startingIndex=(curPage-1)*itemsPerPage;
        if(startingIndex>=getSize())
            return null;

        List<Commodity> result=new ArrayList<Commodity>();
        for(int i=startingIndex;i<startingIndex+itemsPerPage && i<getSize();i++){
            result.add(commodityList.get(i));
        }
        return result;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
