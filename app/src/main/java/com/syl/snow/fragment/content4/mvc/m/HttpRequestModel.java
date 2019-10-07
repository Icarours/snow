package com.syl.snow.fragment.content4.mvc.m;

import com.syl.snow.utils.LogUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.exception.ApiException;

/**
 * Created by Bright on 2019/10/6.
 *
 * @Describe
 * @Called
 */
public class HttpRequestModel implements IMvcRequestHttp {
    private static final String TAG = HttpRequestModel.class.getSimpleName();

    @Override
    public void httpGetRequest(String url, IMvcRequestCallbackListener requestCallbackListener) {
        EasyHttp.get(url)
                .execute(new CallBack<String>() {
                    @Override
                    public void onStart() {
                        LogUtils.d(TAG, "---onStart()");
                    }

                    @Override
                    public void onCompleted() {
                        LogUtils.d(TAG, "---onCompleted");
                    }

                    @Override
                    public void onError(ApiException e) {
                        LogUtils.d(TAG, "---onError");
                        requestCallbackListener.onFailure(e.toString());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LogUtils.d(TAG, "---onSuccess");
                        requestCallbackListener.onSuccess(s);
                    }
                });
    }

    @Override
    public void httpPostRequest(String url, IMvcRequestCallbackListener requestCallbackListener) {
        EasyHttp.post(url)
                .params("page", "1")
                .params("count", "16")//如果有加载更多,至少页面索引是可变的
                .execute(new CallBack<String>() {
                    @Override
                    public void onStart() {
                        LogUtils.d(TAG, "---onStart()");
                    }

                    @Override
                    public void onCompleted() {
                        LogUtils.d(TAG, "---onCompleted");
                    }

                    @Override
                    public void onError(ApiException e) {
                        LogUtils.d(TAG, "---onError");
                        requestCallbackListener.onFailure(e.toString());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LogUtils.d(TAG, "---onSuccess");
                        requestCallbackListener.onSuccess(s);
                    }
                });
    }
}
