package com.syl.snow.fragment.content3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.syl.snow.R;
import com.syl.snow.activity.DataBindingActivity;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.User2;
import com.syl.snow.databinding.FragmentDataBindingBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/1/29.
 *
 * @Describe jetpack data binding
 * dataBinding 在Fragment
 * @Called
 */
public class DataBindingFragment extends BaseFragment {
    @Bind(R.id.btn1)
    Button mBtn1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_data_binding, container, false);
        //FragmentDataBindingBinding如果没有生成,就build一下
        FragmentDataBindingBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_data_binding, container, false);
        User2 user2 = new User2("张三", "张三222");
        dataBinding.setUser(user2);
        View rootView = dataBinding.getRoot();
        ButterKnife.bind(this, rootView);
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DataBindingActivity.class));
            }
        });
        return rootView;
    }
}
