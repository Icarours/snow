package com.syl.snow.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.syl.xiaomi.MultiActivity;
import com.syl.snow.R;
import com.syl.snow.activity.Content4Activity;
import com.syl.snow.adpater.ContentAdapter;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.TitleBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2018/12/8.
 *
 * @Describe 模块4的标题
 * @Called
 */
public class Content4Fragment extends BaseFragment {
    @Bind(R.id.rv_title4)
    RecyclerView mRvTitle4;
    private List<TitleBean> mList;

    @Override
    public void initData() {
        mList = new ArrayList<>();
        mList.add(new TitleBean(0, "mvc", "mvc简单举例"));
        mList.add(new TitleBean(1, "mvp", "mvp简单举例"));
        mList.add(new TitleBean(2, "多渠道", "不同的渠道引用不同的module"));
        for (int i = 10; i < 60; i++) {
            mList.add(new TitleBean(i, "content4 title--" + i, "content4 desc --" + i));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content4, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRvTitle4.setLayoutManager(linearLayoutManager);
        mRvTitle4.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        ContentAdapter adapter = new ContentAdapter(R.layout.rv_title, mList);
        mRvTitle4.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            stepIntoActivity(position);
        });
        return rootView;
    }

    /**
     * 根据索引判断应该跳转到那个Activity
     *
     * @param position 条目索引
     */
    private void stepIntoActivity(int position) {
        Intent intent;
        switch (position) {
            case 2://多渠道，切换不同渠道的时候居然不能自动导包，要我自己手动改一下，这个新版的Android studio有点不敢恭维
                intent = new Intent(getContext(), MultiActivity.class);
                startActivity(intent);
                break;
            default://默认跳转到Content4Activity
                intent = new Intent(getContext(), Content4Activity.class);
                intent.putExtra("title", mList.get(position));
                Toast.makeText(getContext(), "clicked---" + position, Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
