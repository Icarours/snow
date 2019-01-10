package com.syl.snow.activity;

import android.os.Bundle;

import com.syl.snow.R;

import androidx.appcompat.app.AppCompatActivity;
/**
 * author   Bright
 * date     2019/1/10 21:49
 * desc
 * 自定义View,展示页面
 */
public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }
}
