package com.syl.snow.fragment.content1;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/1/7.
 *
 * @Describe RecyclerView侧滑菜单
 * @Called
 */
public class Rv2Fragment extends BaseFragment {
    private static final String TAG = Rv2Fragment.class.getSimpleName();
    @Bind(R.id.rv)
    SwipeMenuRecyclerView mRv;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int pageNumber = 1;
    private List<WarnMessage> mList = new ArrayList<>();//数据集
    private Rv2Adapter mAdapter;
    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
                SwipeMenuItem addItem = new SwipeMenuItem(getContext()).setBackground(R.drawable.selector_green)
                        .setImage(R.drawable.ic_action_add)
                        .setWidth(width)
                        .setHeight(height);
                swipeLeftMenu.addMenuItem(addItem); // 添加菜单到左侧。

                SwipeMenuItem closeItem = new SwipeMenuItem(getContext()).setBackground(R.drawable.selector_purple)
                        .setImage(R.drawable.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);
                swipeLeftMenu.addMenuItem(closeItem); // 添加菜单到左侧。
            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext()).setBackground(R.drawable.selector_green)
                        .setImage(R.drawable.ic_action_delete)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。

                SwipeMenuItem addItem = new SwipeMenuItem(getContext()).setBackground(R.drawable.selector_purple)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(getContext(), "list第" + position + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
                if (menuPosition == 0) {
                    LogUtils.d(TAG, "delete--" + menuPosition);
                    mAdapter.remove(menuPosition);
                    mAdapter.notifyDataSetChanged();
                } else if (menuPosition == 1) {
                    LogUtils.d(TAG, "add--" + menuPosition);
                }
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(getContext(), "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rv2, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);

        mRv.setSwipeMenuCreator(swipeMenuCreator);
        mRv.setSwipeMenuItemClickListener(mMenuItemClickListener);
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
//                        LogUtils.d(TAG, "result=" + s);
                        BaseApi baseApi = JSONObject.parseObject(s, BaseApi.class);
                        if (baseApi.isOk()) {
                            String apiData = baseApi.getData();
                            if (apiData != null && !"".equals(apiData)) {
                                List<WarnMessage> list = JSONObject.parseArray(apiData, WarnMessage.class);
                                if (list.size() > 0) {
                                    mList.addAll(list);
                                    pageNumber++;
                                    initAdapter();
                                    if (list.size() % Integer.parseInt(Constant.PAGE_SIZE) == 0) {
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
            mAdapter = new Rv2Adapter(R.layout.rv_item2, mList);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mRv.setAdapter(mAdapter);
    }

    class Rv2Adapter extends BaseQuickAdapter<WarnMessage, BaseViewHolder> {
        public Rv2Adapter(int layoutResId, @Nullable List<WarnMessage> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WarnMessage item) {
            helper.setText(R.id.tv_type, item.getWarn_type())
                    .setText(R.id.tv_camera_id, item.getCamera_id())
                    .setText(R.id.tv_camera_name, item.getCamera_name())
                    .setText(R.id.tv_time, item.getCreate_time());
            Glide.with(getActivity())
                    .load(item.getWarn_picture())
                    .into((ImageView) helper.getView(R.id.iv_img));
        }
    }
}
