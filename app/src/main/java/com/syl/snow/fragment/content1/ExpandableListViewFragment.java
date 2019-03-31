package com.syl.snow.fragment.content1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/3/31.
 *
 * @Describe ListView两级菜单-ExpandableListView
 * @Called
 */
public class ExpandableListViewFragment extends BaseFragment {
    @Bind(R.id.elv)
    ExpandableListView mElv;
    private FragmentActivity mContext;
    private String[] groupString = {"射手", "辅助", "坦克", "法师"};
    private String[][] childString = {
            {"孙尚香", "后羿", "马可波罗", "狄仁杰"},
            {"孙膑", "蔡文姬", "鬼谷子", "杨玉环"},
            {"张飞", "廉颇", "牛魔王", "项羽"},
            {"诸葛亮", "王昭君", "安其拉", "干将"}
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expandable_list_view, container, false);
        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        MyExtendableListViewAdapter adapter = new MyExtendableListViewAdapter(mContext, groupString, childString);
        mElv.setAdapter(adapter);
        //设置分组的监听
        mElv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(mContext, groupString[groupPosition], Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //设置子项布局监听
        mElv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, childString[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        for (int i = 0; i < groupString.length; i++) {
            //只有在ExpandableListView绘制完成之后,才能展开二级列表
            mElv.expandGroup(i);
        }
        return rootView;
    }

    class MyExtendableListViewAdapter extends BaseExpandableListAdapter {
        private Context mcontext;
        private String[] groupString;
        private String[][] childString;

        public MyExtendableListViewAdapter(FragmentActivity context, String[] groupString, String[][] childString) {
            this.mcontext = context;
            this.groupString = groupString;
            this.childString = childString;
        }

        @Override
        // 获取分组的个数
        public int getGroupCount() {
            return groupString.length;
        }

        //获取指定分组中的子选项的个数
        @Override
        public int getChildrenCount(int groupPosition) {
            return childString[groupPosition].length;
        }

        //        获取指定的分组数据
        @Override
        public Object getGroup(int groupPosition) {
            return groupString[groupPosition];
        }

        //获取指定分组中的指定子选项数据
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childString[groupPosition][childPosition];
        }

        //获取指定分组的ID, 这个ID必须是唯一的
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        //获取子选项的ID, 这个ID必须是唯一的
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
        @Override
        public boolean hasStableIds() {
            return true;
        }

        /**
         * 获取显示指定组的视图对象
         *
         * @param groupPosition 组位置
         * @param isExpanded    该组是展开状态还是伸缩状态
         * @param convertView   重用已有的视图对象
         * @param parent        返回的视图对象始终依附于的视图组
         */
// 获取显示指定分组的视图
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder groupViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.partent_item, parent, false);
                groupViewHolder = new GroupViewHolder();
                groupViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.label_group_normal);
                convertView.setTag(groupViewHolder);
            } else {
                groupViewHolder = (GroupViewHolder) convertView.getTag();
            }
            groupViewHolder.tvTitle.setText(groupString[groupPosition]);
            return convertView;
        }

        /**
         * 获取一个视图对象，显示指定组中的指定子元素数据。
         *
         * @param groupPosition 组位置
         * @param childPosition 子元素位置
         * @param isLastChild   子元素是否处于组中的最后一个
         * @param convertView   重用已有的视图(View)对象
         * @param parent        返回的视图(View)对象始终依附于的视图组
         * @return
         * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View,
         * android.view.ViewGroup)
         */

        //取得显示给定分组给定子位置的数据用的视图
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder childViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
                childViewHolder = new ChildViewHolder();
                childViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.expand_child);
                convertView.setTag(childViewHolder);

            } else {
                childViewHolder = (ChildViewHolder) convertView.getTag();
            }
            childViewHolder.tvTitle.setText(childString[groupPosition][childPosition]);
            return convertView;
        }

        //指定位置上的子元素是否可选中
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        class GroupViewHolder {
            TextView tvTitle;
        }

        class ChildViewHolder {
            TextView tvTitle;
        }
    }
}
