package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
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
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2018/12/27.
 *
 * @Describe RecyclerView加载更多举例
 * @Called
 */
public class Rv1Fragment extends BaseFragment {
    private static final String TAG = Rv1Fragment.class.getSimpleName();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int pageNumber = 1;
    @Bind(R.id.rv)
    RecyclerView mRv;
    private List<WarnMessage> mList = new ArrayList<>();//数据集
    private RvAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rv1, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return rootView;
    }

    @Override
    public void initData() {
        initList();
    }

    private void initList() {
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
                        LogUtils.d(TAG, "网络请求失败..");
                    }

                    @Override
                    public void onSuccess(String s) {
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
                                } else {
                                    LogUtils.d(TAG, "data 数据集长度为0");
                                }
                            } else {
                                LogUtils.d(TAG, "data 数据集为空");
                            }
                        } else {
                            LogUtils.d(TAG, "code 返回值不为200");
                        }
                    }
                }) {
                });
    }

    private void initAdapter() {
        if (mAdapter == null) {
            //给RecyclerView设置Adapter必须紧接着Adapter创建之后,如果在if语句后面设置,RecyclerView上拉刷新之后会自动跳到RecyclerView顶部
            mAdapter = new RvAdapter(R.layout.rv_item1, mList);
            mRv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
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
