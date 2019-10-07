package com.syl.snow.fragment.content4.mvp.v;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 */
public interface IMvpView extends IMvpBaseView {
    /**
     * 业务方获取数据成功,调用该方法刷新UI
     * @param data
     */
    void showSuccessData(String data);

    /**
     * 业务方获取数据失败,调用此方法展示失败页面刷新UI
     * @param data
     */
    void showFailureData(String data);
}
