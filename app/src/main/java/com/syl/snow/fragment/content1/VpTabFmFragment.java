package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/17.
 *
 * @Describe ViewPager+TabLayout+Fragment
 * @Called
 */
public class VpTabFmFragment extends BaseFragment {
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.vp)
    ViewPager mVp;
    private List<String> mTitles;
    private List<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.vp_tab_fm, container, false);
        ButterKnife.bind(this, rootView);

        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            mFragments.add(TestFragment.newInstance(mTitles.get(i)));
        }
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < mFragments.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
        }
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles.get(position);
            }
        };
        mVp.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mVp);
        return rootView;
    }

    @Override
    public void initData() {
        mTitles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mTitles.add("title" + i);
        }
    }
}
