package com.syl.snow.activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.TitleBean;
import com.syl.snow.fragment.ExecutorFragment;
import com.syl.snow.fragment.content1.Demo1Fragment;
import com.syl.snow.fragment.content2.ContactFragment;
import com.syl.snow.fragment.content2.NotificationFragment;
import com.syl.snow.fragment.content2.StringFormatFragment2;
import com.syl.snow.fragment.content2.VibratorFragment;
import com.syl.snow.fragment.content2.WifiFragment;

import java.util.Objects;

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
    private static final String TAG = Content2Activity.class.getSimpleName();
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
        TitleBean titleBean = (TitleBean) getIntent().getSerializableExtra("title");
        mToolbar.setTitle(Objects.requireNonNull(titleBean).getTitle());
        mToolbar.setSubtitle(titleBean.getDescription());
        initFragment(titleBean);
    }

    private void initFragment(TitleBean titleBean) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        BaseFragment baseFragment;
        switch (titleBean.getId()) {
            case 0:
                baseFragment = new StringFormatFragment2();
                break;
            case 1:
                baseFragment = new VibratorFragment();
                break;
            case 2:
                baseFragment = new NotificationFragment();
                break;
            case 4:
                baseFragment = new ExecutorFragment();
                break;
            case 5:
                baseFragment = new ContactFragment();
                break;
            case 6:
                baseFragment = new WifiFragment();
                break;
            default:
                baseFragment = new Demo1Fragment();
                Bundle args = new Bundle();
                args.putSerializable("title", titleBean);
                baseFragment.setArguments(args);
                break;
        }
        transaction.replace(R.id.fl_content2, baseFragment);
        transaction.commit();
    }

    private void initToolBar() {
        mToolbar.inflateMenu(R.menu.menu1);
        mToolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                default:
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
