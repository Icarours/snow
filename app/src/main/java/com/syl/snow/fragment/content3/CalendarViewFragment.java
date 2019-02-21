package com.syl.snow.fragment.content3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/20.
 *
 * @Describe 日历控件
 * @Called
 */
public class CalendarViewFragment extends BaseFragment {
    @Bind(R.id.cvd)
    CalendarView mCvd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fagment_calendar_view, container, false);
        ButterKnife.bind(this, rootView);
        mCvd.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "-" + (month < 10 ? "0" + month : month) + "-" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
                Toast.makeText(getContext(), date, Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
