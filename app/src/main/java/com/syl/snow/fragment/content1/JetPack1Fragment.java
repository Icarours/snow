package com.syl.snow.fragment.content1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.activity.LiveDataActivity;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.AccountModle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/1/13.
 *
 * @Describe JetPack, LIveData和ViewModule
 * LiveData 和ViewModule在Fragment中的应用
 * @Called
 */
public class JetPack1Fragment extends BaseFragment {
    @Bind(R.id.tv1)
    TextView mTv1;
    @Bind(R.id.btn1)
    Button mBtn1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_jet_pack1, container, false);
        ButterKnife.bind(this, rootView);
        AccountModle module = ViewModelProviders.of(getActivity()).get(AccountModle.class);
        return rootView;
    }

    @OnClick({R.id.btn1,R.id.btn2})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                break;
            case R.id.btn2:
                startActivity(new Intent(getContext(),LiveDataActivity.class));
                break;
            default:
                break;
        }
    }
}
