package com.syl.snow.fragment.content1.chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.syl.snow.R;
import com.syl.snow.utils.MPChartHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/16.
 *
 * @Describe 折线图(单)
 * @Called
 */
public class LineFragment extends BaseChartFragment {
    @Bind(R.id.line_chart)
    LineChart mLineChart;
    private List<String> xAxisValues = new ArrayList<>();
    private List<Float> yAxisValues = new ArrayList<>();
    private List<String> titles = new ArrayList<>();


    @Override
    public void initData() {
        titles.add("折线图(单)");
        for (int i = 0; i < 8; i++) {
            xAxisValues.add(String.valueOf(i));
            yAxisValues.add((float) (Math.random() * 1000 + 20));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_line_chart, null);
        ButterKnife.bind(this, rootView);

        MPChartHelper.setLineChart(mLineChart, xAxisValues, yAxisValues, titles.get(0), true);
        return rootView;
    }
}
