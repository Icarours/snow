package com.syl.snow.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.komi.slider.SliderConfig;
import com.komi.slider.SliderUtils;
import com.komi.slider.position.SliderPosition;
import com.syl.snow.R;
import com.syl.snow.base.BaseActivity;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends BaseActivity {

    @Bind(R.id.ViewPager)
    ViewPager mViewPager;
    @Bind(R.id.text)
    TextView mText;
    private PagerAdapter pagerAdapter;
    private SliderConfig mConfig;
    private ArrayList<ImageView> mList;
    private int imageIds[] = {R.drawable.mm1, R.drawable.mm2, R.drawable.mm3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);
        initSilder();
        initView();
        initShowphoto();
    }

    private void initShowphoto() {
        //Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        int i = intent.getIntExtra("showphoto", 0);
        setSelect(i);//通过传值获取点击图片位置,从而显示当前图片
    }

    //实现界面上下滑动退出界面效果
    private void initSilder() {
        mConfig = new SliderConfig.Builder()
                .secondaryColor(Color.TRANSPARENT)
                .position(SliderPosition.VERTICAL)  //设置上下滑动
                .edge(false)  //是否允许有滑动边界值,默认是有的true
                .build();
        SliderUtils.attachActivity(this, mConfig);
    }

    private void initView() {
        mList = new ArrayList<ImageView>();
        pagerAdapter = new PagerAdapter() {

            // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;

                //return Integer.MAX_VALUE;    返回一个比较大的值，目的是为了实现无限轮播
            }

            // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，
            // 我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView photoView = new PhotoView(PhotoViewActivity.this);
//                ImageView imageView = new ImageView(PhotoViewActivity.this);
                photoView.setImageResource(imageIds[position % imageIds.length]);

//                //使图片具有放缩功能
//                PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView);
//                mAttacher.update();
                photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float x, float y) {

                    }

                    @Override
                    public void onOutsidePhotoTap() {
                        finish();
                    }
                });
                container.addView(photoView);
                mList.add(photoView);
                return photoView;
            }

            //PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mList.get(position));
            }
        };

        mViewPager.setAdapter(pagerAdapter);
        //设置滑动监听事件
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //滑动到第几张图片的调用的方法，position当前显示图片位置
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setSelect(0);
                        mText.setText("1/3");
                        break;
                    case 1:
                        setSelect(1);
                        mText.setText("2/3");
                        break;
                    case 2:
                        mText.setText("3/3");
                        setSelect(2);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //启动查看图片时，显示你点击图片
    public void setSelect(int i) {
        switch (i) {
            case 0:
                mViewPager.setCurrentItem(0);
                break;
            case 1:
                mViewPager.setCurrentItem(1);
                break;
            case 2:
                mViewPager.setCurrentItem(2);
                break;
        }
    }
}
