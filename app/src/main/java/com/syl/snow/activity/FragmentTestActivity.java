package com.syl.snow.activity;


import android.os.Bundle;

import com.syl.snow.R;
import com.syl.snow.fragment.TestFragment2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
/**
 * author   Bright
 * date     2019/2/17 19:23
 * desc
 * 时间选择器
 */
public class FragmentTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmenttest);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_activity_main, new TestFragment2());
        fragmentTransaction.commitAllowingStateLoss();

    }
}
