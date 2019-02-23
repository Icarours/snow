package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.DayE;
import com.syl.snow.utils.LogUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/21.
 *
 * @Describe 使用RecyclerView创建类似日历的控件/布局,失败
 * @Called
 */
public class CalendarRecyclerViewF extends BaseFragment {
    private static final String TAG = CalendarRecyclerViewF.class.getSimpleName();
    @Bind(R.id.rv)
    RecyclerView mRv;
    private List<DayE> mListData = new ArrayList<>();//数据集

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        Calendar calendar = null;
        int year = 0;
        int month = 0;
        int date = 0;
        if (bundle != null) {
            int position = bundle.getInt("position", 1);
            calendar = (Calendar) bundle.getSerializable("calendar");
            switch (position) {
                case 0:
                    calendar.add(Calendar.MONTH, -1);
                    break;
                default:
                case 1:
                    break;
                case 2:
                    calendar.add(Calendar.MONTH, 1);
                    break;
            }
        }
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        //周日--周六
        mListData.add(new DayE("周日", "", 11));
        mListData.add(new DayE("周一", "", 11));
        mListData.add(new DayE("周二", "", 11));
        mListData.add(new DayE("周三", "", 11));
        mListData.add(new DayE("周四", "", 11));
        mListData.add(new DayE("周五", "", 11));
        mListData.add(new DayE("周六", "", 11));
        //当月的第一天
        Date currentMonthFirst = new Date(year, month, 1);
        LogUtils.d(TAG, "currentMonthFirst==" + year + "-" + month + "-" + 1);
        //第二月的第一天
        Date secondMonthFirst;
        if (month < 12) {
            secondMonthFirst = new Date(year, month + 1, 1);
            LogUtils.d(TAG, "secondMonthFirst==" + year + "-" + (month + 1) + "-" + 1);
        } else {
            secondMonthFirst = new Date(year + 1, month, 1);
        }
        calendar.setTime(currentMonthFirst);
//        calendar.add(Calendar.DATE, 1);
        date = calendar.get(Calendar.DATE);
        LogUtils.d(TAG, year + "-" + month + "-" + date);
        calendar.add(Calendar.DATE, -1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        LogUtils.d(TAG,"dayOfWeek--1=="+dayOfWeek);
        date = calendar.get(Calendar.DATE);
        //上个月的后几天
        for (int i = 1; i < dayOfWeek; i++) {
            mListData.add(mListData.size(), new DayE((date - dayOfWeek + i + 1) + "", "上月", 12));
        }
        int betweenDays = getBetweenDays(currentMonthFirst, secondMonthFirst);
        //当月
        for (int i = 1; i <= betweenDays; i++) {
            mListData.add(new DayE(i + "", "", 13));
        }
        //下个月,前几天
        Date currentMonthLast = new Date(year, month, betweenDays);
        calendar.setTime(currentMonthLast);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        LogUtils.d(TAG,"dayOfWeek--2=="+dayOfWeek);
        for (int i = 0; i < 7 - dayOfWeek + 1; i++) {
            mListData.add(new DayE((i + 1) + "", "", 12));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fagment_calendar_recycler_view, container, false);
        ButterKnife.bind(this, rootView);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        mRv.setLayoutManager(layoutManager);
        DayAdapter adapter = new DayAdapter(mListData);
        mRv.setAdapter(adapter);
        return rootView;
    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getBetweenDays(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    class DayAdapter extends BaseMultiItemQuickAdapter<DayE, BaseViewHolder> {

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public DayAdapter(List<DayE> data) {
            super(data);
            addItemType(11, R.layout.rv_item_day_11);//周日
            addItemType(12, R.layout.rv_item_day_12);//本月外的
            addItemType(13, R.layout.rv_item_day_13);//本月内的
            addItemType(14, R.layout.rv_item_day_14);//当天
        }

        @Override
        protected void convert(BaseViewHolder helper, DayE item) {
            helper.setText(R.id.tv_day, item.getDay());
        }
    }
}
