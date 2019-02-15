package com.syl.snow.fragment.content3;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/2/15.
 *
 * @Describe 日期选择器(只有年月, 没有日)
 * @Called
 */
public class DatePickerDialog2Fragment extends BaseFragment {
    @Bind(R.id.btn_date_picker1)
    Button mBtnDatePicker1;
    @Bind(R.id.tv_date)
    TextView mTvDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_date_picker_dialog, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn_date_picker1})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_date_picker1:
                pickDate1();
                break;
        }
    }

    /**
     * 日期选择器(只有年月, 没有日)
     */
    private void pickDate1() {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                mTvDate.setText(year + "-" + (month < 10 ? "0" + month : month));
            }
        };
        DatePickerDialog dlg = new DatePickerDialog(new ContextThemeWrapper(getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar), dateSetListener, yy, mm, dd) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                LinearLayout mSpinners = (LinearLayout) findViewById(getContext().getResources().getIdentifier("android:id/pickers", null, null));
                if (mSpinners != null) {
                    NumberPicker mMonthSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/month", null, null));
                    NumberPicker mYearSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/year", null, null));
                    mSpinners.removeAllViews();
                    if (mYearSpinner != null) {
                        mSpinners.addView(mYearSpinner);
                    }
                    if (mMonthSpinner != null) {
                        mSpinners.addView(mMonthSpinner);
                    }
                }
                View dayPickerView = findViewById(getContext().getResources().getIdentifier("android:id/day", null, null));
                if (dayPickerView != null) {
                    dayPickerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                super.onDateChanged(view, year, month, day);
                setTitle("请选择考勤月份");
            }
        };
        dlg.setTitle("请选择考勤月份");
        dlg.show();
    }
}
