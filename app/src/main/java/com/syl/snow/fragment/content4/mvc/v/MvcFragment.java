package com.syl.snow.fragment.content4.mvc.v;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.fragment.content4.mvc.m.HttpRequestModel;
import com.syl.snow.fragment.content4.mvc.m.IMvcRequestCallbackListener;
import com.syl.snow.fragment.content4.mvc.m.WangYiNewsAdapter;
import com.syl.snow.fragment.content4.mvc.m.WangYiNewsApiE;
import com.syl.snow.fragment.content4.mvc.m.WangYiNewsE;
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
 * @Called mvc, Fragment/Activity充当controller和View
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
    private List<WangYiNewsE> mList = new ArrayList<>();
    private WangYiNewsAdapter mAdapter;
    private int index = 1;
    private boolean isRefreshPullDown = false;
    private boolean isRefreshPullUp = false;
    private LinearLayoutManager mLinearLayoutManager;
    private int mLastVisibleItem;
    private int mFirstVisibleItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mvc, container, false);
        ButterKnife.bind(this, rootView);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(mLinearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mSrl.setOnRefreshListener(() -> {
            index = 1;
            isRefreshPullDown = true;
            netRequest();
        });
        mSrl.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
//        initListener();
        return rootView;
    }

    /**
     * 监听RecyclerView滑动
     */
    private void initListener() {
        //给RecyclerView添加滑动监听,在RecyclerView滑动到底部,触发加载更多
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LogUtils.d(TAG, "----" + newState);
                mLastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

                /**
                 * 这种加载更多的方式如果不使用BRVAH的RequestLoadMore,就需要自己写footer,我自己写的不好,没搞定;
                 * 如果使用,只有在快速滑动的时候有效,缓慢滑动不能触发加载更多
                 */
                if ((newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_DRAGGING) &&
                        mLinearLayoutManager.getItemCount() > 0 && mLastVisibleItem + 1 == mList.size()) {
                    mAdapter.setOnLoadMoreListener(() -> {
                        isRefreshPullUp = true;
                        index++;
                        netRequest();
                    }, mRv);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        netRequest();
    }

    /**
     * 网络请求
     */
    private void netRequest() {
        String url = "https://api.apiopen.top/getWangYiNews?count=16&page=" + index;
        LogUtils.d(TAG, url);
        new HttpRequestModel().httpGetRequest(url, new IMvcRequestCallbackListener<String>() {

            @Override
            public void onSuccess(String successData) {
                if (isRefreshPullDown) {
                    mList.clear();
                    isRefreshPullDown = false;
                    mHandler.postDelayed(() -> mSrl.setRefreshing(false), 1200);
                }
                LogUtils.d(TAG, successData);
                parseData(successData);
                loadMore();
            }

            @Override
            public void onFailure(String failureData) {
                LogUtils.d(TAG, failureData);
            }
        });
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        if (mList.size() % 16 == 0) {
            index++;
            mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            netRequest();
                        }
                    }, 1000);
                }
            }, mRv);
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreEnd();
        }
    }

    /**
     * 解析数据
     *
     * @param successData
     */
    private void parseData(String successData) {
        if (!TextUtils.isEmpty(successData)) {
            WangYiNewsApiE wangYiNewsApiE = JSONObject.parseObject(successData, WangYiNewsApiE.class);
            if (wangYiNewsApiE.getCode() == 200) {
                String result = wangYiNewsApiE.getResult();
                if (!TextUtils.isEmpty(result)) {
                    List<WangYiNewsE> list = JSONObject.parseArray(result, WangYiNewsE.class);
                    if (list.size() > 0) {
                        mList.addAll(list);
                        initAdapter();
                    } else {
                        LogUtils.d(TAG, "网络请求返回的数据集长度为0");
                    }
                } else {
                    LogUtils.d(TAG, "result为空");
                }
            } else {
                LogUtils.d(TAG, "网络请求返回code部位200");
            }
        } else {
            LogUtils.d(TAG, "数据为空");
        }
    }

    /**
     * 初始化Adapter
     */
    private void initAdapter() {
        if (mAdapter == null) {
            mAdapter = new WangYiNewsAdapter(getActivity(), R.layout.rv_wang_yi_news, mList);
            mRv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), WangYiNewsActivity.class);
                intent.putExtra("path", mList.get(position).getPath());
                LogUtils.d(TAG, mList.get(position).getPath());
                startActivity(intent);
            }
        });
    }
}
