package com.yang.dm.mvp.model;

import java.util.List;

/**
 * Describe:
 * Created by Yang on 2019/2/13.
 */
public class VideoResponse<T> {
    /**
     * count : 20
     * total : 0
     * nextPageUrl : null
     * adExist : false
     */

    private int count;
    private int total;
    private String nextPageUrl;
    private boolean adExist;
    private List<T> itemList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public boolean isAdExist() {
        return adExist;
    }

    public void setAdExist(boolean adExist) {
        this.adExist = adExist;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }

}
