package com.syl.snow.fragment.content3;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Bright on 2018/12/8.
 *
 * @Describe
 * @Called
 */
public class Text3Fragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText("this is content3");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        return textView;
    }
}
