package com.yang.dm.mvp.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yang.dm.ui.adapter.VideoDisTopAdapter;

/**
 * Describe:
 * Created by Yang on 2019/2/23.
 */
public class TagsBean implements MultiItemEntity {


    /**
     * id : 176
     * name : 感人
     * actionUrl : eyepetizer://tag/176/?title=%E6%84%9F%E4%BA%BA
     * adTrack : null
     * desc : null
     * bgPicture : http://img.kaiyanapp.com/8b4dbcea6462e9c54994a46c9670e8f0.jpeg?imageMogr2/quality/60/format/jpg
     * headerImage : http://img.kaiyanapp.com/8b4dbcea6462e9c54994a46c9670e8f0.jpeg?imageMogr2/quality/60/format/jpg
     * tagRecType : NORMAL
     * childTagList : null
     * childTagIdList : null
     * communityIndex : 0
     */

    private int id;
    private String name;
    private String actionUrl;
    private String adTrack;
    private String desc;
    private String bgPicture;
    private String headerImage;
    private String tagRecType;
    private Object childTagList;
    private Object childTagIdList;
    private int communityIndex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Object getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getTagRecType() {
        return tagRecType;
    }

    public void setTagRecType(String tagRecType) {
        this.tagRecType = tagRecType;
    }

    public Object getChildTagList() {
        return childTagList;
    }

    public void setChildTagList(Object childTagList) {
        this.childTagList = childTagList;
    }

    public Object getChildTagIdList() {
        return childTagIdList;
    }

    public void setChildTagIdList(Object childTagIdList) {
        this.childTagIdList = childTagIdList;
    }

    public int getCommunityIndex() {
        return communityIndex;
    }

    public void setCommunityIndex(int communityIndex) {
        this.communityIndex = communityIndex;
    }

    @Override
    public int getItemType() {
        return VideoDisTopAdapter.TYPE_LEVEL_1;
    }
}
