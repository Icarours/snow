package com.syl.snow.adpater;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.bean.ImageE;
import com.syl.snow.utils.LogUtils;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/2/17.
 *
 * @Describe
 * @Called
 */
public class ImageAdapter extends BaseQuickAdapter<ImageE, BaseViewHolder> {
    private static final String TAG = ImageAdapter.class.getSimpleName();
    private Context mContext;

    public ImageAdapter(int layoutResId, @Nullable List<ImageE> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageE item) {
        LogUtils.d(TAG, item.toString());
        Glide.with(mContext)
                .load(item.getPath())
                .into((ImageView) helper.getView(R.id.iv));
        helper.setText(R.id.tv, "item--" + item.getId());
    }
}