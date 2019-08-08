package com.syl.snow.fragment.content3;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.RealPathFromUriUtils;
import com.syl.snow.utils.glide.GlideUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * Created by Bright on 2019/8/8.
 *
 * @Describe
 * @Called 从相册中选择图片
 */
public class AlbumFragment extends BaseFragment {
    private static final int REQUEST_CODE_GALLERY = 1000;
    @Bind(R.id.tv_pic)
    TextView mTvPic;
    @Bind(R.id.iv)
    ImageView mIv;
    private static final int REQUEST_PICK_IMAGE = 11101;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_album, container, false);
        ButterKnife.bind(this, rootView);
        RxPermissions rxPermissions = new RxPermissions(this);
        mTvPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disposable subscribe = rxPermissions
                        .requestEach(Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(permission -> { // will emit 2 Permission objects
                            if (permission.granted) {
                                // `permission.name` is granted !
                                getImage();
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // Denied permission without ask never again
                                Toast.makeText(getContext(), "没有权限将选择图片", Toast.LENGTH_SHORT).show();
                            } else {
                                // Denied permission with ask never again
                                // Need to go to the settings
                                Toast.makeText(getContext(), "没有权限将选择图片", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        return rootView;
    }


    private void getImage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"),
                    REQUEST_PICK_IMAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_PICK_IMAGE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PICK_IMAGE:
                    if (data != null) {
                        String realPathFromUri = RealPathFromUriUtils.getRealPathFromUri(getContext(), data.getData());
                        GlideUtil.loadImage(getActivity(), realPathFromUri, mIv, R.drawable.mm2);
                    } else {
                        Toast.makeText(getContext(), "图片损坏，请重新选择", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
