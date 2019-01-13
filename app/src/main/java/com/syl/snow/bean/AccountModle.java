package com.syl.snow.bean;

import android.app.Application;

import com.syl.snow.utils.LogUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by Bright on 2019/1/13.
 *
 * @Describe 继承ViewModel或者其子类AndroidViewModel.并创建MutableLiveData,在成员变量位置
 * @Called
 */
public class AccountModle extends AndroidViewModel {
    private static final String TAG = AccountModle.class.getSimpleName();
    //创建LiveData
    private MutableLiveData<AccountBean> mAccount = new MutableLiveData<>();

    public AccountModle(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<AccountBean> getAccount() {
        return mAccount;
    }

    public void setAccount(MutableLiveData<AccountBean> account) {
        mAccount = account;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtils.d(TAG,"AccountModle --- onCleared()");
    }
}
