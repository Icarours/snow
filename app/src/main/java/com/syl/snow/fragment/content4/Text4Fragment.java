package com.syl.snow.fragment.content4;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.TitleBean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Bright on 2018/12/8.
 *
 * @Describe 根据Android文档说明，当一个Fragment重新创建的时候，系统会再次调用Fragment中的默认构造函数，
 * 注意是默认构造函数。即，当你创建了一个带有参数的Fragment的之后，一旦由于什么原因
 * （例如横竖屏切换）导致你的Fragment重新创建。那么，很遗憾，你之前传递的参数都不见了，
 * 因为recreate你的Fragment的时候，调用的是默认构造函数。因此，官方推荐使用
 * Fragment.setArguments(Bundle bundle)这种方式来传递参数，而不推荐通过构造方法直接来传递参数。
 * @Called
 */
public class Text4Fragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TitleBean title = (TitleBean) getArguments().getSerializable("title");
        TextView textView = new TextView(getContext());
        textView.setText(title.toString());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        return textView;
    }
}
