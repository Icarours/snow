package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/21.
 *
 * @Describe 日期选择Calendar
 * @Called
 */
public class CalendarFragment extends BaseFragment {
    @Bind(R.id.tv)
    TextView mTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, rootView);
        mTv.setMovementMethod(ScrollingMovementMethod.getInstance());

        StringBuffer sb = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        int date = calendar.get(Calendar.DATE);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfWeekInMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int aMpM = calendar.get(Calendar.AM_PM);
        int hour = calendar.get(Calendar.HOUR);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int milliSecond = calendar.get(Calendar.MILLISECOND);
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        sb.append("Calendar中的静态字段---n")
                .append("year=" + year + "\n")
                .append("month=" + month + "\n")
                .append("weekOfYear=" + weekOfYear + "\n")
                .append("weekOfMonth=" + weekOfMonth + "\n")
                .append("date=" + date + "\n")
                .append("dayOfMonth=" + dayOfMonth + "\n")
                .append("dayOfYear=" + dayOfYear + "\n")
                .append("dayOfWeek=" + dayOfWeek + "\n")
                .append("dayOfWeekInMonth=" + dayOfWeekInMonth + "\n")
                .append("aMpM=" + aMpM + "\n")
                .append("hour=" + hour + "\n")
                .append("hourOfDay=" + hourOfDay + "\n")
                .append("minute=" + minute + "\n")
                .append("second=" + second + "\n")
                .append("milliSecond=" + milliSecond + "\n")
                .append("zoneOffset=" + zoneOffset + "\n")
                .append("dstOffset=" + dstOffset + "\n");
        mTv.setText(sb.toString());
        return rootView;
    }
}
