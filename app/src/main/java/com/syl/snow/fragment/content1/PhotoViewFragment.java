package com.syl.snow.fragment.content1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.syl.snow.R;
import com.syl.snow.activity.PhotoViewActivity;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.TitleBean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/3/6.
 *
 * @Describe Android图片查看器
 * @Called
 */
public class PhotoViewFragment extends BaseFragment {
    @Bind(R.id.iv1)
    ImageView mIv1;
    @Bind(R.id.iv2)
    ImageView mIv2;
    @Bind(R.id.iv3)
    ImageView mIv3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo_view, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.iv1, R.id.iv2, R.id.iv3})
    public void onClickView(View view) {
        Intent intent = new Intent(getContext(),PhotoViewActivity.class);
        intent.putExtra("title",new TitleBean(0,"查看图片", "简易图片查看器"));
        switch (view.getId()) {
            case R.id.iv1:
                intent.putExtra("showphoto",0);
                startActivity(intent);
                break;
            case R.id.iv2:
                intent.putExtra("showphoto",1);
                startActivity(intent);
                break;
            case R.id.iv3:
                intent.putExtra("showphoto",2);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
