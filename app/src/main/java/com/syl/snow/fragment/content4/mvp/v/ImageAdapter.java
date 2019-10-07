package com.syl.snow.fragment.content4.mvp.v;

import android.app.Activity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.fragment.content4.mvp.m.ImageE;
import com.syl.snow.utils.glide.GlideUtil;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 */
public class ImageAdapter extends BaseQuickAdapter<ImageE, BaseViewHolder> {
    private Activity mActivity;

    public ImageAdapter(Activity activity, int layoutResId, @Nullable List<ImageE> data) {
        super(layoutResId, data);
        mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageE item) {
        helper.setText(R.id.tv_date_time, item.getId() + "--" + item.getTime());
        GlideUtil.loadImage(mActivity, item.getImg(), (ImageView) helper.getView(R.id.iv), R.drawable.mm1);
    }
}
