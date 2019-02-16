package com.syl.snow.fragment.content1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.activity.ChartActivity;
import com.syl.snow.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/16.
 *
 * @Describe 强大的Android自定义统计图表控件
 * @Called
 */
public class MPAndroidChartFragment extends BaseFragment {
    @Bind(R.id.rv_chart)
    RecyclerView mRvChart;
    private List<String> mListTitle = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mp_android_chart, container, false);
        ButterKnife.bind(this,rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        mRvChart.setLayoutManager(linearLayoutManager);
        ChartAdapter chartAdapter = new ChartAdapter(R.layout.rv_item_single_text, mListTitle);
        mRvChart.setAdapter(chartAdapter);
        chartAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goToChartActivity(position);
            }
        });
        return rootView;
    }

    @Override
    public void initData() {
        mListTitle.add("折线图(单)");
        mListTitle.add("折线图(单)2");
        mListTitle.add("折线图(多)3");
        mListTitle.add("环形图");
        mListTitle.add("环形图(带引线)");
        mListTitle.add("环形图3");
        mListTitle.add("柱形图");
        mListTitle.add("柱形图(多条)");
    }

    /**
     * 跳转到具体展示统计图的界面
     *
     * @param position
     */
    private void goToChartActivity(int position) {
        Intent intent = new Intent(getActivity(), ChartActivity.class);
        intent.putExtra("title", mListTitle.get(position));
        intent.putExtra("chart_code", position);
        startActivity(intent);
    }

    class ChartAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public ChartAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv, item);
        }
    }
}
