package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.view.ArcView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/17.
 *
 * @Describe 自定义饼形统计图
 * @Called
 */
public class ArcFragment extends BaseFragment {
    @Bind(R.id.arcView)
    ArcView mArcView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arc, container, false);
        ButterKnife.bind(this, rootView);
        List<Times> times = new ArrayList<>();
        for (int i = 6; i > 0; i--) {
            Times t = new Times();
            t.hour = i;
            t.text = "Number" + i;
            times.add(t);
        }
        //must set adapter!
        ArcView.ArcViewAdapter myAdapter = new ArcView.ArcViewAdapter<Times>() {
            @Override
            public double getValue(Times times) {
                return times.hour;
            }

            @Override
            public String getText(Times times) {
                return times.text;
            }
        };
        //You'd better sort the data from large to small !!!
        myAdapter.setData(times);//must set adapter's data
        mArcView.setMaxNum(7);//the max piece of sector  optional
        mArcView.setOthersText("Others");//the text of others  optional
        //arcView.setRadius(150);//set radius  optional
        //arcView.setColors(new int[]{getResources().getColor(R.color.green),getResources().getColor(R.color.colorAccent)});//set colors  optional

        return rootView;
    }

    class Times {
        double hour;
        String text;
    }
}
