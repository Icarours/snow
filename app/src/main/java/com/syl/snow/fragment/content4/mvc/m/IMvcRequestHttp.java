package com.syl.snow.fragment.content4.mvc.m;

/**
 * Created by Bright on 2019/10/6.
 *
 * @Describe
 * 如果有加载更多,至少页面索引是可变的
 * @Called
 */
public interface IMvcRequestHttp {
    void httpGetRequest(String url, IMvcRequestCallbackListener requestCallbackListener);

    void httpPostRequest(String url, IMvcRequestCallbackListener requestCallbackListener);
}
