package com.syl.snow.fragment.content4.mvc.v;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.LogUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.exception.ApiException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/10/5.
 *
 * @Describe
 * @Called mvc
 */
public class MvcFragment extends BaseFragment {
    private static final String TAG = MvcFragment.class.getSimpleName();
    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.srl)
    SwipeRefreshLayout mSrl;
    @Bind(R.id.tv_no_data)
    TextView mTvNoData;
    @Bind(R.id.tv_retry)
    TextView mTvRetry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mvc, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        String url = "https://api.apiopen.top/getWangYiNews";
        EasyHttp.post(url)
                .params("page", "1")
                .params("count", "16")
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
                    }

                    @Override
                    public void onSuccess(String s) {
                        LogUtils.d(TAG, "---onSuccess");
                    }
                });
    }
}
