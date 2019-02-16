package com.syl.snow.fragment.content1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.syl.snow.R;
import com.syl.snow.activity.BottomNavigationActivity;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/2/16.
 *
 * @Describe MaterialDesignFragment
 * @Called
 */
public class MaterialDesignFragment extends BaseFragment {
    @Bind(R.id.btn_bottom_navigation)
    Button mBtnBottomNavigation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_material_design, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn_bottom_navigation})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bottom_navigation:
                startActivity(new Intent(getContext(),BottomNavigationActivity.class));
                break;
            default:
                break;
        }
    }
}
