package com.syl.snow.fragment.content3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.syl.snow.R;
import com.syl.snow.utils.LogUtils;
import com.syl.snow.utils.glide.GlideUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/8/8.
 *
 * @Describe
 * @Called 使用takephoto_library从相册中获取图片
 */
public class AlbumFragment2 extends TakePhotoFragment {
    private static final String TAG = AlbumFragment2.class.getSimpleName();
    @Bind(R.id.tv_pic)
    TextView mTvPic;
    @Bind(R.id.iv)
    ImageView mIv;
    @Bind(R.id.iv2)
    ImageView mIv2;
    private TImage mImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_album2, container, false);
        ButterKnife.bind(this, rootView);
        mTvPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TakePhoto takePhoto = getTakePhoto();
                takePhoto.onPickFromGallery();
            }
        });
        return rootView;
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        mImage = result.getImage();
        String compressPath = mImage.getCompressPath();
        String originalPath = mImage.getOriginalPath();
        GlideUtil.loadImage(getActivity(), compressPath, mIv, R.drawable.mm2);
        GlideUtil.loadImage(getActivity(), originalPath, mIv2, R.drawable.mm2);
        LogUtils.d(TAG, "compressPath==" + compressPath + "\noriginalPath==" + originalPath);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }
}
