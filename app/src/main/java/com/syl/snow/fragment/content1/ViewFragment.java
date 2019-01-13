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
 * @Describe 自定义View, 入口
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

    @OnClick({R.id.btn_view1, R.id.btn_view2, R.id.btn_view3, R.id.btn_view4, R.id.btn_view5,
            R.id.btn_view6, R.id.btn_view7, R.id.btn_view8})
    public void onViewClick(View view) {
        Intent intent = new Intent(getContext(), ViewActivity.class);
        switch (view.getId()) {
            case R.id.btn_view1:
                intent.putExtra("viewType", 0);
                startActivity(intent);
                break;
            case R.id.btn_view2:
                intent.putExtra("viewType", 1);
                startActivity(intent);
                break;
            case R.id.btn_view3:
                intent.putExtra("viewType", 2);
                startActivity(intent);
                break;
            case R.id.btn_view4:
                intent.putExtra("viewType", 3);
                startActivity(intent);
                break;
            case R.id.btn_view5:
                intent.putExtra("viewType", 4);
                startActivity(intent);
                break;
            case R.id.btn_view6:
                intent.putExtra("viewType", 5);
                startActivity(intent);
                break;
            case R.id.btn_view7:
                intent.putExtra("viewType", 6);
                startActivity(intent);
                break;
            case R.id.btn_view8:
                intent.putExtra("viewType", 7);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
