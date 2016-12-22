package com.fanziwen.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/21
 */

public class HouseBean implements Serializable {


    /**
     * respCode : 0
     * data : [{"id":2,"name":"东家乡","address":"石家庄联邦购物广场","phone":"17743888575","introduce":"234","owner":"AAA","banner":"http://139.129.10.71:6061/propertyManager/uploadFile/sellerBanner/1476004056135.jpg","longitude":"114.56114","latitude":"38.017339","createTime":"2016-12-21 09:21:34","orders":1,"houseType":154,"useType":174,"url":"http://221.238.40.119:30017/propertyInterface/uploadFile/repairImages/2016/2016-12-19/1482133194319.JPG"},{"id":3,"name":"一室两厅一厨一卫好房源","address":"石家庄联邦购物广场","phone":"17778251130","introduce":"位置好","owner":"BB","banner":"http://192.168.1.120:80/house/uploadFile/sellerBanner/1482283294140.png","longitude":"116.403262","latitude":"39.889473","createTime":"2016-12-21 09:21:34","orders":1,"houseType":153,"useType":171,"url":"http://221.238.40.119:30017/propertyInterface/uploadFile/repairImages/2016/2016-12-19/1482133194319.JPG"},{"id":1,"name":"火红楼","address":"翟营大街","phone":"0311-82822236","introduce":"123","owner":"CC","banner":"http://139.129.10.71:6061/propertyManager/uploadFile/sellerBanner/1476004352961.jpg","longitude":"114.56127","latitude":"38.028751","createTime":"2016-12-21 09:21:34","orders":2,"houseType":155,"useType":173,"url":""}]
     * respMsg : 成功！
     */

    private int respCode;
    private String respMsg;
    /**
     * id : 2
     * name : 东家乡
     * address : 石家庄联邦购物广场
     * phone : 17743888575
     * introduce : 234
     * owner : AAA
     * banner : http://139.129.10.71:6061/propertyManager/uploadFile/sellerBanner/1476004056135.jpg
     * longitude : 114.56114
     * latitude : 38.017339
     * createTime : 2016-12-21 09:21:34
     * orders : 1
     * houseType : 154
     * useType : 174
     * url : http://221.238.40.119:30017/propertyInterface/uploadFile/repairImages/2016/2016-12-19/1482133194319.JPG
     */

    private List<DataBean> data;

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private int id;
        private String name;
        private String address;
        private String phone;
        private String introduce;
        private String owner;
        private String banner;
        private String longitude;
        private String latitude;
        private String createTime;
        private int orders;
        private int houseType;
        private int useType;
        private String url;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getOrders() {
            return orders;
        }

        public void setOrders(int orders) {
            this.orders = orders;
        }

        public int getHouseType() {
            return houseType;
        }

        public void setHouseType(int houseType) {
            this.houseType = houseType;
        }

        public int getUseType() {
            return useType;
        }

        public void setUseType(int useType) {
            this.useType = useType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
