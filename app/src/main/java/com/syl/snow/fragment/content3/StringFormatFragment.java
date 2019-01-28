package com.syl.snow.fragment.content3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.StringFormat;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/1/28.
 *
 * @Describe 格式化字符串, String.format()用法
 * @Called
 */
public class StringFormatFragment extends BaseFragment {
    private static final String TAG = StringFormatFragment.class.getSimpleName();
    @Bind(R.id.btn1)
    Button mBtn1;
    @Bind(R.id.tv)
    TextView mTv;
    @Bind(R.id.tv2)
    TextView mTv2;
    @Bind(R.id.btn2)
    Button mBtn2;
    @Bind(R.id.btn3)
    Button mBtn3;
    @Bind(R.id.iv)
    ImageView mIv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_string_format, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                StringFormat.test1();
                StringFormat.test2();
                StringFormat.test3();
                StringFormat.test4();
                break;
            case R.id.btn2:
                Date date = new Date();
                //保存图片到公共文件
                String path = Environment.getExternalStorageDirectory().getPath() + "/EZOpenSDK/CapturePicture/" + String.format("%tY", date)
                        + String.format("%tm", date) + String.format("%td", date) + "/"
                        + String.format("%tH", date) + String.format("%tM", date) + String.format("%tS", date) + String.format("%tL", date) + ".jpg";
                //保存图片app私有文件夹
                String path2 = getActivity().getFilesDir().getPath() + "/EZOpenSDK/CapturePicture/" + String.format("%tY", date)
                        + String.format("%tm", date) + String.format("%td", date) + "/"
                        + String.format("%tH", date) + String.format("%tM", date) + String.format("%tS", date) + String.format("%tL", date) + ".jpg";
                mTv.setText(path);
                mTv2.setText(path2);
                break;
            case R.id.btn3:
                Bitmap bitmap = capture(getActivity());
                mIv.setImageBitmap(bitmap);
                break;
            default:
                break;
        }
    }

    public static Bitmap capture(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }
}
