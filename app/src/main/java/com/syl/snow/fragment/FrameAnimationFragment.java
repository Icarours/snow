package com.syl.snow.fragment;

import android.annotation.TargetApi;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/2/19.
 *
 * @Describe 逐帧动画
 * @Called
 */
public class FrameAnimationFragment extends BaseFragment {
    @Bind(R.id.btn_frame1)
    Button mBtnFrame1;
    @Bind(R.id.btn_frame2)
    Button mBtnFrame2;
    @Bind(R.id.btn_frame3)
    Button mBtnFrame3;
    @Bind(R.id.iv)
    ImageView mIv;
    @Bind(R.id.tv_tips)
    TextView mTvTips;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frame_animation, container, false);
        ButterKnife.bind(this, rootView);
        mTvTips.setText("逐帧动画...从xml动画文件中加载,或者使用Java代码实现");
        mTvTips.setMovementMethod(ScrollingMovementMethod.getInstance());
        return rootView;
    }

    @OnClick({R.id.btn_frame1, R.id.btn_frame2, R.id.btn_frame3,})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_frame1:
                btnFrame1();
                break;
            case R.id.btn_frame2:
                btnFrame2();
                break;
            case R.id.btn_frame3:
                btnFrame3();
                break;
            default:
                break;
        }
    }

    private void btnFrame3() {
        //加载xml文件,设置为ImageView的背景
        mIv.setBackgroundResource(R.drawable.frame_anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) mIv.getBackground();
        // 设置为循环播放
        animationDrawable.setOneShot(false);
        animationDrawable.start();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void btnFrame2() {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        // 为AnimationDrawable添加动画帧
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img00), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img01), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img02), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img03), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img04), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img05), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img06), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img07), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img08), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img09), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img10), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img11), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img12), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img13), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img14), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img15), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img16), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img17), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img18), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img19), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img20), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img21), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img22), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img23), 50);
        animationDrawable.addFrame(
                getResources().getDrawable(R.drawable.img24), 50);
        // 设置为循环播放
        animationDrawable.setOneShot(false);
        mIv.setBackground(animationDrawable);
        animationDrawable.start();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void btnFrame1() {
        //从xml文件中加载资源,设置为ImageView的背景
        AnimationDrawable animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.frame_anim);
        mIv.setBackground(animationDrawable);
        // 设置为循环播放
        animationDrawable.setOneShot(false);
        animationDrawable.start();
    }
}
