package com.syl.snow.fragment.content4.mvp.v;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.view.LoadingDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 */
public class MvpBaseFragment extends BaseFragment implements IMvpBaseView {
    private LoadingDialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mvp_base, container, false);
        mLoadingDialog = new LoadingDialog(getActivity(), R.style.LoadingTheme);
        return rootView;
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.showLoading();
        }
    }

    @Override
    public void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismissLoading();
        }
    }
}
