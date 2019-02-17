package com.syl.snow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;

import com.syl.snow.R;
import com.syl.snow.fragment.content1.chart.Bar2Fragment;
import com.syl.snow.fragment.content1.chart.BarFragment;
import com.syl.snow.fragment.content1.chart.LineFragment;
import com.syl.snow.fragment.content1.chart.LineFragment2;
import com.syl.snow.fragment.content1.chart.LineFragment3;
import com.syl.snow.fragment.content1.chart.Pie2Fragment;
import com.syl.snow.fragment.content1.chart.Pie3Fragment;
import com.syl.snow.fragment.content1.chart.PieFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2019/2/16 20:01
 * desc
 * MPAndroid统计图表
 */
public class ChartActivity extends AppCompatActivity {

    @Bind(R.id.fl_chart)
    FrameLayout mFlChart;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private int mChart_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        mChart_code = intent.getIntExtra("chart_code", 0);
        mToolbar.setNavigationOnClickListener(v -> finish());
        mToolbar.setTitle(title);
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (mChart_code) {
            case 0:
                transaction.replace(R.id.fl_chart, new LineFragment());
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.fl_chart, new LineFragment2());
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.fl_chart, new LineFragment3());
                transaction.commit();
                break;
            case 3:
                transaction.replace(R.id.fl_chart, new PieFragment());
                transaction.commit();
                break;
            case 4:
                transaction.replace(R.id.fl_chart, new Pie2Fragment());
                transaction.commit();
                break;
            case 5:
                transaction.replace(R.id.fl_chart, new Pie3Fragment());
                transaction.commit();
                break;
            case 6:
                transaction.replace(R.id.fl_chart, new BarFragment());
                transaction.commit();
                break;
            case 7:
                transaction.replace(R.id.fl_chart, new Bar2Fragment());
                transaction.commit();
                break;
            default:
                break;
        }
    }
}
