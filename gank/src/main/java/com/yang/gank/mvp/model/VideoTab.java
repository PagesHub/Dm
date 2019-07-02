package com.yang.gank.mvp.model;

/**
 * Describe:
 * Created by Yang on 2019/2/12.
 */
public class VideoTab {
    /**
     * nameType : 0
     * apiUrl : http://baobab.kaiyanapp.com/api/v5/index/tab/discovery
     * name : 发现
     * tabType : 0
     * id : -1
     * adTrack : null
     */

    private int nameType;
    private String apiUrl;
    private String name;
    private int tabType;
    private int id;
    private Object adTrack;

    public int getNameType() {
        return nameType;
    }

    public void setNameType(int nameType) {
        this.nameType = nameType;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTabType() {
        return tabType;
    }

    public void setTabType(int tabType) {
        this.tabType = tabType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(Object adTrack) {
        this.adTrack = adTrack;
    }
}
