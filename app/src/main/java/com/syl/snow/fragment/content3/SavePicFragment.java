package com.syl.snow.fragment.content3;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by Bright on 2019/1/28.
 *
 * @Describe Android截图并保存图片到本地
 * 截图有问题
 * @Called
 */
public class SavePicFragment extends BaseFragment {
    private static final String TAG = SavePicFragment.class.getSimpleName();
    @Bind(R.id.btn1)
    Button mBtn1;
    @Bind(R.id.iv)
    ImageView mIv;
    private RxPermissions mRxPermissions;
    private File mFile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_save_pic, container, false);
        ButterKnife.bind(this, rootView);
        mRxPermissions = new RxPermissions(getActivity());
        return rootView;
    }

    @OnClick({R.id.btn1})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mRxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    savePic();
                                } else {
                                    showMissingPermissionDialog();
                                }
                            }
                        });
                break;
            case R.id.btn2:
                Glide.with(getContext())
                        .load(mFile);
                break;
            default:
                break;
        }
    }

    // 显示缺失权限提示
    public void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.basic_help);
        builder.setMessage(R.string.basic_string_help_text);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.basic_quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton(R.string.basic_settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    // 启动应用的设置
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
        startActivity(intent);
    }

    private void savePic() {
        mFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "zhangphil.jpg");
        Bitmap bitmap = screenShot(getActivity());
        try {
            if (!mFile.exists())
                mFile.createNewFile();

            boolean ret = save(bitmap, mFile, Bitmap.CompressFormat.JPEG, true);
            if (ret) {
                Toast.makeText(getContext(), "截图已保持至 " + mFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                LogUtils.d(TAG, "path==" + mFile.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存图片到文件File。
     *
     * @param src     源图片
     * @param file    要保存到的文件
     * @param format  格式
     * @param recycle 是否回收
     * @return true 成功 false 失败
     */
    public static boolean save(Bitmap src, File file, Bitmap.CompressFormat format, boolean recycle) {
        if (isEmptyBitmap(src))
            return false;

        OutputStream os;
        boolean ret = false;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            ret = src.compress(format, 100, os);
            if (recycle && !src.isRecycled())
                src.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


    /**
     * 获取当前屏幕截图，不包含状态栏（Status Bar）。
     *
     * @param activity activity
     * @return Bitmap
     */
    public static Bitmap screenShot(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int statusBarHeight = getStatusBarHeight(activity);
        int width = (int) getDeviceDisplaySize(activity)[0];
        int height = (int) getDeviceDisplaySize(activity)[1];

        Bitmap ret = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();

        return ret;
    }

    public static float[] getDeviceDisplaySize(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        float[] size = new float[2];
        size[0] = width;
        size[1] = height;

        return size;
    }

    public static int getStatusBarHeight(Context context) {
        int height = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = context.getResources().getDimensionPixelSize(resourceId);
        }

        return height;
    }

    /**
     * Bitmap对象是否为空。
     */
    public static boolean isEmptyBitmap(Bitmap src) {
        return src == null || src.getWidth() == 0 || src.getHeight() == 0;
    }
}
