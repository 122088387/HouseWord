package com.fanziwen.base;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.xutils.x;

import java.util.HashMap;

/**
 * @anthor 王晓赛 or 2016/6/22
 */
public class MyApplication extends Application {
    public static MyApplication application;

    public static MyApplication getApplication() {
        return application;
    }


    public static SharedPreferences.Editor editor;
    public static SharedPreferences sharedPreferences;
    public static String approotPath;
    public static HashMap<String, String> userState = new HashMap<String, String>();

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

    /**
     * Gson初始化-去乱码
     */
    public static Gson gson = new Gson();


    private static MyApplication WuYeApp;

    /**
     * 单例模式获取app
     *
     * @return
     */
    public static MyApplication Initialize() {
        if (WuYeApp == null) {
            WuYeApp = new MyApplication();
        }
        return WuYeApp;
    }

}
