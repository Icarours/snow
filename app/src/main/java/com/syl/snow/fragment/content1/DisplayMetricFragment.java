package com.syl.snow.fragment.content1;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/16.
 *
 * @Describe 获取当前手机屏幕宽高
 * @Called
 */
public class DisplayMetricFragment extends BaseFragment {
    @Bind(R.id.tv1)
    TextView mTv1;
    @Bind(R.id.tv2)
    TextView mTv2;
    @Bind(R.id.tv3)
    TextView mTv3;
    @Bind(R.id.tv4)
    TextView mTv4;
    private int height;
    private int width;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.display_metric, container, false);
        ButterKnife.bind(this,rootView);
        getDisplay1();
        getDisplay2();
        getDisplay3();
        getDisplay4();
        return rootView;
    }

    private void getDisplay4() {
        //4、通过类直接取
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.xdpi;
        float height = dm.ydpi;
        mTv4.setText("通过类直接取 DisplayMetrics width==" + width + "--height==" + height);
    }

    private void getDisplay3() {
        //3、获取屏幕的默认分辨率
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        mTv3.setText("获取屏幕的默认分辨率 Display width==" + width + "--height==" + height);
    }

    @NonNull
    private void getDisplay2() {
        //2、通过Resources获取
        DisplayMetrics dm = getResources().getDisplayMetrics();
        height = dm.heightPixels;
        width = dm.widthPixels;
        mTv2.setText("通过Resources获取 DisplayMetrics width==" + width + "--height==" + height);
    }

    private void getDisplay1() {
        //1、通过WindowManager获取
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        mTv1.setText("通过WindowManager获取 DisplayMetrics width==" + width + "--height==" + height);
    }
}
