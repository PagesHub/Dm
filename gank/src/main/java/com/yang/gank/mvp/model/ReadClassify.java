package com.yang.gank.mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Describe:
 * Created by Yang on 2019/1/25.
 */
public class ReadClassify {

    /**
     * _id : 57c83777421aa97cbd81c74d
     * en_name : wow
     * name : 科技资讯
     * rank : 1
     */

    @SerializedName("_id")
    private String id;
    private String en_name;
    private String name;
    private int rank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
