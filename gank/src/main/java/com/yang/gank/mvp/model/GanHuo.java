package com.yang.gank.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Describe:
 * Created by Yang on 2019/1/22.
 */
public class GanHuo {
    /**
     * desc : 还在用ListView？
     * ganhuo_id : 57334c9d67765903fb61c418
     * publishedAt : 2016-05-12T12:04:43.857000
     * readability :
     * type : Android
     * url : http://www.jianshu.com/p/a92955be0a3e
     * who : 陈宇明
     */
    @SerializedName("_id")
    private String id;
    private String createdAt;
    private String desc;
    @SerializedName("ganhuo_id")
    private String ganhuoId;
    private String publishedAt;
    private String readability;
    private String source;
    private String type;
    private String url;
    private String who;
    private String used;
    private List<String> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGanhuoId() {
        return ganhuoId;
    }

    public void setGanhuoId(String ganhuo_id) {
        this.ganhuoId = ganhuo_id;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getReadability() {
        return readability;
    }

    public void setReadability(String readability) {
        this.readability = readability;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "GanHuo{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", ganhuoId='" + ganhuoId + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", readability='" + readability + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", who='" + who + '\'' +
                ", used='" + used + '\'' +
                ", images=" + images +
                '}';
    }
}
