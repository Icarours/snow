package com.syl.snow;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syl.snow.fragment.Content1Fragment;
import com.syl.snow.fragment.Content2Fragment;
import com.syl.snow.fragment.Content3Fragment;
import com.syl.snow.fragment.Content4Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * author   Bright
 * date     2018/12/8 9:37
 * desc
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.fl_content, new Content1Fragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.fl_content, new Content2Fragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    transaction.replace(R.id.fl_content, new Content3Fragment());
                    transaction.commit();
                    return true;
                case R.id.navigation_forth:
                    transaction.replace(R.id.fl_content, new Content4Fragment());
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content, new Content1Fragment());
        transaction.commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
