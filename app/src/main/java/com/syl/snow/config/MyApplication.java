package com.syl.snow.config;

import android.app.Application;

/**
 * Created by Bright on 2018/12/8.
 *
 * @Describe 常量, 单例设置
 * @Called
 */
public class MyApplication extends Application {
    public static boolean isOpenLog = true;

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
