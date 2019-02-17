package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.adpater.ImageAdapter;
import com.syl.snow.adpater.ImageData;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.ImageE;

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
 * Created by Bright on 2019/2/17.
 *
 * @Describe Glide加载网络图片
 * @Called
 */
public class GlideFragment extends BaseFragment {
    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.tv_no_data)
    TextView mTvNoData;
    @Bind(R.id.tv_retry)
    TextView mTvRetry;
    @Bind(R.id.tv_load_end)
    TextView mTvLoadEnd;
    private List<ImageE> mListData = new ArrayList<>();//数据集
    private ImageAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_glide, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        initAdapter();
        return rootView;
    }

    @Override
    public void initData() {
        initList();
    }

    private void initList() {
        for (int i = 0; i < ImageData.IMAGESNET.length; i++) {
            mListData.add(new ImageE(i, ImageData.IMAGESNET[i]));
        }
    }

    private void initAdapter() {
        if (mAdapter == null) {
            mAdapter = new ImageAdapter(R.layout.rv_item_image, mListData, getContext());
            mRv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
