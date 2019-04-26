package com.syl.snow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.bean.TitleBean;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/1/7.
 *
 * @Describe 文本, Text
 * @Called
 */
public class TextAdapter extends BaseQuickAdapter<TitleBean, BaseViewHolder> {
    public TextAdapter(int layoutResId, @Nullable List<TitleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TitleBean item) {
        helper.setText(R.id.tv_text, item.getTitle() + "\n" + item.getDescription());
    }
}
