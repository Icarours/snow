package com.syl.snow.adpater;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.bean.PersonE2;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author Bright
 * @date 2020/3/31 13:53
 * @describe 只有一个TextView控件
 */
public class TextAdapter2 extends BaseQuickAdapter<PersonE2, BaseViewHolder> {
    public TextAdapter2(int layoutResId, @Nullable List<PersonE2> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonE2 item) {
        helper.setText(R.id.tv_text, item.getContactId()+item.getDisplayName()+item.getPhoneNumber()+item.getAddress());
    }
}
