package com.syl.baidu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Bright
 * @date 2020/12/6 17:38
 * @describe 多渠道，不同渠道引用不同的Activity，百度渠道
 */
public class MultiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
    }
}