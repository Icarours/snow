package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.AccountBean;
import com.syl.snow.bean.AccountModle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/1/13.
 *
 * @Describe
 * //ViewModelProviders.of()中的参数很重要,它是被观察者,本例中就是Fragment所在的Activity
 * @Called
 */
public class ViewModleFragment1 extends BaseFragment {
    @Bind(R.id.tv_content)
    TextView mTvContent;
    @Bind(R.id.btn_fragment1)
    Button mBtnFragment1;
    private AccountModle mModle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_modle1, container, false);
        ButterKnife.bind(this,rootView);
        //ViewModelProviders.of()中的参数很重要,它是被观察者,本例中就是Fragment所在的Activity
        mModle = ViewModelProviders.of(getActivity()).get(AccountModle.class);
        mModle.getAccount().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(AccountBean accountBean) {
                mTvContent.setText(accountBean.toString());
            }
        });
        mBtnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModle.getAccount().postValue(new AccountBean("秦川小将", "182*****008", "这段数据是从Fragment1中Post出来的"));
            }
        });
        return rootView;
    }
}
