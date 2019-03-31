package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.DoubleRvE;
import com.syl.snow.bean.TitleBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2019/3/31.
 *
 * @Describe RecyclerView嵌套, RecyclerView的条目还是RecyclerView
 * @Called
 */
public class DoubleRecyclerViewFragment extends BaseFragment {
    @Bind(R.id.rv)
    RecyclerView mRv;
    private List<DoubleRvE> mList = new ArrayList<>();//数据集

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_double_recycler_view, container, false);
        ButterKnife.bind(this, rootView);
        FragmentActivity context = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        DoublerRvAdapter adapter = new DoublerRvAdapter(R.layout.rv_double_rv, mList, context);
        mRv.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i < 20; i++) {
            DoubleRvE doubleRvE = new DoubleRvE();
            Random random = new Random();
            List<TitleBean> list = new ArrayList<>();
            for (int j = 0; j < random.nextInt(10); j++) {
                list.add(new TitleBean(j, "title--" + j, "desc--" + j));
            }
            doubleRvE.setTieleId(i);
            doubleRvE.setTitleDesc("title desc--" + i);
            doubleRvE.setTitleName("title name--" + i);
            doubleRvE.setTitleList(list);
            mList.add(doubleRvE);
        }
    }

    class DoublerRvAdapter extends BaseQuickAdapter<DoubleRvE, BaseViewHolder> {
        private FragmentActivity mContext;

        public DoublerRvAdapter(int layoutResId, @Nullable List<DoubleRvE> data, FragmentActivity context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, DoubleRvE item) {
            helper.setText(R.id.tv_title, item.getTitleName())
                    .setText(R.id.tv_desc, item.getTitleDesc());
            RecyclerView rvItem = helper.getView(R.id.rv_item);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
            rvItem.setLayoutManager(linearLayoutManager);
            rvItem.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
            ItemAdapter itemAdapter = new ItemAdapter(R.layout.rv_item_item, item.getTitleList());
            rvItem.setAdapter(itemAdapter);
        }

        class ItemAdapter extends BaseQuickAdapter<TitleBean, BaseViewHolder> {

            public ItemAdapter(int layoutResId, @Nullable List<TitleBean> data) {
                super(layoutResId, data);
            }

            @Override
            protected void convert(BaseViewHolder helper, TitleBean item) {
                helper.setText(R.id.tv_item, item.getId() + "--" + item.getTitle() + "---" + item.getDescription());
            }
        }
    }
}
