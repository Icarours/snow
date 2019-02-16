package com.syl.snow.adpater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.bean.TitleBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/2/16.
 *
 * @Describe
 * @Called
 */
public class ContentAdapter extends BaseQuickAdapter<TitleBean, BaseViewHolder> {
    public ContentAdapter(int layoutResId, @Nullable List<TitleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TitleBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_desc, item.getDescription());
    }
}
