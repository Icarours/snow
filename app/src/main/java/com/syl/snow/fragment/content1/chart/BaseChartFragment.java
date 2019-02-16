package com.syl.snow.fragment.content1.chart;

import android.graphics.Typeface;
import android.os.Bundle;

import com.syl.snow.base.BaseFragment;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2018/10/6.
 *
 * @Describe MPAndroidChart Fragment的基类
 * @Called
 */
public class BaseChartFragment extends BaseFragment {
    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    protected String[] mParties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    protected Typeface mTfRegular;
    protected Typeface mTfLight;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
    }
    protected float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }
}
