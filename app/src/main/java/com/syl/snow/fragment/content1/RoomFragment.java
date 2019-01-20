package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syl.snow.R;
import com.syl.snow.base.AppExecutors;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.User;
import com.syl.snow.config.Constant;
import com.syl.snow.config.MyApplication;
import com.syl.snow.db.AppDataBase;
import com.syl.snow.db.UserDao;
import com.syl.snow.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/1/20.
 *
 * @Describe Room数据库,从数据库加载数据RecyclerView
 * @Called
 */
public class RoomFragment extends BaseFragment {
    private static final String TAG = RoomFragment.class.getSimpleName();
    @Bind(R.id.btn_insert)
    Button mBtnInsert;
    @Bind(R.id.btn_query)
    Button mBtnQuery;
    @Bind(R.id.btn_clear)
    Button mBtnClear;
    @Bind(R.id.btn_update)
    Button mBtnUpdate;
    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.tv_content)
    TextView mTvContent;
    private List<User> mList = new ArrayList<>();//数据集
    private UserAdapter mAdapter;
    private int index = 0;//索引,页面
    private int pageSize = 16;//每屛数据条目数量

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return rootView;
    }

    @OnClick({R.id.btn_insert, R.id.btn_query, R.id.btn_clear, R.id.btn_update})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                btnInsert();
                break;
            case R.id.btn_query:
                btnQuery();
                break;
            case R.id.btn_clear:
                btnClear();
                break;
            case R.id.btn_update:
                btnUpdate();
                break;
        }
    }

    private void btnInsert() {
        new AppExecutors().getmDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    UserDao userDao = AppDataBase.getInstance(getContext()).getUserDao();
                    userDao.insert(new User(i, "name--" + i, "description--" + i));
                }
                LogUtils.d(TAG, "数据插入完成");
            }
        });
    }

    private void btnQuery() {
        new AppExecutors().getmDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = AppDataBase.getInstance(getContext()).getUserDao();
                List<User> allUser = userDao.getPageUser(index * pageSize, pageSize);
                final int size = allUser.size();
                for (int i = 0; i < size; i++) {
                    LogUtils.d(TAG, allUser.get(i).toString());
                }
                LogUtils.d(TAG, "数据查询完成");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (size > 0) {
                            mList.addAll(allUser);
                        }
                        initAdapter();
                        index++;
                        if (size % pageSize == 0) {//只有==0才有可能有加载更多
                            mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                @Override
                                public void onLoadMoreRequested() {
                                    MyApplication.mHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            btnQuery();
                                        }
                                    },Constant.delayTime);
                                }
                            }, mRv);
                            mAdapter.loadMoreComplete();
                        } else {//加载结束
                            mAdapter.loadMoreEnd();
                        }
                    }
                });
            }
        });
    }

    private void initAdapter() {
        if (mAdapter == null) {
            mAdapter = new UserAdapter(R.layout.rv_text1, mList);
            mRv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<User> list = adapter.getData();
                User user = list.get(position);
                Toast.makeText(getContext(), user.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void btnClear() {
        new AppExecutors().getmDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = AppDataBase.getInstance(getContext()).getUserDao();
                List<User> allUser = userDao.getAllUser();
                for (int i = 0; i < allUser.size(); i++) {
                    userDao.delete(allUser.get(i));
                    LogUtils.d(TAG, allUser.get(i).toString() + "--删除成功");
                }
                LogUtils.d(TAG, "数据删除完成");
            }
        });
    }

    /**
     * Room会直接替换原来的数据
     * 备注: SQLite将@Insert(onConflict = REPLACE)作为REMOVE和REPLACE的集合来操作, 而非单独的UPDATE操作. 这个取代冲突值的方法能够影响你的外键约束.
     */
    private void btnUpdate() {
        new AppExecutors().getmDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = AppDataBase.getInstance(getContext()).getUserDao();
                userDao.update(new User(1, "name_update", "description update"));
                LogUtils.d(TAG, "数据更新完成");
            }
        });
    }

    class UserAdapter extends BaseQuickAdapter<User, BaseViewHolder> {
        public UserAdapter(int layoutResId, @Nullable List<User> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, User item) {
            helper.setText(R.id.tv_text, item.toString());
        }
    }
}
