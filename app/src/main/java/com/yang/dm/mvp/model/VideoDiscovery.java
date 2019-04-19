package com.yang.dm.mvp.model;

import java.util.List;

/**
 * Describe:
 * Created by Yang on 2019/2/13.
 */
public class VideoDiscovery {
    /**
     * type : horizontalScrollCard
     * data : {"dataType":"HorizontalScrollCard","itemList":[{"type":"banner2","data":{"dataType":"Banner","id":1248,"title":"","description":"","image":"http://img.kaiyanapp.com/4f608f996865c23cbef73b15d9feee38.jpeg?imageMogr2/quality/60/format/jpg","actionUrl":"eyepetizer://webview/?title=%E5%BC%80%E7%9C%BC%E5%B8%A6%E4%BD%A0%E5%8E%BB%E5%8D%97%E6%9E%81&url=http%3A%2F%2Fwww.eyepetizer.net%2Farticle.html%3Fnid%3D1309%26shareable%3Dtrue%26type%3DarticleTopic","adTrack":null,"shade":false,"label":{"text":"","card":"","detail":null},"labelList":[],"header":{"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null},"autoPlay":false},"tag":null,"id":0,"adIndex":-1},{"type":"banner2","data":{"dataType":"Banner","id":1237,"title":"","description":"","image":"http://img.kaiyanapp.com/bc41e43777a66037c6c8dae88c39ae3d.png?imageMogr2/quality/60/format/jpg","actionUrl":"eyepetizer://webview/?title=&url=https%3A%2F%2Fwww.kaiyanapp.com%2Fcampaign%2Ftag_square%2Ftag_square.html%3Fnid%3D1306%26tid%3D1031%26tname%3D%2525E5%2525AF%2525BB%2525E5%252591%2525B3%26cookie%3D%26udid%3D%26shareable%3Dtrue","adTrack":null,"shade":false,"label":{"text":"","card":"","detail":null},"labelList":[],"header":{"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null},"autoPlay":false},"tag":null,"id":0,"adIndex":-1},{"type":"banner2","data":{"dataType":"Banner","id":1227,"title":"","description":"","image":"http://img.kaiyanapp.com/8389c40a39fce1c337700e8a9832e9ae.png?imageMogr2/quality/60/format/jpg","actionUrl":"eyepetizer://webview/?title=&url=http%3A%2F%2Fwww.kaiyanapp.com%2Fcampaign%2Fphotography_match%2Fphotography_match.html%3Fshareable%3Dtrue","adTrack":null,"shade":false,"label":{"text":"","card":"","detail":null},"labelList":[],"header":{"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null},"autoPlay":false},"tag":null,"id":0,"adIndex":-1}],"count":3}
     * tag : null
     * id : 0
     * adIndex : -1
     */

    private String type;
    private ContentBean.DataBean data;
    private Object tag;
    private int id;
    private int adIndex;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ContentBean.DataBean getData() {
        return data;
    }

    public void setData(ContentBean.DataBean data) {
        this.data = data;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdIndex() {
        return adIndex;
    }

    public void setAdIndex(int adIndex) {
        this.adIndex = adIndex;
    }

    public static class DataBeanX {
        /**
         * dataType : HorizontalScrollCard
         * itemList : [{"type":"banner2","data":{"dataType":"Banner","id":1248,"title":"","description":"","image":"http://img.kaiyanapp.com/4f608f996865c23cbef73b15d9feee38.jpeg?imageMogr2/quality/60/format/jpg","actionUrl":"eyepetizer://webview/?title=%E5%BC%80%E7%9C%BC%E5%B8%A6%E4%BD%A0%E5%8E%BB%E5%8D%97%E6%9E%81&url=http%3A%2F%2Fwww.eyepetizer.net%2Farticle.html%3Fnid%3D1309%26shareable%3Dtrue%26type%3DarticleTopic","adTrack":null,"shade":false,"label":{"text":"","card":"","detail":null},"labelList":[],"header":{"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null},"autoPlay":false},"tag":null,"id":0,"adIndex":-1},{"type":"banner2","data":{"dataType":"Banner","id":1237,"title":"","description":"","image":"http://img.kaiyanapp.com/bc41e43777a66037c6c8dae88c39ae3d.png?imageMogr2/quality/60/format/jpg","actionUrl":"eyepetizer://webview/?title=&url=https%3A%2F%2Fwww.kaiyanapp.com%2Fcampaign%2Ftag_square%2Ftag_square.html%3Fnid%3D1306%26tid%3D1031%26tname%3D%2525E5%2525AF%2525BB%2525E5%252591%2525B3%26cookie%3D%26udid%3D%26shareable%3Dtrue","adTrack":null,"shade":false,"label":{"text":"","card":"","detail":null},"labelList":[],"header":{"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null},"autoPlay":false},"tag":null,"id":0,"adIndex":-1},{"type":"banner2","data":{"dataType":"Banner","id":1227,"title":"","description":"","image":"http://img.kaiyanapp.com/8389c40a39fce1c337700e8a9832e9ae.png?imageMogr2/quality/60/format/jpg","actionUrl":"eyepetizer://webview/?title=&url=http%3A%2F%2Fwww.kaiyanapp.com%2Fcampaign%2Fphotography_match%2Fphotography_match.html%3Fshareable%3Dtrue","adTrack":null,"shade":false,"label":{"text":"","card":"","detail":null},"labelList":[],"header":{"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null},"autoPlay":false},"tag":null,"id":0,"adIndex":-1}]
         * count : 3
         */

        private String dataType;
        private int count;
        private List<ItemListBean> itemList;

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ItemListBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBean> itemList) {
            this.itemList = itemList;
        }

        public static class ItemListBean {
            /**
             * type : banner2
             * data : {"dataType":"Banner","id":1248,"title":"","description":"","image":"http://img.kaiyanapp.com/4f608f996865c23cbef73b15d9feee38.jpeg?imageMogr2/quality/60/format/jpg","actionUrl":"eyepetizer://webview/?title=%E5%BC%80%E7%9C%BC%E5%B8%A6%E4%BD%A0%E5%8E%BB%E5%8D%97%E6%9E%81&url=http%3A%2F%2Fwww.eyepetizer.net%2Farticle.html%3Fnid%3D1309%26shareable%3Dtrue%26type%3DarticleTopic","adTrack":null,"shade":false,"label":{"text":"","card":"","detail":null},"labelList":[],"header":{"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null},"autoPlay":false}
             * tag : null
             * id : 0
             * adIndex : -1
             */

            private String type;
            private ContentBean.DataBean data;
            private Object tag;
            private int id;
            private int adIndex;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public ContentBean.DataBean getData() {
                return data;
            }

            public void setData(ContentBean.DataBean data) {
                this.data = data;
            }

            public Object getTag() {
                return tag;
            }

            public void setTag(Object tag) {
                this.tag = tag;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAdIndex() {
                return adIndex;
            }

            public void setAdIndex(int adIndex) {
                this.adIndex = adIndex;
            }

        }
    }
}
