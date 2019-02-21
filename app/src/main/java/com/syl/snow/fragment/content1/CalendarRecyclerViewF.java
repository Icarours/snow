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
    @Bind(R.id.rv)
    RecyclerView mRv;
    private List<DayE> mListData = new ArrayList<>();//数据集

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        Calendar calendar = null;
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
        //周日--周六
        mListData.add(new DayE("周日", "", 11));
        mListData.add(new DayE("周一", "", 11));
        mListData.add(new DayE("周二", "", 11));
        mListData.add(new DayE("周三", "", 11));
        mListData.add(new DayE("周四", "", 11));
        mListData.add(new DayE("周五", "", 11));
        mListData.add(new DayE("周六", "", 11));
        calendar.add(Calendar.MONTH, -1);
        int date = calendar.get(Calendar.DATE);
        calendar.add(Calendar.DATE, -date);//当月1号
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //当月的上一月的后几天
        for (int i = 0; i < dayOfWeek; i++) {
            calendar.add(Calendar.DATE, -dayOfWeek + i);
            mListData.add(new DayE(calendar.get(Calendar.DATE) + "", "", 12));
        }
        //当月
        calendar.add(Calendar.MONTH, 1);
        int days = getBetweenDays(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 0),
                new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 0));
        for (int i = 1; i <= days; i++) {
            mListData.add(new DayE(i + "", "", 13));
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
