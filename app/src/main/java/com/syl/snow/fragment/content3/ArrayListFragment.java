package com.syl.snow.fragment.content3;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.User2;

import java.util.ArrayList;
import java.util.Iterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/2/25.
 *
 * @Describe 去除ArrayList中的重复对象
 * @Called
 */
public class ArrayListFragment extends BaseFragment {
    @Bind(R.id.textView6)
    TextView mTextView6;
    @Bind(R.id.tv_before)
    TextView mTvBefore;
    @Bind(R.id.textView8)
    TextView mTextView8;
    @Bind(R.id.btn_action)
    Button mBtnAction;
    @Bind(R.id.tv_after)
    TextView mTvAfter;
    private ArrayList<User2> mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_array_list, container, false);
        ButterKnife.bind(this, rootView);
        mList = new ArrayList<>();
        mList.add(new User2("zhangsan", "33"));
        mList.add(new User2("lisi", "44"));
        mList.add(new User2("zhangsan", "33"));
        mList.add(new User2("zhangsan", "33"));
        StringBuffer sb = new StringBuffer();
        for (User2 user :
                mList) {
            sb.append(user.toString());
        }
        mTvBefore.setMovementMethod(ScrollingMovementMethod.getInstance());
        mTvBefore.setText(sb.toString());
        mTvAfter.setMovementMethod(ScrollingMovementMethod.getInstance());
        return rootView;
    }

    @OnClick({R.id.btn_action})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_action:
                StringBuffer sb = new StringBuffer();
                ArrayList<User2> list = fangfa(mList);
                for (User2 user :
                        list) {
                    sb.append(user.toString());
                }
                mTvAfter.setText(sb.toString());
                break;
            default:
                break;
        }
    }

    /**
     * 去除重复对象
     * 向辅助集合b中添加对象,如果辅助集b合中已经有该对象,就不再添加
     *
     * @param a
     * @return
     */
    public static ArrayList<User2> fangfa(ArrayList<User2> a) {//写判断是否重复的内部静态方法。
        ArrayList<User2> b = new ArrayList();
        for (Iterator t = a.iterator(); t.hasNext(); ) {
            User2 c = (User2) t.next();
            if (!(b.contains(c))) {
                b.add(c);
            }//此处contains调用了被重写的equals方法。
        }
        return b;//返回进行判断后的ArrayList。
    }
}
