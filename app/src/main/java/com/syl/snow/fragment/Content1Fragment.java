package com.syl.snow.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.activity.Content1Activity;
import com.syl.snow.adpater.ContentAdapter;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.TitleBean;
import com.syl.snow.view.RecyclerViewDivider;

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
 * Created by Bright on 2018/12/8.
 *
 * @Describe 模块1的标题
 * @Called
 */
public class Content1Fragment extends BaseFragment {
    @Bind(R.id.rv_title1)
    RecyclerView mRvTitle1;
    private List<TitleBean> mList;

    @Override
    public void initData() {
        mList = new ArrayList<>();
        mList.add(new TitleBean(0,"httpConnection", "httpConnection网络请求"));
        mList.add(new TitleBean(1,"dialog", "常用对话框"));
        mList.add(new TitleBean(2,"加载图片", "压缩图片,加载大图片"));
        mList.add(new TitleBean(3,"RecyclerView", "RecyclerView加载更多举例"));
        mList.add(new TitleBean(4,"RecyclerView", "RecyclerView侧滑菜单"));
        mList.add(new TitleBean(5,"RecyclerView", "RecyclerView侧滑菜单2"));
        mList.add(new TitleBean(6,"自定义View", "自定义View"));
        mList.add(new TitleBean(7,"Android Jetpack1", "ViewModule和LiveData"));
        mList.add(new TitleBean(8,"Android Jetpack2", "Room数据库,从数据库加载数据RecyclerView"));
        mList.add(new TitleBean(9,"material design", "material design控件"));
        mList.add(new TitleBean(10,"Fragment传值", "Activity传值给Fragment1"));
        mList.add(new TitleBean(11,"获取当前手机的参数", "获取当前手机的参数"));
        mList.add(new TitleBean(12,"获取当前手机屏幕宽高", "屏幕宽高"));
        mList.add(new TitleBean(13,"Android自定义统计图表控件", "强大的Android自定义统计图表控件"));
        mList.add(new TitleBean(14,"弹出式菜单和ToolBar", "弹出式菜单和ToolBar"));
        mList.add(new TitleBean(15,"ViewPager+TabLayout+Fragment", "ViewPager+TabLayout+Fragment"));
        mList.add(new TitleBean(16,"自定义饼形统计图", "自定义饼形统计图"));
        mList.add(new TitleBean(17,"Glide加载网络图片", "Glide加载网络图片"));
        mList.add(new TitleBean(18,"加载网络图片", "加载网络图片"));
        mList.add(new TitleBean(19,"滚轮选择器", "滚轮选择器"));
        mList.add(new TitleBean(20,"View动画", "View动画"));
        mList.add(new TitleBean(21,"属性动画", "PropertyAnimation"));
        mList.add(new TitleBean(22,"逐帧动画", "FrameAnimation"));
        mList.add(new TitleBean(23,"简易画板", "简易画板"));
        mList.add(new TitleBean(24,"饺子播放器", "饺子播放器"));
        mList.add(new TitleBean(25,"日期选择器", "Calendar"));
        mList.add(new TitleBean(26,"CalendarRecyclerView,失败", "CalendarRecyclerViewF,失败"));
        mList.add(new TitleBean(27,"RecyclerView和复选框", "RecyclerView和复选框,记录和回显"));
        mList.add(new TitleBean(28,"RecyclerView和MediaPlayer", "RecyclerView上拉加载更多,下拉刷新,使用MediaPlayer播放音乐文件"));
        mList.add(new TitleBean(29,"VideoView", "使用VideoView播放视频文件"));
        mList.add(new TitleBean(30,"常用的文件目录", "Android中的常用的文件目录"));
        mList.add(new TitleBean(31,"图片查看器", "Android图片查看器"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content1, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRvTitle1.setLayoutManager(linearLayoutManager);
        RecyclerViewDivider itemDecoration = new RecyclerViewDivider(getContext(), DividerItemDecoration.VERTICAL, 1, 0xffff0000);
        mRvTitle1.addItemDecoration(itemDecoration);

        ContentAdapter adapter = new ContentAdapter(R.layout.rv_title, mList);
        mRvTitle1.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Intent intent = new Intent(getContext(),Content1Activity.class);
            intent.putExtra("title",mList.get(position));
            startActivity(intent);
            Toast.makeText(getContext(), "clicked---" + position, Toast.LENGTH_SHORT).show();
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
