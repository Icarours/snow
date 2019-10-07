package com.syl.snow.fragment.content4.mvp.v;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 */
public interface IMvpBaseView {
    /**
     * 显示通用页面loading
     */
    void showLoading();

    /**
     * 取消通用页面loading
     */
    void dismissLoading();
}
