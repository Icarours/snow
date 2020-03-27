package com.syl.snow.fragment.content1;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * Created by Bright on 2018/12/23.
 *
 * @Describe 加载本地图片,使用RxPermission请求动态权限
 * @Called
 */
public class ImageFragment extends BaseFragment {
    @Bind(R.id.btn_load_img)
    Button mBtnLoadImg;
    @Bind(R.id.iv)
    ImageView mIv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn_load_img, R.id.btn_load_img2})
    public void onViewClick(View view) {
        RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity or Fragment instance
        switch (view.getId()) {
            default:
            case R.id.btn_load_img:
                rxPermissions
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                loadImg();
                            } else {
                                Toast.makeText(getContext(), "该操作需要读取sd卡的权限", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.btn_load_img2:
                Disposable dis = rxPermissions
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                scaleLoad();
                            } else {
                                Toast.makeText(getContext(), "该操作需要读取sd卡的权限", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }

    private void loadImg() {
        String url = "/sdcard/DCIM/Camera/IMG_20181205_204323.jpg";
        //        String path = etPath.getText().toString().trim();
        // 根据路径得到图片对象
        Bitmap bitmap = BitmapFactory.decodeFile(url);
        // 把图片展示到ImageView 控件上.
        mIv.setImageBitmap(bitmap);
    }

    public void scaleLoad() {
        String path = "sdcard/DCIM/Camera/IMG_20181128_204311.jpg";
        //        String path = etPath.getText().toString().trim();
        // 得到图片的宽和高
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true; // 加载器不加载图片, 而是把图片        的out(宽和高)的字段信息取出来
        BitmapFactory.decodeFile(path, opts);
        int imageWidth = opts.outWidth;
        int imageHeight = opts.outHeight;
        System.out.println("图片的宽和高: " + imageWidth + " * " +
                imageHeight);
        // 得到屏幕的宽和高
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();
        System.out.println("屏幕的宽和高: " + screenWidth + " * " +
                screenHeight);
        // 计算缩放比例
        int widthScale = imageWidth / screenWidth;
        int heightScale = imageHeight / screenHeight;
        int scale = widthScale > heightScale ? widthScale : heightScale;
        System.out.println("缩放比例为: " + scale);
        // 使用缩放比例进行缩放加载图片
        opts.inJustDecodeBounds = false; // 加载器就会返回图片了
        opts.inSampleSize = scale;
        Bitmap bm = BitmapFactory.decodeFile(path, opts);
        // 显示在屏幕上
        mIv.setImageBitmap(bm);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
