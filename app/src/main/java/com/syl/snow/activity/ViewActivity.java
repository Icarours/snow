package com.syl.snow.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.view.ViewArc;
import com.syl.snow.view.ViewBitmap;
import com.syl.snow.view.ViewBitmap2;
import com.syl.snow.view.ViewCircle;
import com.syl.snow.view.ViewPath;
import com.syl.snow.view.ViewPoint;
import com.syl.snow.view.ViewStar;
import com.syl.snow.view.ViewText;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2019/1/10 21:49
 * desc
 * 自定义View,展示页面
 */
public class ViewActivity extends AppCompatActivity {

    @Bind(R.id.viewCircle)
    ViewCircle mViewCircle;
    @Bind(R.id.tv)
    TextView mTv;
    @Bind(R.id.tv2)
    TextView mTv2;
    @Bind(R.id.view2)
    ViewPoint mView2;
    @Bind(R.id.view3)
    ViewBitmap mView3;
    @Bind(R.id.view4)
    ViewArc mView4;
    @Bind(R.id.view5)
    ViewStar mView5;
    @Bind(R.id.view6)
    ViewPath mView6;
    @Bind(R.id.view7)
    ViewText mView7;
    @Bind(R.id.view8)
    ViewBitmap2 mView8;
    private List<View> mViewList = new ArrayList<>();//view集合


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        int viewType = getIntent().getIntExtra("viewType", 0);

        mViewList.add(mViewCircle);
        mViewList.add(mView2);
        mViewList.add(mView3);
        mViewList.add(mView4);
        mViewList.add(mView5);
        mViewList.add(mView6);
        mViewList.add(mView7);
        mViewList.add(mView8);
        showView(viewType);
        switch (viewType) {
            case 4:
                mTv.setVisibility(View.GONE);
                mTv2.setVisibility(View.GONE);
                break;
            default:
                break;
        }

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        float density = displayMetrics.density;
        int densityDpi = displayMetrics.densityDpi;
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        float scaledDensity = displayMetrics.scaledDensity;
        float xdpi = displayMetrics.xdpi;
        float ydpi = displayMetrics.ydpi;
        StringBuilder sb = new StringBuilder();
        sb.append("不带状态栏\r\n")
                .append("density=" + density + "\r\n")
                .append("densityDpi=" + densityDpi + "\r\n")
                .append("widthPixels=" + widthPixels + "\r\n")
                .append("heightPixels=" + heightPixels + "\r\n")
                .append("scaledDensity=" + scaledDensity + "\r\n")
                .append("xdpi=" + xdpi + "\r\n")
                .append("ydpi=" + ydpi + "\r\n");
        mTv.setText(sb.toString());
        mTv.setMovementMethod(ScrollingMovementMethod.getInstance());

        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics2);
        float density2 = displayMetrics2.density;
        int densityDpi2 = displayMetrics2.densityDpi;
        int widthPixels2 = displayMetrics2.widthPixels;
        int heightPixels2 = displayMetrics2.heightPixels;
        float scaledDensity2 = displayMetrics2.scaledDensity;
        float xdpi2 = displayMetrics2.xdpi;
        float ydpi2 = displayMetrics2.ydpi;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("带状态栏\r\n")
                .append("density=" + density2 + "\r\n")
                .append("densityDpi=" + densityDpi2 + "\r\n")
                .append("widthPixels=" + widthPixels2 + "\r\n")
                .append("heightPixels=" + heightPixels2 + "\r\n")
                .append("scaledDensity=" + scaledDensity2 + "\r\n")
                .append("xdpi=" + xdpi2 + "\r\n")
                .append("ydpi=" + ydpi2 + "\r\n");
        mTv2.setText(sb2.toString());
        mTv2.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    /**
     * 根据viewType,同时也是集合中的索引,控制集合中元素的显隐
     *
     * @param viewType
     */
    private void showView(int viewType) {
        for (int i = 0; i < mViewList.size(); i++) {
            if (viewType == i) {
                mViewList.get(i).setVisibility(View.VISIBLE);
            } else {
                mViewList.get(i).setVisibility(View.GONE);
            }
        }
    }
}
