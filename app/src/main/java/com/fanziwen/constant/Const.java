package com.fanziwen.constant;

import android.os.Environment;

/**
 * 接口
 */
public interface Const {

    public static class House {

        /**
         * 本地服务器测试地址
         */

        public static final String URL_BASE = "http://192.168.1.120:80/";//本地
//        public static final String URL_BASE = "http://221.238.40.119:30017/";//公网测试

//        public static final String URL_BASE = "http://139.129.10.71:6060/";//正式

        /**
         * 用户登陆接口
         */
        public static final String URL_HOUSE_USER_APP_LOGIN = URL_BASE +
                "house/userApp/login.action";
        /**
         * 获取头部下拉列表数据
         */
        public static final String URL_HOUSE_USER_APP_GET_HOUSE_FILTRATE = URL_BASE +
                "house/userApp/getHouseFiltrate.action";

        /**
         * 查询房源列表
         */
        public static final String URL_HOUSE_USER_APP_GET_SELLER_LIST = URL_BASE +
                "house/userApp/getSellerList.action";
        /**
         * 查看房源详情
         */
        public static final String URL_USER_APP_GETsELLER_DETAIL = URL_BASE +
                "house/userApp/getSellerDetail.action?id";

    }

    /**
     * 数据库所存的KEY
     */
    public static class SPDate {
        /**
         * 用户ID
         */
        public static final String ID = "id";
        /**
         * 登陆名
         */
        public static final String LONGING_NAME = "loginName";
        /**
         * 登录密码
         */
        public static final String PASS_WORD = "WuYePassword";
        /**
         * 是否自动登录
         */
        public static final String LOGIN_AUTO = "WuYeAutoLogin";
        /**
         * 是否为管理人员
         */
        public static final String IS_ADMIN = "isAdmin";
        /**
         * 是否为管理人员
         */
        public static final String IS_WUYE_OR_YEZHU = "isWuYeOrYeZhu";
        /**
         * 头像地址
         */
        public static final String HEAD_URL = "portrait";
        /**
         * 是否自动声音
         */
        public static final String AUTO_VOICE = "WuYeAutoVoice";
        /**
         * 是否自动登录
         */
        public static final String AUTO_VIBRATION = "WuYeAutovVibration";
        /**
         * 用户名
         */
        public static final String USER_NAME = "userName";
        /**
         * 单位信息
         */
        public static final String USER_COMPANY = "WuYeCompany";
        /**
         * 小区ID
         */
        public static final String USER_DISTRICT_ID = "districtId";
        /**
         * 楼宇Id
         */
        public static final String USER_BUILDING_ID = "buildingId";
        /**
         * 楼层
         */
        public static final String USER_ELEMENT_ID = "elementId";
        /**
         * 小区名字
         */
        public static final String USER_DISTRICT_NAME = "districtName";
        /**
         * 公司
         */
        public static final String YE_ZHU_COMPANY = "company";

        /**
         * 极光绑定是的macId
         */
        public static final String MAC_ID = "macId";
        public static final String MAC_ID_ORIGINAL = "macId_original";
        /**
         * 是否第一次进入应用
         */
        public static final String IS_FIRST = "WuYeIsFirst";

        //经度
        public static final String LONGITHDE = "WuYeLongitude";
        //纬度
        public static final String LATITUDE = "WuYeLatitude";
    }

    /**
     * 数据库所存联系人界面的key
     */
    public static class SpAddress {
        /**
         * 联系人中paramKey
         */
        public static final String ADDRESS_KEY = "WuYeAddressKey";

    }


    public static class SAVE_MEDAR {
        /**
         * 声音
         */
        public static final String VOICE_PATH = Environment
                .getExternalStorageDirectory().getAbsolutePath() + "/tempVoice/";
        /**
         * 拍照
         */
        public static final String SIGN_PATH = Environment
                .getExternalStorageDirectory().getPath() + "/tempHand/";
        /**
         * 签字
         */
        public static final String PIC_PATH = Environment
                .getExternalStorageDirectory().getPath() + "/tempPic/";

    }

    public static class WXAPPID {
        /**
         * 微信APPID
         */
        public static final String ID = "wx41a06671b7e5f8e9";

    }
}
