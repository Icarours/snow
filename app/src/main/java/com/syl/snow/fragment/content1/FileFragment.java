package com.syl.snow.fragment.content1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/3/5.
 *
 * @Describe Android中的常用的文件目录
 * @Called
 */
public class FileFragment extends BaseFragment {
    private static final String TAG = FileFragment.class.getSimpleName();
    @Bind(R.id.tv)
    TextView mTv;
    private String fileName = "test.json";
    private String fileName2 = "test2.json";
    private String fileName3 = "test3.json";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_file, container, false);
        ButterKnife.bind(this, rootView);
        StringBuilder sb = new StringBuilder();
        File filesDir = getActivity().getFilesDir();//获取/data/data/<packageName>/files目录
        sb.append("getFilesDir()--" + "\n");
        sb.append(filesDir + "\n");
        try {
            FileInputStream fileInputStream = getActivity().openFileInput(fileName);//打开现有文件进行读取
            sb.append("openFileInput(fileName)--" + "\n");
            sb.append(fileInputStream + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LogUtils.d(TAG, "文件不存在..");
        }
        try {
            //打开文件进行写入,如不存在就创建它
            FileOutputStream fileOutputStream = getActivity().openFileOutput(fileName2, Context.MODE_PRIVATE);
            sb.append("openFileOutput(fileName2, Context.MODE_PRIVATE)--" + "\n");
            sb.append(fileOutputStream + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File dir = getActivity().getDir(fileName3, Context.MODE_PRIVATE);//获取/data/data/<packageName>/files目录的子目录,如不存在就先创建它
        sb.append("getDir(fileName3, Context.MODE_PRIVATE)--" + "\n");
        sb.append(dir + "\n");
        String[] strings = getActivity().fileList();//获取/data/data/<packageName>/files目录的文件列表.可与其他方法配合使用,例如openFileInput(String name)
        for (int i = 0; i < strings.length; i++) {
            sb.append("fileList()--" + i + "\n");
            sb.append(strings[i] + "\n");
        }
        File cacheDir = getActivity().getCacheDir();//获取/data/data/<packageName>/cache目录.应注意及时清理该目录,并节约使用空间
        sb.append("getCacheDir()--" + "\n");
        sb.append(cacheDir + "\n");
        mTv.setText(sb.toString());
        return rootView;
    }
}
