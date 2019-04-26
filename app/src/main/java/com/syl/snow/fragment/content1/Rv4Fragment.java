package com.syl.snow.fragment.content1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.TextAdapter;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.TitleBean;
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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/4/26.
 *
 * @Describe RecyclerView侧滑菜单, 多种样式, 有的可以滑动, 有的可以滑动, 还可以有多种样式
 * @Called
 */
public class Rv4Fragment extends BaseFragment {
    private static final String TAG = Rv4Fragment.class.getSimpleName();
    @Bind(R.id.rv)
    SwipeMenuRecyclerView mRv;
    private static final int VIEWTYPE_THREE = 0;
    private static final int VIEWTYPE_TWO_RIGHT = 1;
    private static final int VIEWTYPE_TWO_LEFT = 2;
    private static final int VIEWTYPE_NONE = 3;
    private TextAdapter mAdapter;
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

            // 1. 根据ViewType来决定哪一个item该如何添加菜单。
            // 2. 更多的开发者需要的是根据position，因为不同的ViewType之间不会有缓存优化效果。
            int viewType = position % 4;
            if (viewType == VIEWTYPE_THREE) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mActivity).setBackground(
                        R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("删除")
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。

                SwipeMenuItem closeItem = new SwipeMenuItem(mActivity).setBackground(
                        R.drawable.selector_purple).setImage(R.mipmap.ic_action_close).setWidth(width).setHeight(height);
                swipeRightMenu.addMenuItem(closeItem); // 添加菜单到右侧。

                SwipeMenuItem addItem = new SwipeMenuItem(mActivity).setBackground(
                        R.drawable.selector_green)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            } else if (viewType == VIEWTYPE_TWO_RIGHT) {
                SwipeMenuItem closeItem = new SwipeMenuItem(mActivity).setBackground(
                        R.drawable.selector_purple).setImage(R.mipmap.ic_action_close).setWidth(width).setHeight(height);
                swipeRightMenu.addMenuItem(closeItem); // 添加菜单到右侧。

                SwipeMenuItem addItem = new SwipeMenuItem(mActivity).setBackground(
                        R.drawable.selector_green)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            } else if (viewType == VIEWTYPE_TWO_LEFT) {
                SwipeMenuItem addItem = new SwipeMenuItem(mActivity).setBackground(
                        R.drawable.selector_green).setImage(R.mipmap.ic_action_add).setWidth(width).setHeight(height);
                swipeLeftMenu.addMenuItem(addItem); // 添加菜单到左侧。

                SwipeMenuItem closeItem = new SwipeMenuItem(mActivity).setBackground(
                        R.drawable.selector_red).setText("删除").setTextColor(Color.WHITE).setWidth(width).setHeight(height);
                swipeLeftMenu.addMenuItem(closeItem); // 添加菜单到左侧。
            } else {
                Log.d(TAG, "没有菜单---" + position);
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
                Toast.makeText(mActivity, "list第" + position + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(mActivity, "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };
    private FragmentActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_rv4, container, false);
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
            String str;
            if (i % 4 == 0) {
                str = "我右侧有3个菜单";
            } else if (i % 4 == 1) {
                str = "我右侧有2个菜单";
            } else if (i % 4 == 2) {
                str = "我左侧有2个菜单";
            } else {
                str = "我不能滑动";
            }
            mList.add(new TitleBean(i, "title--" + i, "description--" + i + "---"+str));
        }
    }
}
