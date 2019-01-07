package com.syl.snow.fragment.content1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.TextAdapter;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.TitleBean;
import com.syl.snow.utils.LogUtils;
import com.syl.snow.view.RecyclerViewDivider;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

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
 * @Describe RecyclerView侧滑菜单2
 * @Called
 */
public class Rv3Fragment extends BaseFragment {
    private static final String TAG = Rv3Fragment.class.getSimpleName();
    @Bind(R.id.rv)
    SwipeMenuRecyclerView mRv;
    private List<TitleBean> mList = new ArrayList<>();//数据集

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

            //position是item在RecyclerView中的位置
            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(getContext(), "list第" + position + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
                if (menuPosition == 0) {
                    LogUtils.d(TAG, "delete--" + menuPosition);
                    mAdapter.remove(position);
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
    private TextAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rv3, container, false);
        ButterKnife.bind(this, rootView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);

        mRv.setSwipeMenuCreator(swipeMenuCreator);
        mRv.setSwipeMenuItemClickListener(mMenuItemClickListener);

        mAdapter = new TextAdapter(R.layout.rv_text1, mList);
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(new RecyclerViewDivider(getContext(), RecyclerView.VERTICAL));
        return rootView;
    }

    @Override
    public void initData() {
        for (int i = 0; i < 100; i++) {
            mList.add(new TitleBean(i, "title--" + i, "description--" + i));
        }
    }
}
