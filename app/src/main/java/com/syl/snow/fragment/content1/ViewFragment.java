package com.syl.snow.fragment.content1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.syl.snow.R;
import com.syl.snow.activity.ViewActivity;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/1/10.
 *
 * @Describe 自定义View,入口
 * @Called
 */
public class ViewFragment extends BaseFragment {
    @Bind(R.id.btn_view1)
    Button mBtnView1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn_view1})
    public void onViewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_view1:
                intent = new Intent(getContext(), ViewActivity.class);
                intent.putExtra("viewType", "view1");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
