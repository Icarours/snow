package com.syl.snow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.bean.TitleBean;
import com.syl.snow.fragment.content4.Text4Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2018/12/8 17:58
 * desc
 * 模块4的内容
 */
public class Content4Activity extends AppCompatActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fl_content4)
    FrameLayout mFlContent4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_content4);
        ButterKnife.bind(this);
        initToolBar();
        Intent intent = getIntent();
        TitleBean titleBean = (TitleBean) intent.getSerializableExtra("title");
        mToolbar.setTitle(titleBean.getTitle());
        mToolbar.setSubtitle(titleBean.getDescription());
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        Text4Fragment fragment = new Text4Fragment();
        Bundle args = new Bundle();
        args.putSerializable("title",titleBean);
        fragment.setArguments(args);
        transaction.replace(R.id.fl_content4, fragment);
        transaction.commit();
    }

    private void initToolBar() {
        mToolbar.inflateMenu(R.menu.menu1);
        mToolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item1:
                    Toast.makeText(Content4Activity.this, "item1 was clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item2:
                    Toast.makeText(Content4Activity.this, "item2 was clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item3:
                    Toast.makeText(Content4Activity.this, "item3 was clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        });
        mToolbar.setNavigationOnClickListener(v -> finish());
    }
}
