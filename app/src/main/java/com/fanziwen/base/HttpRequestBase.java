package com.fanziwen.base;

import java.io.Serializable;

public class HttpRequestBase implements Serializable {


    /**
     * addTime : 2016-06-27 17:58:35.0
     * deleteFlag : 0
     * gender : 0
     * headicon : http://139.129.10.71:8080/appeduInterface/uploadFile/images/headicon/images/201606271758452096.jpg
     * id : 609
     * name : 笑笑
     * phonenum : 17778251120
     * pwd : 96e79218965eb72c92a549dd5a330112
     * status : 0
     */

    private UserBean user;
    /**
     * user : {"addTime":"2016-06-27 17:58:35.0","deleteFlag":0,"gender":0,"headicon":"http://139.129.10.71:8080/appeduInterface/uploadFile/images/headicon/images/201606271758452096.jpg","id":609,"name":"笑笑","phonenum":"17778251120","pwd":"96e79218965eb72c92a549dd5a330112","status":0}
     * Code : 0
     * Desc : 成功！
     */

    private int Code;
    private String Desc;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public static class UserBean {
        private String addTime;
        private int deleteFlag;
        private int gender;
        private String headicon;
        private int id;
        private String name;
        private String phonenum;
        private String pwd;
        private int status;

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getHeadicon() {
            return headicon;
        }

        public void setHeadicon(String headicon) {
            this.headicon = headicon;
        }

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

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
