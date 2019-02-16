package com.syl.snow.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.activity.Content2Activity;
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
 * @Describe 模块2的标题
 * @Called
 */
public class Content2Fragment extends BaseFragment {
    @Bind(R.id.rv_title2)
    RecyclerView mRvTitle2;
    private List<TitleBean> mList;

    @Override
    public void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            mList.add(new TitleBean(i,"content2 title--" + i, "content2 desc --" + i));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content2, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRvTitle2.setLayoutManager(linearLayoutManager);
        mRvTitle2.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        ContentAdapter adapter = new ContentAdapter(R.layout.rv_title, mList);
        mRvTitle2.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Intent intent = new Intent(getContext(),Content2Activity.class);
            intent.putExtra("title",mList.get(position));
            startActivity(intent);
            Toast.makeText(getContext(), "clicked---"+position, Toast.LENGTH_SHORT).show();
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
