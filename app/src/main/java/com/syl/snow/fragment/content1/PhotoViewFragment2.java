package com.syl.snow.fragment.content1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.activity.PhotoViewActivity2;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.Api;
import com.syl.snow.bean.BaseApi;
import com.syl.snow.bean.Params;
import com.syl.snow.bean.WarnMessage;
import com.syl.snow.config.Constant;
import com.syl.snow.utils.LogUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

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
 * Created by Bright on 2019/3/13.
 *
 * @Describe Android图片查看器2
 * @Called
 */
public class PhotoViewFragment2 extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = PhotoViewFragment2.class.getSimpleName();
    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.srl)
    SwipeRefreshLayout mSrl;
    @Bind(R.id.tv_no_data)
    TextView mTvNoData;
    @Bind(R.id.tv_retry)
    TextView mTvRetry;
    private int pageNumber = 1;
    private List<WarnMessage> mList = new ArrayList<>();//数据集
    private RvAdapter mAdapter;
    private boolean isRefresh = false;//是否是刷新

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo_view2, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mSrl.setColorSchemeResources(R.color.colorAccent);
        mSrl.setOnRefreshListener(this);
        initList();
        return rootView;
    }

    private void initList() {
        mTvNoData.setVisibility(View.GONE);
        mTvRetry.setVisibility(View.GONE);
        showDialog();
        /**
         * http://cloud.lanlyc.cn/new_gongdi/warnMessage/getWarnMessageList?paramJson={"pageNumber":"1","pageSize":"16"}
         */
        String url = "http://cloud.lanlyc.cn/new_gongdi/warnMessage/getWarnMessageList";
        Params params = new Params(pageNumber + "", Constant.PAGE_SIZE);
        String string = JSONObject.toJSONString(params);
        EasyHttp.post(url)
                .params("paramJson", string)
                .execute(new CallBackProxy<Api<String>, String>(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        hideDialog();
                        LogUtils.d(TAG, "网络请求失败..");
                        mTvRetry.setVisibility(View.VISIBLE);
                        mTvRetry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                clearList();
                                initList();
                            }
                        });
                    }

                    @Override
                    public void onSuccess(String s) {
                        hideDialog();
                        LogUtils.d(TAG, "result=" + s);
                        BaseApi baseApi = JSONObject.parseObject(s, BaseApi.class);
                        if (baseApi.isOk()) {
                            String apiData = baseApi.getData();
                            if (apiData != null && !"".equals(apiData)) {
                                List<WarnMessage> list = JSONObject.parseArray(apiData, WarnMessage.class);
                                if (list.size() > 0) {
                                    mList.addAll(list);
                                    initAdapter();
                                    if (list.size() % Integer.parseInt(Constant.PAGE_SIZE) == 0) {
                                        pageNumber++;
                                        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                            @Override
                                            public void onLoadMoreRequested() {
                                                mHandler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        initList();
                                                    }
                                                }, 1000);
                                            }
                                        }, mRv);
                                        mAdapter.loadMoreComplete();
                                    } else {
                                        if (pageNumber > 1)
                                            mAdapter.loadMoreEnd();
                                    }
                                    if (isRefresh) {//如果是刷新进来的
                                        isRefresh = false;
                                        mAdapter.notifyDataSetChanged();
                                        mSrl.setRefreshing(false);
                                    }
                                } else {
                                    LogUtils.d(TAG, "data 数据集长度为0");
                                }
                                if (mList.size() > 0) {
                                    mTvNoData.setVisibility(View.GONE);
                                } else {
                                    mTvNoData.setVisibility(View.VISIBLE);
                                }
                            } else {
                                LogUtils.d(TAG, "data 数据集为空");
                            }
                        } else {
                            LogUtils.d(TAG, "code 返回值不为200");
                            mTvNoData.setVisibility(View.VISIBLE);
                            mTvNoData.setText(baseApi.getMessage());
                        }
                    }
                }) {
                });
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        clearList();
        initList();
    }

    /**
     * 清空数据集,并刷新RecycleView
     */
    private void clearList() {
        if (mList.size() > 0) {
            mList.clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initAdapter() {
        if (mAdapter == null) {
            //给RecyclerView设置Adapter必须紧接着Adapter创建之后,如果在if语句后面设置,RecyclerView上拉刷新之后会自动跳到RecyclerView顶部
            mAdapter = new RvAdapter(R.layout.rv_item1, mList);
            mRv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), PhotoViewActivity2.class);
                intent.putParcelableArrayListExtra("photo_list", (ArrayList<? extends Parcelable>) mList);
                startActivity(intent);
            }
        });
    }

    class RvAdapter extends BaseQuickAdapter<WarnMessage, BaseViewHolder> {
        public RvAdapter(int layoutResId, @Nullable List<WarnMessage> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WarnMessage item) {
            helper.setText(R.id.tv_type, item.getWarn_type())
                    .setText(R.id.tv_time, item.getCreate_time());
            Glide.with(getActivity())
                    .load(item.getWarn_picture())
                    .into((ImageView) helper.getView(R.id.iv_img));
        }
    }

}
