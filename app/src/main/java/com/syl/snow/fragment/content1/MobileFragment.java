package com.syl.snow.fragment.content1;

import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import java.lang.reflect.Field;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/2/16.
 *
 * @Describe 获取当前手机的参数
 * @Called
 */
public class MobileFragment extends BaseFragment {
    private static final String TAG = MobileFragment.class.getSimpleName();
    @Bind(R.id.tv)
    TextView mTv;
    private StringBuffer mSb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mobile, container, false);
        ButterKnife.bind(this, rootView);
        mTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        mTv.setText(mSb.toString());
        return rootView;
    }

    @Override
    public void initData() {
        mSb = new StringBuffer();
        mSb.append("当前手机的参数\n\n");

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mSb.append("Build.").append(field.getName()).append("===").append(field.get(null)).append("\n");
                Log.d(TAG, "Build." + field.getName() + "===" + field.get(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        mSb.append("+++++++++++++++\n");
        Field[] fieldsVersion = Build.VERSION.class.getDeclaredFields();
        for (Field field : fieldsVersion) {
            try {
                field.setAccessible(true);
                Log.d(TAG, "Build.VERSION." + field.getName() + " === " + field.get(null));
                mSb.append("Build.VERSION.").append(field.getName()).append(" === ").append(field.get(null)).append("\n");
            } catch (Exception e) {
                Log.e(TAG, "an error occurred when collect crash info", e);
            }
        }
    }
}
