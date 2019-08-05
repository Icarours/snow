package com.syl.snow.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.syl.snow.R;
import com.syl.snow.utils.LogUtils;
import com.syl.snow.utils.glide.GlideUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import uk.co.senab.photoview.PhotoView;

/**
 * @author syl
 * create at 2019/8/5
 * description:
 * 图片查看器3,全屏
 *
 * 在Androidmanifest.xml 注册文件中设置全屏
 * <activity android:name=".activity.PhotoViewActivity3"
 *                   android:theme="@style/Theme.AppCompat.NoActionBar">
 */
public class PhotoViewActivity3 extends AppCompatActivity {
    private static final String TAG = PhotoViewActivity3.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置竖屏
        setContentView(R.layout.activity_photo_view3);
        int position = getIntent().getIntExtra("position", 0);
        String photos = getIntent().getStringExtra("photos");
        List<String> list = new ArrayList<>();
        if (!TextUtils.isEmpty(photos)) {
            String[] split = photos.split(",");
            if (split.length > 0) {
                list.addAll(Arrays.asList(split));
            } else {
                LogUtils.d(TAG, "split长度为0");
            }
        } else {
            LogUtils.d(TAG, "photos 为空");
        }
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new SamplePagerAdapter(list, PhotoViewActivity3.this));
        viewPager.setCurrentItem(position);
    }

    static class SamplePagerAdapter extends PagerAdapter {
        private List<String> mList;
        private Activity mActivity;

        public SamplePagerAdapter(List<String> list, Activity activity) {
            mList = list;
            mActivity = activity;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            GlideUtil.loadImage(mActivity, mList.get(position), photoView, R.drawable.mm1);
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}