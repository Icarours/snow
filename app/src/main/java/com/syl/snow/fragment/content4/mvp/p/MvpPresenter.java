package com.syl.snow.fragment.content4.mvp.p;

import com.syl.snow.fragment.content4.mvp.m.MvpDataModel;
import com.syl.snow.fragment.content4.mvp.m.MvpDataModelManager;
import com.syl.snow.fragment.content4.mvp.v.IMvpView;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 */
public class MvpPresenter extends MvpBasePresenter<IMvpView> implements IMvpLoadDataCallback {
    @Override
    public void onSuccess(String successData) {
        if (isViewAttached()) {
            getAttachView().dismissLoading();
            getAttachView().showSuccessData(successData);
        }
    }

    @Override
    public void onFailure(String failureData) {
        if (isViewAttached()) {
            getAttachView().dismissLoading();
            getAttachView().showFailureData(failureData);
        }
    }

    public void requestGet(String url) {
        getAttachView().showLoading();
        MvpDataModelManager.newInstance(MvpDataModel.class.getSimpleName()).executeGetRequest(url,this);
    }

    public void requestPost(String url, int page, int count) {
        getAttachView().showLoading();
        MvpDataModelManager.newInstance(MvpDataModel.class.getName()).executePostRequest(url,page,count,this);
    }
}
