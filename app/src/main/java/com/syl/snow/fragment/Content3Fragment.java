package com.syl.snow.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.activity.Content3Activity;
import com.syl.snow.adpater.ContentAdapter;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.TitleBean;

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
 * @Describe 模块3的标题
 * @Called
 */
public class Content3Fragment extends BaseFragment {
    @Bind(R.id.rv_title3)
    RecyclerView mRvTitle3;
    private List<TitleBean> mList;

    @Override
    public void initData() {
        mList = new ArrayList<>();
        mList.add(new TitleBean(0, "格式化字符串", "String.format()用法"));
        mList.add(new TitleBean(1, "Android截图", "Android截图并保存图片到本地"));
        mList.add(new TitleBean(2, "jetpack data binding", "data binding"));
        mList.add(new TitleBean(3, "日期选择器", "DatePickerDialog"));
        mList.add(new TitleBean(4, "CalendarView", "日历控件 "));
        mList.add(new TitleBean(5, "自定义日历控件,失败", "使用RecyclerView自定义日历控件"));
        mList.add(new TitleBean(6, "去除ArrayList中的重复对象", "去除ArrayList中的重复对象"));
        mList.add(new TitleBean(7, "MediaPlayer播放网络音乐", "MediaPlayer播放网络音乐"));
        for (int i = 20; i < 100; i++) {
            mList.add(new TitleBean(i, "content3 title--" + i, "content3 desc--" + i));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content3, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRvTitle3.setLayoutManager(linearLayoutManager);
        mRvTitle3.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        ContentAdapter adapter = new ContentAdapter(R.layout.rv_title, mList);
        mRvTitle3.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Intent intent = new Intent(getContext(), Content3Activity.class);
            intent.putExtra("title", mList.get(position));
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
