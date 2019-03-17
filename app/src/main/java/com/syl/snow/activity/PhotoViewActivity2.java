package com.syl.snow.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.komi.slider.SliderConfig;
import com.komi.slider.SliderUtils;
import com.komi.slider.position.SliderPosition;
import com.syl.snow.R;
import com.syl.snow.base.BaseActivity;
import com.syl.snow.bean.TitleBean;
import com.syl.snow.bean.WarnMessageE;
import com.syl.snow.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @author syl
 * create at 2019/3/13
 * description:
 * 查看图片2,
 */
public class PhotoViewActivity2 extends BaseActivity {
    private static final String TAG = PhotoViewActivity2.class.getSimpleName();
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ViewPager)
    ViewPager mViewPager;
    @Bind(R.id.tv)
    TextView mTv;
    private List<WarnMessageE> mListData = new ArrayList<>();//数据集
    private ArrayList<ImageView> mList = new ArrayList<>();
    private boolean isFirst = true;//是否第一次进入页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo_view2);
        ButterKnife.bind(this);

        initToolBar(mToolbar);
        TitleBean titleBean = (TitleBean) getIntent().getSerializableExtra("title");
        mToolbar.setTitle(titleBean.getTitle());
        mToolbar.setSubtitle(titleBean.getDescription());
        int position = getIntent().getIntExtra("position", 0);
//        initSlider();
        initAdapter();
        mViewPager.setCurrentItem(position);
        mTv.setText(++position + "/" + mListData.size());
    }

    @Override
    public void initData() {
        ArrayList<WarnMessageE> photo_list = getIntent().getParcelableArrayListExtra("photo_list");
        if (photo_list != null && photo_list.size() > 0) {
            mListData.addAll(photo_list);
            for (int i = 0; i < mListData.size(); i++) {
                PhotoView photoView = new PhotoView(PhotoViewActivity2.this);
                String ivPath = mListData.get(i).getWarn_picture();
                RequestOptions options = new RequestOptions();
                options.error(R.drawable.mm2);
                options.error(R.drawable.mm3);
                options.placeholder(R.drawable.mm4);
                Glide.with(PhotoViewActivity2.this)
                        .load(ivPath)
                        .apply(options)
                        .into(photoView);
                photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float x, float y) {

                    }

                    @Override
                    public void onOutsidePhotoTap() {
                        finish();
                    }
                });
                mList.add(photoView);
            }
        }
    }

    /**
     * 实现界面上下滑动退出界面效果
     */
    private void initSlider() {
        SliderConfig config = new SliderConfig.Builder()
                .secondaryColor(Color.TRANSPARENT)
                .position(SliderPosition.VERTICAL)  //设置上下滑动
                .edge(false)  //是否允许有滑动边界值,默认是有的true
                .build();
        SliderUtils.attachActivity(this, config);
    }

    private void initAdapter() {
        PagerAdapter pagerAdapter = new PagerAdapter() {
            // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;//返回一个比较大的值，目的是为了实现无限轮播
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
                int position1 = getIntent().getIntExtra("position", 0);
                if (0 != position1 && isFirst) {
                    isFirst = !isFirst;
                    position = position1;
                }
                int index = position % mList.size();
                ImageView view = mList.get(index);
                container.addView(view);
                return view;
            }

            //PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                int index = position % mList.size();
                container.removeView(mList.get(index));
            }
        };

        mViewPager.setAdapter(pagerAdapter);
        //设置滑动监听事件
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogUtils.d(TAG, "onPageScrolled()----position==" + position + "--positionOffset==" + positionOffset + "--positionOffsetPixels==" + positionOffsetPixels);
            }

            //滑动到第几张图片的调用的方法，position当前显示图片位置
            @Override
            public void onPageSelected(int position) {
                LogUtils.d(TAG, "onPageSelected()--position==" + position);
                mTv.setText((position % mList.size() + 1) + "/" + mList.size());
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
