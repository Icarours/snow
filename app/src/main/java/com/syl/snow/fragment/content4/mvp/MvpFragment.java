package com.syl.snow.fragment.content4.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/10/5.
 *
 * @Describe
 * @Called mvp
 */
public class MvpFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //必须使用第三个参数是attachToRoot的构造方法,使用二参构造方法会crash
        View rootView = inflater.inflate(R.layout.fragment_mvp, container, false);
        return rootView;
    }
}
