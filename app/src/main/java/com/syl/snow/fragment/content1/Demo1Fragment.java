package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.TitleBean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2018/12/16.
 *
 * @Describe content1跳转过来后对应的空白Fragment
 * Activity向Fragment传递数据
 * @Called
 */
public class Demo1Fragment extends BaseFragment {
    @Bind(R.id.tv_demo1)
    TextView mTvDemo1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_demo1, container, false);
        ButterKnife.bind(this, rootView);

        Bundle bundle = this.getArguments();
        TitleBean titleBean = (TitleBean) bundle.getSerializable("title");
        mTvDemo1.setText(titleBean.getTitle() + "\r\n" + titleBean.getDescription());

        return rootView;
    }
}
