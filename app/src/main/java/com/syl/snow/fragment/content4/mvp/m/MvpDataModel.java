package com.syl.snow.fragment.content4.mvp.m;

import com.syl.snow.fragment.content4.mvp.p.IMvpLoadDataCallback;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.exception.ApiException;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 */
public class MvpDataModel extends MvpBaseModel {

    @Override
    public void executeGetRequest(String url, IMvpLoadDataCallback loadDataCallback) {

    }

    @Override
    public void executePostRequest(String url, int page, int count, IMvpLoadDataCallback loadDataCallback) {
        super.executePostRequest(url, page, count, loadDataCallback);
        EasyHttp.post(url)
                .params("page", String.valueOf(page))
                .params("count", String.valueOf(count))
                .execute(new CallBack<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(ApiException e) {
                        loadDataCallback.onFailure(e.toString());
                    }

                    @Override
                    public void onSuccess(String s) {
                        loadDataCallback.onSuccess(s);
                    }
                });
    }
}
