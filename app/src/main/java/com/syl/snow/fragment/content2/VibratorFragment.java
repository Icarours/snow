package com.syl.snow.fragment.content2;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by Bright on 2019/4/14.
 *
 * @Describe Android手机振动器Vibrator,Spinner下拉选择框
 * @Called
 */
public class VibratorFragment extends BaseFragment {
    @Bind(R.id.tv_duration)
    TextView mTvDuration;
    @Bind(R.id.sp_duration)
    Spinner mSpDuration;
    @Bind(R.id.btn_start)
    Button mBtnStart;
    @Bind(R.id.tv_vibrator)
    TextView mTvVibrator;
    private List<Integer> duration = new ArrayList<>();
    private int interval = 0;
    private List<String> desc = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vibrator, container, false);
        ButterKnife.bind(this, rootView);

        desc.add("0.5秒");
        desc.add("1秒");
        desc.add("2秒");
        desc.add("3秒");
        desc.add("4秒");
        desc.add("5秒");

        duration.add(500);
        duration.add(1000);
        duration.add(2000);
        duration.add(3000);
        duration.add(4000);
        duration.add(5000);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(interval);
                mTvVibrator.setText(DateUtil.getNowTime() + "手机震动了" + (interval / 1000.0f) + "秒");
            }
        });

        //简单的string数组适配器：样式res，数组
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, desc);
        //下拉的样式res
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        mSpDuration.setAdapter(spinnerAdapter);

        mSpDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                interval = duration.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                interval = duration.get(0);
            }
        });
        return rootView;
    }
}
