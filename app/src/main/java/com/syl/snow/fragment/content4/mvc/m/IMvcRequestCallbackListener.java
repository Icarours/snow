package com.syl.snow.fragment.content4.mvc.m;

/**
 * Created by Bright on 2019/10/6.
 *
 * @Describe
 * @Called
 */
public interface IMvcRequestCallbackListener<T> {
    void onSuccess(T successData);

    void onFailure(String failureData);
}
