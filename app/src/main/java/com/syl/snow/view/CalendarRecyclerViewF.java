package com.syl.snow.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.DayE;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/21.
 *
 * @Describe 使用RecyclerView创建类似日历的控件/布局
 * @Called
 */
public class CalendarRecyclerViewF extends BaseFragment {
    @Bind(R.id.rv)
    RecyclerView mRv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fagment_calendar_recycler_view, container, false);
        ButterKnife.bind(this, rootView);
        Bundle bundle = getArguments();
        int position = bundle.getInt("position", 1);
        Calendar calendar = (Calendar) bundle.getSerializable("calendar");
        int year;
        int month;
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

        return rootView;
    }

    class DayApdater extends BaseMultiItemQuickAdapter<DayE, BaseViewHolder> {

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public DayApdater(List<DayE> data) {
            super(data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DayE item) {

        }
    }
}
