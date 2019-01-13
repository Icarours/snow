package com.syl.snow.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.bean.AccountBean;
import com.syl.snow.bean.AccountModle;
import com.syl.snow.fragment.content1.ViewModleFragment1;
import com.syl.snow.fragment.content1.ViewModleFragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2019/1/13 15:48
 * desc
 * LiveData 和ViewModule在Activity中的应用
 */
public class LiveDataActivity extends AppCompatActivity {

    @Bind(R.id.tv_content)
    TextView mTvContent;
    @Bind(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        ButterKnife.bind(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl1,new ViewModleFragment1());
        transaction.replace(R.id.fl2,new ViewModleFragment2());
        transaction.commit();
        //初始化ViewModule
        AccountModle modle = ViewModelProviders.of(this).get(AccountModle.class);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modle.getAccount().postValue(new AccountBean("张三","15989469066","https://www.baidu.com"));
            }
        });
        modle.getAccount().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(AccountBean accountBean) {
                mTvContent.setText(accountBean.toString());
            }
        });
    }
}
