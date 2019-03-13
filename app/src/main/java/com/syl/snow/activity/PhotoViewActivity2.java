package com.syl.snow.activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author syl
 * create at 2019/3/13
 * description:
 * 查看图片,
 */
public class PhotoViewActivity2 extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ViewPager)
    ViewPager mViewPager;
    @Bind(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo_view2);
        ButterKnife.bind(this);
    }
}
