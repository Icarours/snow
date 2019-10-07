package com.syl.snow.fragment.content4.mvp.p;

import com.syl.snow.fragment.content4.mvp.v.IMvpBaseView;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 */
public class MvpBasePresenter<V extends IMvpBaseView> {
    public V mView;

    /**
     * 建立连接/绑定mView
     * 一般在onCreate方法中调用
     * @param view
     */
    public void attachView(V view) {
        mView = view;
    }

    /**
     * 解除连接/绑定,销毁mView
     * 一般在onDestroy方法中调用
     */
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

    /**
     * 是否绑定,是否与mView建立连接/绑定
     * 每次业务方发起请求都要调用此方法检测是否与mView建立连接/绑定
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    /**
     * 获取连接/绑定的View
     * @return
     */
    public V getAttachView() {
        return mView;
    }
}
