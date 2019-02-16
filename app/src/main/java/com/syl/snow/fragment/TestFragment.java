package com.syl.snow.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/**
 * author   Bright
 * date     2019/2/16 18:10
 * desc
 * Activity传值到Fragment2
 */
public class TestFragment extends Fragment {

    public static TestFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_test, container, false);
        TextView tv = RootView.findViewById(R.id.tv);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.get("name").toString();
            tv.setText(name);
        }
        return RootView;
    }
}
