package com.syl.snow.fragment.content1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.activity.ToolbarActivity1;
import com.syl.snow.activity.ToolbarActivity2;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/2/17.
 *
 * @Describe 弹出式菜单和ToolBar
 * @Called
 */
public class PopFragment extends BaseFragment implements PopupMenu.OnMenuItemClickListener{
    @Bind(R.id.btn_pop)
    Button mBtnPop;
    @Bind(R.id.btn_toolbar)
    Button mBtnToolbar;
    @Bind(R.id.btn_toolbar2)
    Button mBtnToolbar2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pop, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @OnClick({R.id.btn_pop, R.id.btn_toolbar, R.id.btn_toolbar2})
    public void onViewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_pop:
                initPopMenu(view);
                break;
            case R.id.btn_toolbar:
                intent = new Intent(getActivity(), ToolbarActivity1.class);
                startActivity(intent);
                break;
            case R.id.btn_toolbar2:
                intent = new Intent(getActivity(), ToolbarActivity2.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化弹出式菜单
     *
     * @param v
     */
    private void initPopMenu(View v) {
        //创建弹出式菜单,
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        //获取菜单填充器
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        //填充菜单
        menuInflater.inflate(R.menu.menu_pop, popupMenu.getMenu());
        //设置菜单点击事件
        popupMenu.setOnMenuItemClickListener(this);
        //显示菜单
        popupMenu.show();
    }

    /**
     * 菜单点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                Toast.makeText(getActivity(), "退出..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.set:
                Toast.makeText(getActivity(), "设置..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.account:
                Toast.makeText(getActivity(), "账号..", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }
}
