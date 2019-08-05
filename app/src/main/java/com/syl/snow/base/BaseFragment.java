package com.syl.snow.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syl.snow.utils.LogUtils;
import com.zhouyou.http.EasyHttp;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * Created by Bright on 2018/12/8.
 *
 * @Describe Fragment的基类
 * @Called
 */
public class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    public Handler mHandler = new Handler(Looper.getMainLooper());
    public List<Disposable> mDisposableList = new ArrayList<>();
    private ProgressDialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(TAG, "onCreate()");
        init();
        initData();
    }

    public void init() {

    }

    public void initData() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.i(TAG, "onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LogUtils.i(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtils.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        LogUtils.d(TAG, "onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        LogUtils.d(TAG, "onDestroyView");
        super.onDestroyView();
        ButterKnife.unbind(this);
        for (int i = 0; i < mDisposableList.size(); i++) {
            Disposable disposable = mDisposableList.get(i);
            EasyHttp.cancelSubscription(disposable);//取消请求
            mDisposableList.remove(disposable);
        }
    }

    @Override
    public void onResume() {
        LogUtils.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onStart() {
        LogUtils.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        LogUtils.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    public void onPause() {
        LogUtils.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.d(TAG, "onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Context context) {
        LogUtils.d(TAG, "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        LogUtils.d(TAG, "onDetach");
        super.onDetach();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        LogUtils.d(TAG, "onAttachFragment");
        super.onAttachFragment(childFragment);
    }

    /**
     * 圆形进度条对话框,网络请求,显示对话框
     */
    public void showDialog() {
        mDialog = new ProgressDialog(getContext());
        mDialog.setMessage("正在加载中");
        mDialog.show();
    }

    /**
     * 圆形进度条对话框,网络请求,隐藏对话框
     */
    public void hideDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
