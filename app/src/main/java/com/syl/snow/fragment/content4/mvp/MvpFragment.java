package com.syl.snow.fragment.content4.mvp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.syl.snow.R;
import com.syl.snow.fragment.content4.mvp.m.ApiImageE;
import com.syl.snow.fragment.content4.mvp.m.ImageE;
import com.syl.snow.fragment.content4.mvp.p.MvpPresenter;
import com.syl.snow.fragment.content4.mvp.v.IMvpView;
import com.syl.snow.fragment.content4.mvp.v.ImageAdapter;
import com.syl.snow.fragment.content4.mvp.v.MvpBaseFragment;
import com.syl.snow.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

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
 * @Called mvp
 */
public class MvpFragment extends MvpBaseFragment implements IMvpView {
    private static final String TAG = MvpFragment.class.getSimpleName();
    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.srl)
    SwipeRefreshLayout mSrl;
    @Bind(R.id.tv_no_data)
    TextView mTvNoData;
    @Bind(R.id.tv_retry)
    TextView mTvRetry;
    private MvpPresenter mMvpPresenter;
    private int page = 1;
    private int count = 16;
    private List<ImageE> mList = new ArrayList<>();//数据集
    private ImageAdapter mAdapter;
    private boolean isRefreshPullDown = false;
    private String mUrl = "https://api.apiopen.top/getImages";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //必须使用第三个参数是attachToRoot的构造方法,使用二参构造方法会crash
        View rootView = inflater.inflate(R.layout.fragment_mvp, container, false);
        ButterKnife.bind(this,rootView);
        mMvpPresenter = new MvpPresenter();
        mMvpPresenter.attachView(this);

        mMvpPresenter.requestPost(mUrl, page, count);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mSrl.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                isRefreshPullDown=true;
                page = 1;
                mMvpPresenter.requestPost(mUrl, page, count);
            }
        });
        return rootView;
    }

    @Override
    public void showSuccessData(String data) {
        LogUtils.d(TAG, data);
        if (!TextUtils.isEmpty(data)) {
            parseData(data);
        } else {
            LogUtils.d(TAG, "data数据为空");
        }
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        if (mList.size() % 16 == 0) {
            page++;
            mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mMvpPresenter.requestPost(mUrl, page, count);
                        }
                    }, 1000);
                }
            }, mRv);
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreEnd();
        }
    }

    private void parseData(String data) {
        ApiImageE apiImageE = JSONObject.parseObject(data, ApiImageE.class);
        if (apiImageE.getCode() == 200) {
            String result = apiImageE.getResult();
            if (!TextUtils.isEmpty(result)) {
                List<ImageE> list = JSONObject.parseArray(result, ImageE.class);
                if (list.size() > 0) {
                    mList.addAll(list);
                    initAdapter();
                    if (isRefreshPullDown) {
                        isRefreshPullDown = false;
                        mHandler.postDelayed(() -> mSrl.setRefreshing(false), 1200);
                    }
                    loadMore();
                } else {
                    LogUtils.d(TAG, "list长度为0");
                }
            } else {
                LogUtils.d(TAG, "result为空");
            }
        } else {
            LogUtils.d(TAG, "code返回值不是200");
        }
    }

    private void initAdapter() {
        if (mAdapter == null) {
            mAdapter = new ImageAdapter(getActivity(),R.layout.rv_item_image_mvp, mList);
            mRv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showFailureData(String data) {
        LogUtils.d(TAG, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMvpPresenter != null) {
            mMvpPresenter.detachView();
        }
    }
}
