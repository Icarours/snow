package com.syl.snow.fragment.content3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.config.MyApplication;
import com.syl.snow.utils.LogUtils;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/6/17.
 *
 * @Describe PackageManager包管理器
 * @Called
 */
public class PackageManagerFragment extends BaseFragment {
    private static final String TAG = PackageManagerFragment.class.getSimpleName();
    @Bind(R.id.tv1)
    TextView mTv1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_package_manager, container, false);
        ButterKnife.bind(this, rootView);
        getAppInfo();
        mTv1.setMovementMethod(ScrollingMovementMethod.getInstance());
        return rootView;
    }

    private void getAppInfo() {
        StringBuilder sb = new StringBuilder();
        // 获取PackageManager对象
        PackageManager pm = MyApplication.getContext().getPackageManager();
        // 设置<intent-filter>标签内需要满足的条件
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        // 通过queryIntentActivities获取ResolveInfo对象
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);

        // 调用系统排序，根据name排序
        // 该排序很重要，否则只能显示系统应用，不能显示第三方应用
        // 其实我测试发现有没有其实是一样的，就是输出的顺序是乱的
        Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(pm));

        for (ResolveInfo resolveInfo : resolveInfos) {
            String appName = resolveInfo.loadLabel(pm).toString();// 获取应用名称
            String packageName = resolveInfo.activityInfo.packageName;// 包名
            String className = resolveInfo.activityInfo.name;// 入口类名
            sb.append("程序名：").append(appName).append(" 包名:").append(packageName).append(" 入口类名：").append(className).append("\n");
            LogUtils.d(TAG, "程序名：" + appName + " 包名:" + packageName
                    + " 入口类名：" + className);
        }

        mTv1.setText(sb.toString());
    }
}
