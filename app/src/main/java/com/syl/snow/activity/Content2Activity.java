package com.syl.snow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.bean.TitleBean;
import com.syl.snow.fragment.content1.Demo1Fragment;
import com.syl.snow.fragment.content2.StringFormatFragment2;
import com.syl.snow.fragment.content2.VibratorFragment;

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
 * 模块2的内容
 */
public class Content2Activity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fl_content2)
    FrameLayout mFlContent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_content2);
        ButterKnife.bind(this);
        initToolBar();
        Intent intent = getIntent();
        TitleBean titleBean = (TitleBean) intent.getSerializableExtra("title");
        mToolbar.setTitle(titleBean.getTitle());
        mToolbar.setSubtitle(titleBean.getDescription());
        initFragment(titleBean);
    }

    private void initFragment(TitleBean titleBean) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (titleBean.getId()) {
            case 0:
                transaction.replace(R.id.fl_content2, new StringFormatFragment2());
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.fl_content2, new VibratorFragment());
                transaction.commit();
                break;
            default:
                Demo1Fragment fragment = new Demo1Fragment();
                transaction.replace(R.id.fl_content1, fragment);
                Bundle args = new Bundle();
                args.putSerializable("title", titleBean);
                fragment.setArguments(args);
                transaction.commit();
                break;
        }
    }

    private void initToolBar() {
        mToolbar.inflateMenu(R.menu.menu1);
        mToolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item1:
                    Toast.makeText(Content2Activity.this, "item1 was clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item2:
                    Toast.makeText(Content2Activity.this, "item2 was clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item3:
                    Toast.makeText(Content2Activity.this, "item3 was clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        });
        mToolbar.setNavigationOnClickListener(v -> finish());
    }
}
