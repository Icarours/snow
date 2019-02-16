package com.syl.snow.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syl.snow.R;
import com.syl.snow.fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2019/2/16 11:45
 * desc
 * 底部导航栏
 */
public class BottomNavigationActivity extends AppCompatActivity {

    @Bind(R.id.bnv)
    BottomNavigationView mBnv;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        ButterKnife.bind(this);
        mBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_tab1:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.item_tab2:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.item_tab3:
                        mViewPager.setCurrentItem(2);
                        return true;
                    case R.id.item_tab4:
                        mViewPager.setCurrentItem(3);
                        return true;
                    case R.id.item_tab5:
                        mViewPager.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        });
        List<Fragment> list = new ArrayList<>();
        list.add(TestFragment.newInstance("消息"));
        list.add(TestFragment.newInstance("联系人"));
        list.add(TestFragment.newInstance("办公"));
        list.add(TestFragment.newInstance("应用"));
        list.add(TestFragment.newInstance("我的"));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.setList(list);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBnv.getMenu().getItem(0).setChecked(true);
                MenuItem item = mBnv.getMenu().getItem(position);
                item.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        public void setList(List<Fragment> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
        }
    }
}
