package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/17.
 *
 * @Describe 加载网络图片
 * @Called
 */
public class LoadImgFragment extends BaseFragment {
    @Bind(R.id.btn_glide)
    Button mBtnGlide;
    @Bind(R.id.btn_picassoo)
    Button mBtnPicassoo;
    @Bind(R.id.btn_fresco)
    Button mBtnFresco;
    @Bind(R.id.btn_uil)
    Button mBtnUil;
    @Bind(R.id.iv)
    ImageView mIv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_load_img, container, false);
        ButterKnife.bind(this, rootView);
        mBtnGlide.setOnClickListener(this::onViewClick);
        mBtnPicassoo.setOnClickListener(this::onViewClick);
        mBtnFresco.setOnClickListener(this::onViewClick);
        mBtnUil.setOnClickListener(this::onViewClick);
        return rootView;
    }

    public void onViewClick(View view) {
        String url1 = "http://d.hiphotos.baidu.com/image/w%3D2048/sign=b3416130347adab43dd01c43bfecb21c/503d269759ee3d6d1ea355d341166d224f4ade45.jpg";
        switch (view.getId()) {
            case R.id.btn_glide:
                btnGlide(url1);
                break;
            case R.id.btn_picassoo:
                Picasso.get()
                        .load(url1)
                        .error(R.drawable.mm1)
                        .into(mIv);
                break;
            case R.id.btn_fresco:
                break;
            case R.id.btn_uil:
                break;
            default:
                break;
        }
    }

    private void btnGlide(String url1) {
        Glide.with(getActivity())
                .load(url1)
                .into(mIv);
    }
}
