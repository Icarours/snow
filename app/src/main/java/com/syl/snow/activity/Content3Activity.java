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
import com.syl.snow.fragment.content3.ArrayListFragment;
import com.syl.snow.fragment.content3.CalendarView2Fragment;
import com.syl.snow.fragment.content3.CalendarViewFragment;
import com.syl.snow.fragment.content3.DataBindingFragment;
import com.syl.snow.fragment.content3.DatePickerDialog2Fragment;
import com.syl.snow.fragment.content3.FloatingActionButtonFragment;
import com.syl.snow.fragment.content3.MediaPlayerFragment;
import com.syl.snow.fragment.content3.SavePicFragment;
import com.syl.snow.fragment.content3.StringFormatFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author syl
 * create at 2019/2/25
 * description:
 * 模块3的具体内容
 */
public class Content3Activity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fl_content3)
    FrameLayout mFlContent3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_content3);
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
                transaction.replace(R.id.fl_content3, new StringFormatFragment());
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.fl_content3, new SavePicFragment());
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.fl_content3, new DataBindingFragment());
                transaction.commit();
                break;
            case 3:
                transaction.replace(R.id.fl_content3, new DatePickerDialog2Fragment());
                transaction.commit();
                break;
            case 4:
                transaction.replace(R.id.fl_content3, new CalendarViewFragment());
                transaction.commit();
                break;
            case 5:
                transaction.replace(R.id.fl_content3, new CalendarView2Fragment());
                transaction.commit();
                break;
            case 6:
                transaction.replace(R.id.fl_content3, new ArrayListFragment());
                transaction.commit();
                break;
            case 7:
                transaction.replace(R.id.fl_content3, new MediaPlayerFragment());
                transaction.commit();
                break;
            case 8:
                transaction.replace(R.id.fl_content3, new FloatingActionButtonFragment());
                transaction.commit();
                break;
            default:
                Demo1Fragment fragment = new Demo1Fragment();
                transaction.replace(R.id.fl_content3, fragment);
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
                    Toast.makeText(Content3Activity.this, "item1 was clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item2:
                    Toast.makeText(Content3Activity.this, "item2 was clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item3:
                    Toast.makeText(Content3Activity.this, "item3 was clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        });
        mToolbar.setNavigationOnClickListener(v -> finish());
    }
}
