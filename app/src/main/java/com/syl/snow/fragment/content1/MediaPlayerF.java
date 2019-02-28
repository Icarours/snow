package com.syl.snow.fragment.content1;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.Api;
import com.syl.snow.bean.BaseApi;
import com.syl.snow.bean.MusicE;
import com.syl.snow.config.Constant;
import com.syl.snow.utils.LogUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
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
 * Created by Bright on 2019/2/28.
 *
 * @Describe RecyclerView上拉加载更多, 下拉刷新, 使用MediaPlayer播放音乐文件
 * 条目中播放音乐没有实现
 * @Called
 */
public class MediaPlayerF extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = MediaPlayerF.class.getSimpleName();
    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.srl)
    SwipeRefreshLayout mSrl;
    @Bind(R.id.tv_no_data)
    TextView mTvNoData;
    @Bind(R.id.tv_retry)
    TextView mTvRetry;
    private BaseQuickAdapter mAdapter;
    private int pageNumber = 1;
    private boolean refresh = false;
    private List<MusicE> mListData = new ArrayList<>();//数据集
    private MediaPlayer mMediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_media_player, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mSrl.setOnRefreshListener(this);
        mSrl.setColorSchemeColors(getResources().getColor(R.color.bar), getResources().getColor(R.color.bar1),
                getResources().getColor(R.color.bar2), getResources().getColor(R.color.bar3));
        return rootView;
    }

    @Override
    public void initData() {
        initList();
    }

    private void initList() {
        /**
         *http://cloud.lanlyc.cn/lanlyc_gongdi/ttmusic/musicList?paramJson={"mobile":"15989469069"}
         */
        String url = "http://cloud.lanlyc.cn/new_gongdi/ttmusic/musicList";
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", "15989469069");
        String params = JSONArray.toJSONString(map);
        EasyHttp.post(url)
                .params("paramJson", params)
                .execute(new CallBackProxy<Api<String>, String>(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        LogUtils.d(TAG, "网络请求失败..");
                    }

                    @Override
                    public void onSuccess(String s) {
                        LogUtils.d(TAG, "result=" + s);
                        refresh = false;
                        if (mSrl != null && mSrl.isRefreshing()) {
                            mSrl.setRefreshing(refresh);
                        }
                        BaseApi baseApi = JSONObject.parseObject(s, BaseApi.class);
                        if (baseApi.isOk()) {
                            String apiData = baseApi.getData();
                            if (apiData != null && !"".equals(apiData)) {
                                List<MusicE> list = JSONObject.parseArray(apiData, MusicE.class);
                                if (list.size() > 0) {
                                    mListData.addAll(list);
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
            mAdapter = new MusicAdapter(R.layout.rv_item_music, mListData, getContext());
            mRv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        refresh = true;
        mSrl.setRefreshing(refresh);
        clearListData();
        initList();
    }

    /**
     * 清空数据集
     */
    private void clearListData() {
        if (mListData.size() > 0) {
            mListData.clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    class MusicAdapter extends BaseQuickAdapter<MusicE, BaseViewHolder> {
        private Context mContext;

        public MusicAdapter(int layoutResId, @Nullable List<MusicE> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, MusicE item) {
            helper.setText(R.id.tv_music_title, item.getMusic_name())
                    .addOnClickListener(R.id.iv_play);
            Glide.with(mContext)
                    .load(R.drawable.ic_play_circle_filled_blue_24dp)
                    .into((ImageView) helper.getView(R.id.iv_play));
            CheckBox cb = helper.getView(R.id.cb);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setCheck(isChecked);
                }
            });
            cb.setChecked(item.isCheck());
        }
    }
}
