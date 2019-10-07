package com.syl.snow.fragment.content4.mvp.m;

import com.syl.snow.fragment.content4.mvp.p.IMvpLoadDataCallback;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 */
public abstract class MvpBaseModel {
    protected String[] mParams;

    public MvpBaseModel setParams(String[] params) {
        mParams = params;
        return this;
    }

    /**
     * 执行get网络请求
     *
     * @param url
     * @param loadDataCallback
     */
    public abstract void executeGetRequest(String url, IMvpLoadDataCallback loadDataCallback);

    /**
     * 执行post网络请求
     *  @param url
     * @param page
     * @param count
     * @param loadDataCallback
     */
    public void executePostRequest(String url, int page, int count, IMvpLoadDataCallback loadDataCallback) {
    }
}
