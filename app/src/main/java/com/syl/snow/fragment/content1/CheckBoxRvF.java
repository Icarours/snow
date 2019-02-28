package com.syl.snow.fragment.content1;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.Api;
import com.syl.snow.bean.BaseApi;
import com.syl.snow.bean.DepartmentE;
import com.syl.snow.bean.PersonE;
import com.syl.snow.bean.TitleBean;
import com.syl.snow.config.Constant;
import com.syl.snow.utils.LogUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * Created by Bright on 2019/2/22.
 *
 * @Describe RecyclerView和复选框
 * @Called
 */
public class CheckBoxRvF extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = CheckBoxRvF.class.getSimpleName();
    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.srl)
    SwipeRefreshLayout mSrl;
    @Bind(R.id.tv_no_data)
    TextView mTvNoData;
    @Bind(R.id.tv_retry)
    TextView mTvRetry;
    @Bind(R.id.rv_title)
    RecyclerView mRvTitle;
    private boolean refresh = false;
    private PersonAdapter mAdapter;
    private int pageNumber = 1;
    private int departmentId = -1;
    private List<PersonE> mListData = new ArrayList();
    private List<TitleBean> mListTitle = new ArrayList();//标题
    private boolean currentItemChecked = false;//当前被点击的条目是否被选中
    private TitleAdapter mTitleAdapter;
    private List<PersonE> mListSave = new ArrayList<>();//用来保存选中的条目

    @Override
    public void initData() {
        mListTitle.add(new TitleBean(-1, "根节点", ""));
        departmentId = -1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_check_box_rv, container, false);
        ButterKnife.bind(this, rootView);

        mRvTitle.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mSrl.setOnRefreshListener(this);
        mSrl.setColorSchemeColors(getResources().getColor(R.color.bar), getResources().getColor(R.color.bar1),
                getResources().getColor(R.color.bar2), getResources().getColor(R.color.bar3));
        mTitleAdapter = new TitleAdapter(R.layout.rv_item_title, mListTitle);
        mRvTitle.setAdapter(mTitleAdapter);
        mTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                currentItemChecked = false;
                int count = mListTitle.size() - position + 1;
                if (count > 0) {
                    departmentId = mListTitle.get(position).getId();
                    initList();
                    for (int i = 0; i < count; i++) {
                        int pos = position + i + 1;
                        if (pos < mListTitle.size())
                            mListTitle.remove(pos);
                    }
                    mTitleAdapter.notifyDataSetChanged();
                }
            }
        });
        initList();
        return rootView;
    }

    @Override
    public void onRefresh() {
        refresh = true;
        mSrl.setRefreshing(refresh);
        clearListData();
        initList();
    }

    private void initList() {
        clearListData();
        /**
         * http://cloud.lanlyc.cn/lanlyc_gongdi/persDepartment/getPersonAndSubDepartmentByCon?paramJson={"departmentId":-1,"mobile":"15989469069"}
         */
        Map<String, String> params = new HashMap<>();
        String url = "http://cloud.lanlyc.cn/lanlyc_gongdi/persDepartment/getPersonAndSubDepartmentByCon";
//        params.put(Constant.PAGE_SIZE, pageNumber + "");
        params.put(Constant.MOBILE, "15989469069");
        params.put("departmentId", departmentId + "");
        String paramsStr = JSONArray.toJSONString(params);
        Disposable disposable = EasyHttp.post(url)
                .params("paramJson", paramsStr)
                .execute(new CallBackProxy<Api<String>, String>(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        LogUtils.d(TAG, "网络请求失败..");
                    }

                    @Override
                    public void onSuccess(String s) {
                        refresh = false;
                        if (mSrl != null && mSrl.isRefreshing()) {
                            mSrl.setRefreshing(refresh);
                        }
//                        LogUtils.d(TAG, "result=" + s);
                        BaseApi baseApi = JSONObject.parseObject(s, BaseApi.class);
                        if (baseApi.isOk()) {
                            String apiData = baseApi.getData();
                            if (apiData != null && !"".equals(apiData)) {
                                DepartmentE departmentE = JSONObject.parseObject(apiData, DepartmentE.class);
                                List<PersonE> department_list = departmentE.getSub_department_list();
                                List<PersonE> person_list = departmentE.getPerson_list();
                                initAdapter();
                                //1:部门,2:人员
                                if (department_list != null && department_list.size() > 0) {
                                    for (int i = 0; i < department_list.size(); i++) {
                                        PersonE personE = department_list.get(i);
                                        personE.setChecked(currentItemChecked);
                                        personE.setFather_node(true);
                                        personE.setSon_node(true);
                                        personE.setItemType(1);
                                        mListData.add(personE);
                                    }
                                } else {
                                    LogUtils.d(TAG, "父节点部门id==" + departmentId + "部门department_list数据集为空");
                                }
                                if (person_list != null && person_list.size() > 0) {
                                    for (int i = 0; i < person_list.size(); i++) {
                                        PersonE personE = person_list.get(i);
                                        personE.setChecked(currentItemChecked);
                                        personE.setFather_node(true);
                                        personE.setSon_node(false);
                                        personE.setItemType(2);
                                        mListData.add(personE);
                                    }
                                } else {
                                    LogUtils.d(TAG, "父节点部门id==" + departmentId + "人员person_list数据集为空");
                                }
                                if (mListData.size() > 0) {
                                    //一般情况下,我们判断当前请求返回的数据集合,但是这个网络请求有点特别,里面有两个list集合
                                    if (mListData.size() % Integer.parseInt(Constant.PAGE_SIZE) == 0) {
                                        pageNumber++;
                                        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                            @Override
                                            public void onLoadMoreRequested() {
                                                mHandler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        initList();
                                                    }
                                                }, 1000);
                                            }
                                        }, mRv);
                                        mAdapter.loadMoreComplete();
                                    } else {
                                        if (pageNumber > 1)
                                            mAdapter.loadMoreEnd();
                                    }
                                } else {
                                    LogUtils.d(TAG, "data 数据集长度为0");
                                }
                            } else {
                                LogUtils.d(TAG, "data 数据集为空");
                            }
                        } else {
                            LogUtils.d(TAG, "code 返回值不为200");
                        }
                    }
                }) {
                });
        mDisposableList.add(disposable);
    }

    /**
     * 清空数据集
     */
    private void clearListData() {
        if (mListData.size() > 0) {
            mListData.clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initAdapter() {
        if (mAdapter == null) {
            //给RecyclerView设置Adapter必须紧接着Adapter创建之后,如果在if语句后面设置,RecyclerView上拉刷新之后会自动跳到RecyclerView顶部
            mAdapter = new PersonAdapter(mListData, getActivity());
            mRv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<PersonE> list = adapter.getData();
                PersonE personE = list.get(position);
                CheckBox checkBox = view.findViewById(R.id.cb);

                if (1 == personE.getItemType()) {
                    currentItemChecked = checkBox.isChecked();
                    departmentId = Integer.parseInt(mListData.get(position).getDepartment_id());
                    initList();
                    //如果是部门,且集合中没有该元素,就将部门名称加入标题集合
                    boolean b = listTitleHasContain(personE.getDepartment_id());
                    if (personE.isSon_node() && !b) {
                        mListTitle.add(new TitleBean(Integer.parseInt(personE.getDepartment_id()), personE.getDepartment_name(), ""));
                        mTitleAdapter.notifyDataSetChanged();
                    }
                } else if (2 == personE.getItemType()) {
                    Toast.makeText(getContext(), personE.getDepartment_name() + "--" + personE.getPerson_name(), Toast.LENGTH_SHORT).show();
                } else {
                    LogUtils.d(TAG, "条目类型不存在..");
                }
            }
        });
    }

    /**
     * 目标字符串是否在集合中
     *
     * @param department_id
     * @return
     */
    private boolean listTitleHasContain(String department_id) {
        for (int i = 0; i < mListTitle.size(); i++) {
            if (department_id.equals(mListTitle.get(i).getId())) {
                return true;
            }
        }
        return false;
    }

    class PersonAdapter extends BaseMultiItemQuickAdapter<PersonE, BaseViewHolder> {
        private Activity mContext;

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data    A new list is created out of this one to avoid mutable list
         * @param context
         */
        public PersonAdapter(List<PersonE> data, Activity context) {
            super(data);
            mContext = context;
            //1:部门,2:人员
            addItemType(1, R.layout.rv_item_person1);
            addItemType(2, R.layout.rv_item_person2);
        }

        @Override
        protected void convert(BaseViewHolder helper, PersonE item) {
            int itemType = item.getItemType();
            switch (itemType) {
                case 1:
                    helper.setText(R.id.tv_department, item.getDepartment_name());
                    break;
                default:
                case 2:
                    helper.setText(R.id.tv_name, item.getPerson_name())
                            .setText(R.id.tv_phone, item.getMobile_phone());
                    RequestOptions options = new RequestOptions();
                    options.error(R.drawable.mm2);
                    options.circleCrop();
                    Glide.with(mContext)
                            .load(item.getPhoto_path())
                            .apply(options)
                            .into((ImageView) helper.getView(R.id.iv));
                    break;
            }
            CheckBox checkBox = helper.getView(R.id.cb);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setChecked(isChecked);
                    if (item.isChecked()) {//如果选中,就保存到mListSave
                        mListSave.add(item);
                    }
                    //如果没有选中而且在mListSave集合中,就从mListSave中移除
                    if (!item.isChecked() && mListSave.contains(item)) {
                        mListSave.remove(item);
                    }
                }
            });
            //如果listSave集合中有人员名或者部门名相同的对象,就将当前条目设置为选中状态
            if (listDataHasContain(item)) {
                item.setChecked(true);
            }
            checkBox.setChecked(item.isChecked());
        }
    }

    /**
     * list集合是否包含指定信息.注意:两者的对象肯定是不一样的,但是我们要比较的是对象中的信息,只要是部门名和人员名
     *
     * @param item
     */
    private boolean listDataHasContain(PersonE item) {
        for (int i = 0; i < mListSave.size(); i++) {
            PersonE personE = mListSave.get(i);
            int itemType = item.getItemType();
            if (1 == itemType && 1 == personE.getItemType()) {
                if (item.getDepartment_name().equals(personE.getDepartment_name())) {
                    return true;
                }
            } else if (2 == itemType && 2 == personE.getItemType()) {
                if (item.getPerson_name().equals(personE.getPerson_name())) {
                    return true;
                }
            }
        }
        return false;
    }

    class TitleAdapter extends BaseQuickAdapter<TitleBean, BaseViewHolder> {
        public TitleAdapter(int layoutResId, @Nullable List<TitleBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TitleBean item) {
            helper.setText(R.id.tv_title, item.getTitle());
        }
    }
}
