package com.syl.snow.fragment.content1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syl.snow.R;
import com.syl.snow.activity.VideoViewActivity;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/3/2.
 *
 * @Describe 使用VideoView播放视频文件
 * @Called
 */
public class VideoViewF extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_video_view, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn_local, R.id.btn_net})
    public void onClickView(View view) {
        Intent intent =new Intent(getContext(),VideoViewActivity.class);
        switch (view.getId()) {
            case R.id.btn_local:
                intent.putExtra("path_type",1);
                startActivity(intent);
                break;
            case R.id.btn_net:
                intent.putExtra("path_type",2);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
