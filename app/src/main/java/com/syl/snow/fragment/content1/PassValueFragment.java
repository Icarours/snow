package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/16.
 *
 * @Describe
 * Activity传值给Fragment1
 * @Called
 */
public class PassValueFragment extends BaseFragment {
    @Bind(R.id.tv1)
    TextView mTv1;
    @Bind(R.id.tv2)
    TextView mTv2;
    @Bind(R.id.tv3)
    TextView mTv3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pass_value, container, false);
        ButterKnife.bind(this,rootView);
        /**
         * 注意:savedInstanceState和我们自己传值的bundle不是同一个bundle
         */
        Bundle bundle = this.getArguments();
        Serializable title = bundle.getSerializable("title");
        String key1 = bundle.getString("key1");
        String key2 = bundle.getString("key2");
        mTv1.setText(title.toString());
        mTv2.setText(key1);
        mTv3.setText(key2);
        return rootView;
    }
}
