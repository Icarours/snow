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
        mList.add(new TitleBean(13,"强大的Android自定义统计图表控件", "强大的Android自定义统计图表控件"));
        for (int i = 100; i < 140; i++) {
            mList.add(new TitleBean(i,"title--" + i, "desc--" + i));
        }
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
