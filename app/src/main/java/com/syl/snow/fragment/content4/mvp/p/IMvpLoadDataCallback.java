package com.syl.snow.fragment.content4.mvp.p;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 */
public interface IMvpLoadDataCallback {
    /**
     * 成功回调,刷新UI
     *
     * @param successData
     */
    void onSuccess(String successData);

    /**
     * 失败回调,展示失败提示信息
     *
     * @param failureData
     */
    void onFailure(String failureData);
}
