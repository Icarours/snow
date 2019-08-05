package com.syl.snow.adpater;

import android.app.Activity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.bean.WarnMessageE;
import com.syl.snow.utils.glide.GlideUtil;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/8/5.
 *
 * @Describe
 * @Called
 */
public class PhotoAdapter extends BaseQuickAdapter<WarnMessageE, BaseViewHolder> {
    private Activity mActivity;

    public PhotoAdapter(int layoutResId, @Nullable List<WarnMessageE> data, Activity activity) {
        super(layoutResId, data);
        mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, WarnMessageE item) {
        helper.setText(R.id.tv_type, item.getWarn_type())
                .setText(R.id.tv_time, item.getCreate_time());
        GlideUtil.loadImage(mActivity, item.getWarn_picture(), (ImageView) helper.getView(R.id.iv_img), R.drawable.mm1);
    }
}
