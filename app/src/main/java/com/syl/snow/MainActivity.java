package com.syl.snow;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syl.snow.fragment.Content1Fragment;
import com.syl.snow.fragment.Content2Fragment;
import com.syl.snow.fragment.Content3Fragment;
import com.syl.snow.fragment.Content4Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * author   Bright
 * date     2018/12/8 9:37
 * desc
 * 主界面
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final String[] permissions = {Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS, Manifest.permission.GET_ACCOUNTS};
    private boolean mPermissionContact = false;//读取通讯录的动态权限,这个变量暂时没用到.
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
                default:
                    return false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = findViewById(R.id.toolbar);
        toolBar.setNavigationIcon(null);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content, new Content1Fragment());
        transaction.commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initPermission();
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, permissions[1]) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, permissions[2]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, 1);
                Log.d(TAG, "申请存储卡读写权限");
                mPermissionContact = false;
            } else {
                Log.d(TAG, "已授权");
                mPermissionContact = true;
            }
        } else {
            Log.d(TAG, "当前Android版本不需要申请动态权限");
            mPermissionContact = true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "requestCode==" + requestCode + "--permissions==" + permissions + "---grantResults==" + grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "授权成功");
            mPermissionContact = true;
        } else {
            Log.d(TAG, "用户拒绝了某些权限");
            mPermissionContact = false;
        }
    }
}
