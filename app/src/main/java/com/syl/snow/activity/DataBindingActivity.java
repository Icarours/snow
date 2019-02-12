package com.syl.snow.activity;

import android.os.Bundle;

import com.syl.snow.R;
import com.syl.snow.bean.User2;
import com.syl.snow.databinding.ActivityDataBindingBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * @author syl
 * create at 2019/2/12
 * description:dataBinding 在Activity
 */
public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityDataBindingBinding如果没有生成,就build一下
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        binding.setUser(new User2("lisisi", "刘诗诗"));
    }
}
