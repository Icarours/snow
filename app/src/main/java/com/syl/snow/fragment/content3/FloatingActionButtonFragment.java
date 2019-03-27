package com.syl.snow.fragment.content3;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/3/27.
 *
 * @Describe FloatingActionButton
 * @Called
 */
public class FloatingActionButtonFragment extends BaseFragment {
    @Bind(R.id.cbDelay)
    CheckBox mCbDelay;
    @Bind(R.id.miniFab01)
    FloatingActionButton mMiniFab01;
    @Bind(R.id.ll01)
    LinearLayout mLl01;
    @Bind(R.id.miniFab02)
    FloatingActionButton mMiniFab02;
    @Bind(R.id.ll02)
    LinearLayout mLl02;
    @Bind(R.id.miniFab03)
    FloatingActionButton mMiniFab03;
    @Bind(R.id.ll03)
    LinearLayout mLl03;
    @Bind(R.id.miniFab04)
    FloatingActionButton mMiniFab04;
    @Bind(R.id.ll04)
    LinearLayout mLl04;
    @Bind(R.id.miniFab05)
    FloatingActionButton mMiniFab05;
    @Bind(R.id.ll05)
    LinearLayout mLl05;
    @Bind(R.id.miniFab06)
    FloatingActionButton mMiniFab06;
    @Bind(R.id.ll06)
    LinearLayout mLl06;
    @Bind(R.id.rlAddBill)
    RelativeLayout mRlAddBill;
    @Bind(R.id.fab01Add)
    FloatingActionButton mFab01Add;
    private boolean isAdd = false;
    private AnimatorSet addBillTranslate1;
    private AnimatorSet addBillTranslate2;
    private AnimatorSet addBillTranslate3;
    private AnimatorSet addBillTranslate4;
    private AnimatorSet addBillTranslate5;
    private AnimatorSet addBillTranslate6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_floating_action_button, container, false);
        ButterKnife.bind(this,rootView);
        setDefaultValues();
        return rootView;
    }

    private void setDefaultValues() {
        addBillTranslate1 = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.add_bill_anim);
        addBillTranslate2 = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.add_bill_anim);
        addBillTranslate3 = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.add_bill_anim);
        addBillTranslate4 = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.add_bill_anim);
        addBillTranslate5 = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.add_bill_anim);
        addBillTranslate6 = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.add_bill_anim);
    }

    @OnClick({R.id.fab01Add, R.id.miniFab01, R.id.miniFab02, R.id.miniFab03, R.id.miniFab04, R.id.miniFab05, R.id.miniFab06})
    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.fab01Add:
                mFab01Add.setImageResource(isAdd ? R.drawable.icon_more : R.drawable.icon_add);
                isAdd = !isAdd;
                mRlAddBill.setVisibility(isAdd ? View.VISIBLE : View.GONE);
                if (isAdd) {
                    addBillTranslate1.setTarget(mLl01);
                    addBillTranslate1.start();
                    addBillTranslate2.setTarget(mLl02);
                    addBillTranslate2.setStartDelay(mCbDelay.isChecked() ? 150 : 0);
                    addBillTranslate2.start();
                    addBillTranslate3.setTarget(mLl03);
                    addBillTranslate3.setStartDelay(mCbDelay.isChecked() ? 200 : 0);
                    addBillTranslate3.start();
                    addBillTranslate4.setTarget(mLl04);
                    addBillTranslate4.setStartDelay(mCbDelay.isChecked() ? 250 : 0);
                    addBillTranslate4.start();
                    addBillTranslate5.setTarget(mLl05);
                    addBillTranslate5.setStartDelay(mCbDelay.isChecked() ? 300 : 0);
                    addBillTranslate5.start();
                    addBillTranslate6.setTarget(mLl06);
                    addBillTranslate6.setStartDelay(mCbDelay.isChecked() ? 350 : 0);
                    addBillTranslate6.start();
                }
                break;
            case R.id.miniFab01:
                hideFABMenu();
                break;
            case R.id.miniFab02:
                hideFABMenu();
                break;
            case R.id.miniFab03:
                hideFABMenu();
                break;
            case R.id.miniFab04:
                hideFABMenu();
                break;
            case R.id.miniFab05:
                hideFABMenu();
                break;
            case R.id.miniFab06:
                hideFABMenu();
                break;
            default:
                break;
        }
    }

    private void hideFABMenu() {
        mRlAddBill.setVisibility(View.GONE);
        mFab01Add.setImageResource(R.drawable.icon_more);
        isAdd = false;
    }
}
