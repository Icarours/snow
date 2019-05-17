package com.syl.snow.fragment.content3;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.LogUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/5/17.
 *
 * @Describe 动画-控件平滑显隐,ObjectAnimator竖直或者水平方向位移
 * @Called
 */
public class AnimationFragment extends BaseFragment {
    private static final String TAG = AnimationFragment.class.getSimpleName();
    @Bind(R.id.ll_target)
    LinearLayout mLlTarget;
    @Bind(R.id.btn_bottom_show)
    Button mBtnBottomShow;
    @Bind(R.id.btn_bottom_hide)
    Button mBtnBottomHide;
    @Bind(R.id.btn_left_show)
    Button mBtnLeftShow;
    @Bind(R.id.btn_left_hide)
    Button mBtnLeftHide;
    @Bind(R.id.btn_right_show)
    Button mBtnRightShow;
    @Bind(R.id.btn_right_hide)
    Button mBtnRightHide;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animation, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn_top_show, R.id.btn_bottom_show, R.id.btn_left_show, R.id.btn_right_show,
            R.id.btn_top_hide, R.id.btn_bottom_hide, R.id.btn_left_hide, R.id.btn_right_hide})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_top_show:
                resetView();
                ObjectAnimator.ofFloat(mLlTarget, "translationY", -mLlTarget.getHeight(), 0)
                        .setDuration(1000)
                        .start();
                break;
            case R.id.btn_bottom_show:
                resetView();
                ObjectAnimator.ofFloat(mLlTarget, "translationY", mLlTarget.getHeight(), 0)
                        .setDuration(1000)
                        .start();
                break;
            case R.id.btn_left_show:
                resetView();
                ObjectAnimator.ofFloat(mLlTarget, "translationX", -mLlTarget.getWidth(), 0)
                        .setDuration(1000)
                        .start();
                break;
            case R.id.btn_right_show:
                resetView();
                ObjectAnimator.ofFloat(mLlTarget, "translationX", mLlTarget.getWidth(), 0)
                        .setDuration(1000)
                        .start();
                break;
            case R.id.btn_top_hide:
                resetView();
                ObjectAnimator.ofFloat(mLlTarget, "translationY", 0, -mLlTarget.getHeight())
                        .setDuration(1000)
                        .start();
                break;
            case R.id.btn_bottom_hide:
                resetView();
                ObjectAnimator.ofFloat(mLlTarget, "translationY", 0, mLlTarget.getHeight())
                        .setDuration(1000)
                        .start();
                break;
            case R.id.btn_left_hide:
                resetView();
                ObjectAnimator.ofFloat(mLlTarget, "translationX", 0, -mLlTarget.getWidth())
                        .setDuration(1000)
                        .start();
                break;
            case R.id.btn_right_hide:
                resetView();
                ObjectAnimator.ofFloat(mLlTarget, "translationX", 0, mLlTarget.getWidth())
                        .setDuration(1000)
                        .start();
                break;
            default:
                break;
        }
    }

    /**
     * 将控件恢复到初始状态
     */
    private void resetView() {
        mLlTarget.setTranslationX(0.0f);
        mLlTarget.setTranslationY(0.0f);
        mLlTarget.setScaleX(1.0f);
        mLlTarget.setScaleY(1.0f);
        mLlTarget.setAlpha(1.0f);
    }

    /**
     * 多次运行会出bug
     */
    private void btn_bottom_hide() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(mLlTarget.getHeight(), 0);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams layoutParams = mLlTarget.getLayoutParams();
                int currentValue = (int) animation.getAnimatedValue();
                layoutParams.height = currentValue;//对mBtnTarget长度进行赋值
                LogUtils.d(TAG, "currentValue==" + currentValue);
                mLlTarget.requestLayout();
            }
        });
        valueAnimator.start();
    }

    /**
     * 多次运行会出bug
     */
    private void btn_bottom_show() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mLlTarget.getHeight())
                .setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams layoutParams = mLlTarget.getLayoutParams();
                int currentValue = (int) animation.getAnimatedValue();
                layoutParams.height = currentValue;//对mBtnTarget长度进行赋值
                LogUtils.d(TAG, "currentValue==" + currentValue);
                mLlTarget.requestLayout();
            }
        });
        valueAnimator.start();
    }
}
