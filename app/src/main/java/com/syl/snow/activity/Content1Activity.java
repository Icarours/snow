package com.syl.snow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.bean.TitleBean;
import com.syl.snow.fragment.FrameAnimationFragment;
import com.syl.snow.fragment.content1.ArcFragment;
import com.syl.snow.fragment.content1.CalendarFragment;
import com.syl.snow.fragment.content1.CalendarRecyclerViewF;
import com.syl.snow.fragment.content1.CheckBoxRvF;
import com.syl.snow.fragment.content1.Demo1Fragment;
import com.syl.snow.fragment.content1.DialogDemoFragment;
import com.syl.snow.fragment.content1.DisplayMetricFragment;
import com.syl.snow.fragment.content1.DoubleRecyclerViewFragment;
import com.syl.snow.fragment.content1.DrawBoardFragment;
import com.syl.snow.fragment.content1.ExpandableListViewFragment;
import com.syl.snow.fragment.content1.FileFragment;
import com.syl.snow.fragment.content1.GlideFragment;
import com.syl.snow.fragment.content1.Http1Fragment;
import com.syl.snow.fragment.content1.ImageFragment;
import com.syl.snow.fragment.content1.IvCircleFragment;
import com.syl.snow.fragment.content1.JetPack1Fragment;
import com.syl.snow.fragment.content1.LoadImgFragment;
import com.syl.snow.fragment.content1.MPAndroidChartFragment;
import com.syl.snow.fragment.content1.MaterialDesignFragment;
import com.syl.snow.fragment.content1.MediaPlayerF;
import com.syl.snow.fragment.content1.MobileFragment;
import com.syl.snow.fragment.content1.PassValueFragment;
import com.syl.snow.fragment.content1.PhotoViewFragment;
import com.syl.snow.fragment.content1.PhotoViewFragment2;
import com.syl.snow.fragment.content1.PhotoViewFragment3;
import com.syl.snow.fragment.content1.PickViewFragment;
import com.syl.snow.fragment.content1.PopFragment;
import com.syl.snow.fragment.content1.PropertyAnimationFragment;
import com.syl.snow.fragment.content1.RoomFragment;
import com.syl.snow.fragment.content1.Rv1Fragment;
import com.syl.snow.fragment.content1.Rv2Fragment;
import com.syl.snow.fragment.content1.Rv3Fragment;
import com.syl.snow.fragment.content1.Rv4Fragment;
import com.syl.snow.fragment.content1.VideoViewF;
import com.syl.snow.fragment.content1.ViewAnimationFragment;
import com.syl.snow.fragment.content1.ViewFragment;
import com.syl.snow.fragment.content1.VpTabFmFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2018/12/8 17:57
 * desc
 * 模块1的内容
 */
public class Content1Activity extends AppCompatActivity {
    private static final String TAG = Content1Activity.class.getSimpleName();
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fl_content1)
    FrameLayout mFlContent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_content1);
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
                transaction.replace(R.id.fl_content1, new Http1Fragment());
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.fl_content1, new DialogDemoFragment());
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.fl_content1, new ImageFragment());
                transaction.commit();
                break;
            case 3:
                transaction.replace(R.id.fl_content1, new Rv1Fragment());
                transaction.commit();
                break;
            case 4:
                transaction.replace(R.id.fl_content1, new Rv2Fragment());
                transaction.commit();
                break;
            case 5:
                transaction.replace(R.id.fl_content1, new Rv3Fragment());
                transaction.commit();
                break;
            case 6:
                transaction.replace(R.id.fl_content1, new ViewFragment());
                transaction.commit();
                break;
            case 7:
                transaction.replace(R.id.fl_content1, new JetPack1Fragment());
                transaction.commit();
                break;
            case 8:
                transaction.replace(R.id.fl_content1, new RoomFragment());
                transaction.commit();
                break;
            case 9:
                transaction.replace(R.id.fl_content1, new MaterialDesignFragment());
                transaction.commit();
                break;
            case 10:
                PassValueFragment valueFragment = new PassValueFragment();
                transaction.replace(R.id.fl_content1, valueFragment);
                Bundle argsv = new Bundle();
                argsv.putSerializable("title", titleBean);
                argsv.putString("key1", "value1");
                argsv.putString("key2", "value2");
                valueFragment.setArguments(argsv);
                transaction.commit();
                break;
            case 11:
                transaction.replace(R.id.fl_content1, new MobileFragment());
                transaction.commit();
                break;
            case 12:
                transaction.replace(R.id.fl_content1, new DisplayMetricFragment());
                transaction.commit();
                break;
            case 13:
                transaction.replace(R.id.fl_content1, new MPAndroidChartFragment());
                transaction.commit();
                break;
            case 14:
                transaction.replace(R.id.fl_content1, new PopFragment());
                transaction.commit();
                break;
            case 15:
                transaction.replace(R.id.fl_content1, new VpTabFmFragment());
                transaction.commit();
                break;
            case 16:
                transaction.replace(R.id.fl_content1, new ArcFragment());
                transaction.commit();
                break;
            case 17:
                transaction.replace(R.id.fl_content1, new GlideFragment());
                transaction.commit();
                break;
            case 18:
                transaction.replace(R.id.fl_content1, new LoadImgFragment());
                transaction.commit();
                break;
            case 19:
                transaction.replace(R.id.fl_content1, new PickViewFragment());
                transaction.commit();
                break;
            case 20:
                transaction.replace(R.id.fl_content1, new ViewAnimationFragment());
                transaction.commit();
                break;
            case 21:
                transaction.replace(R.id.fl_content1, new PropertyAnimationFragment());
                transaction.commit();
                break;
            case 22:
                transaction.replace(R.id.fl_content1, new FrameAnimationFragment());
                transaction.commit();
                break;
            case 23:
                transaction.replace(R.id.fl_content1, new DrawBoardFragment());
                transaction.commit();
                break;
//            case 24:
//                startActivity(new Intent(getApplicationContext(), JZVideoActivity.class));
//                break;
            case 25:
                transaction.replace(R.id.fl_content1, new CalendarFragment());
                transaction.commit();
                break;
            case 26:
                transaction.replace(R.id.fl_content1, new CalendarRecyclerViewF());
                transaction.commit();
                break;
            case 27:
                transaction.replace(R.id.fl_content1, new CheckBoxRvF());
                transaction.commit();
                break;
            case 28:
                transaction.replace(R.id.fl_content1, new MediaPlayerF());
                transaction.commit();
                break;
            case 29:
                transaction.replace(R.id.fl_content1, new VideoViewF());
                transaction.commit();
                break;
            case 30:
                transaction.replace(R.id.fl_content1, new FileFragment());
                transaction.commit();
                break;
            case 31:
                transaction.replace(R.id.fl_content1, new PhotoViewFragment());
                transaction.commit();
                break;
            case 32:
                transaction.replace(R.id.fl_content1, new PhotoViewFragment2());
                transaction.commit();
                break;
            case 33:
                transaction.replace(R.id.fl_content1, new IvCircleFragment());
                transaction.commit();
                break;
            case 34:
                transaction.replace(R.id.fl_content1, new ExpandableListViewFragment());
                transaction.commit();
                break;
            case 35:
                transaction.replace(R.id.fl_content1, new DoubleRecyclerViewFragment());
                transaction.commit();
                break;
            case 37:
                transaction.replace(R.id.fl_content1, new Rv4Fragment());
                transaction.commit();
                break;
            case 38:
                transaction.replace(R.id.fl_content1, new PhotoViewFragment3());
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
                    Toast.makeText(Content1Activity.this, "item1 was clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item2:
                    Toast.makeText(Content1Activity.this, "item2 was clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item3:
                    Toast.makeText(Content1Activity.this, "item3 was clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        });
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }
}
