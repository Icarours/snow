package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.glide.GlideUtil;
import com.syl.snow.view.CustomRoundAngleImageView;
import com.syl.snow.view.RoundImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/3/18.
 *
 * @Describe
 * @Called
 */
public class IvCircleFragment extends BaseFragment {
    @Bind(R.id.iv1)
    RoundImageView mIv1;
    @Bind(R.id.iv2)
    CustomRoundAngleImageView mIv2;
    @Bind(R.id.iv3)
    ImageView mIv3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_iv_circle, container, false);
        ButterKnife.bind(this, rootView);
        new GlideUtil(getContext()).showImageRound(R.drawable.mm2, mIv3);
        return rootView;
    }
}
