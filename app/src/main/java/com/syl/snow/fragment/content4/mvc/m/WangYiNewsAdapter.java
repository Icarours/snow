package com.syl.snow.fragment.content4.mvc.m;

import android.app.Activity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.utils.glide.GlideUtil;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by Bright on 2019/10/6.
 *
 * @Describe
 * @Called
 */
public class WangYiNewsAdapter extends BaseQuickAdapter<WangYiNewsE, BaseViewHolder> {
    private Activity mContext;

    public WangYiNewsAdapter(FragmentActivity activity,int layoutResId, @Nullable List<WangYiNewsE> data) {
        super(layoutResId, data);
        mContext = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, WangYiNewsE item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_date_time, item.getPasstime());
        GlideUtil.loadImage(mContext, item.getImage(), (ImageView) helper.getView(R.id.iv), R.mipmap.ic_launcher);
    }
}
