package com.syl.snow.utils;

import android.util.Log;

import com.syl.snow.config.MyApplication;

/**
 * Created by Bright on 2018/12/8.
 *
 * @Describe
 * @Called
 */
public class LogUtils {

    public static void i(String tag, String message) {
        if (MyApplication.isOpenLog)
            Log.i(tag, message);
    }

    public static void d(String tag, String message) {
        if (MyApplication.isOpenLog)
            Log.d(tag, message);
    }

    public static void v(String tag, String message) {
        if (MyApplication.isOpenLog)
            Log.v(tag, message);
    }

    public static void e(String tag, String message) {
        if (MyApplication.isOpenLog)
            Log.e(tag, message);
    }

    public static void wtf(String tag, String message) {
        if (MyApplication.isOpenLog)
            Log.wtf(tag, message);
    }
}
