package com.syl.snow.base;

import android.os.Bundle;
import android.widget.Toast;

import com.syl.snow.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by Bright on 2019/3/6.
 *
 * @Describe Activity的基类
 * @Called
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    public void initData() {

    }

    /**
     * 初始化ToolBar
     * @param toolbar
     */
    public void initToolBar(Toolbar toolbar) {
        toolbar.inflateMenu(R.menu.menu1);
        toolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item1:
                    Toast.makeText(BaseActivity.this, "item1 was clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item2:
                    Toast.makeText(BaseActivity.this, "item2 was clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item3:
                    Toast.makeText(BaseActivity.this, "item3 was clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        });
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}
