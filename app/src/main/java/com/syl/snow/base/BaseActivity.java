package com.syl.snow.base;

import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.bean.TitleBean;

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
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        initData();
    }

    public void initData() {

    }

    /**
     * 初始化ToolBar
     * @param toolbar
     * @param titleBean
     */
    public void initToolBar(Toolbar toolbar, TitleBean titleBean) {
        if (titleBean != null) {
            toolbar.setTitle(titleBean.getTitle());
            toolbar.setSubtitle(titleBean.getDescription());
        }
        initToolBar(toolbar);
    }

    /**
     * 初始化ToolBar
     *
     * @param toolbar
     */
    public void initToolBar(Toolbar toolbar) {
        toolbar.inflateMenu(R.menu.menu1);
        toolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                default:
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
