package com.fanziwen.main.bean;

import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/21
 */

public class SelectBean {

    /**
     * respCode : 0
     * items : [{"subtitles":[{"val":-1,"name":"房源类型"},{"id":153,"name":"一室二厅一厨二卫","orders":1,"type":2,"val":1,"level":2,"imagesUrl":null,"roleId":null,"customStr":null},{"id":154,"name":"一室一厅一厨一卫","orders":2,"type":2,"val":2,"level":2,"imagesUrl":null,"roleId":null,"customStr":null},{"id":155,"name":"三室二厅一厨二卫","orders":3,"type":2,"val":3,"level":2,"imagesUrl":null,"roleId":null,"customStr":null}],"orders":1,"fieldname":"houseType"},{"subtitles":[{"val":-1,"name":"房源性质"},{"id":152,"name":"新房","orders":1,"type":1,"val":1,"level":2,"imagesUrl":null,"roleId":null,"customStr":null},{"id":171,"name":"二手房","orders":2,"type":1,"val":2,"level":2,"imagesUrl":null,"roleId":null,"customStr":null},{"id":172,"name":"出租房","orders":3,"type":1,"val":3,"level":2,"imagesUrl":null,"roleId":null,"customStr":null},{"id":173,"name":"商铺","orders":4,"type":1,"val":244,"level":2,"imagesUrl":null,"roleId":null,"customStr":null},{"id":174,"name":"写字楼","orders":5,"type":1,"val":5,"level":2,"imagesUrl":null,"roleId":null,"customStr":null}],"orders":2,"fieldname":"useType"}]
     * respMsg : 成功！
     */

    private int respCode;
    private String respMsg;
    /**
     * subtitles : [{"val":-1,"name":"房源类型"},{"id":153,"name":"一室二厅一厨二卫","orders":1,"type":2,"val":1,"level":2,"imagesUrl":null,"roleId":null,"customStr":null},{"id":154,"name":"一室一厅一厨一卫","orders":2,"type":2,"val":2,"level":2,"imagesUrl":null,"roleId":null,"customStr":null},{"id":155,"name":"三室二厅一厨二卫","orders":3,"type":2,"val":3,"level":2,"imagesUrl":null,"roleId":null,"customStr":null}]
     * orders : 1
     * fieldname : houseType
     */

    private List<ItemsBean> items;

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

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        private int orders;
        private String fieldname;
        /**
         * val : -1
         * name : 房源类型
         */

        private List<SubtitlesBean> subtitles;

        public int getOrders() {
            return orders;
        }

        public void setOrders(int orders) {
            this.orders = orders;
        }

        public String getFieldname() {
            return fieldname;
        }

        public void setFieldname(String fieldname) {
            this.fieldname = fieldname;
        }

        public List<SubtitlesBean> getSubtitles() {
            return subtitles;
        }

        public void setSubtitles(List<SubtitlesBean> subtitles) {
            this.subtitles = subtitles;
        }

        public static class SubtitlesBean {
            private int val;
            private String name;

            public int getVal() {
                return val;
            }

            public void setVal(int val) {
                this.val = val;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
