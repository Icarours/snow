package com.syl.snow.fragment.content3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.view.CalendarRecyclerViewF;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/21.
 *
 * @Describe 使用RecyclerView自定义日历控件
 * @Called
 */
public class CalendarView2Fragment extends BaseFragment {
    private static final String TAG = CalendarView2Fragment.class.getSimpleName();
    @Bind(R.id.iv_left)
    ImageView mIvLeft;
    @Bind(R.id.tv_month)
    TextView mTvMonth;
    @Bind(R.id.iv_right)
    ImageView mIvRight;
    @Bind(R.id.vp)
    ViewPager mVp;
    private List<String> mTitles;
    private List<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar_view2, container, false);
        ButterKnife.bind(this, rootView);
        mFragments = new ArrayList<>();

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
        return rootView;
    }

    @Override
    public void initData() {
        Calendar calendar = Calendar.getInstance();
        CalendarRecyclerViewF currentF = new CalendarRecyclerViewF();
        Bundle bundle = new Bundle();
        bundle.putSerializable("calendar",calendar);
        bundle.putInt("position",1);
        currentF.setArguments(bundle);

        CalendarRecyclerViewF leftF = new CalendarRecyclerViewF();
        bundle.putInt("position",2);
        leftF.setArguments(bundle);

        CalendarRecyclerViewF rightF = new CalendarRecyclerViewF();
        bundle.putInt("position",3);
        rightF.setArguments(bundle);

        mFragments.add(currentF);
        mFragments.add(leftF);
        mFragments.add(rightF);
    }
}
