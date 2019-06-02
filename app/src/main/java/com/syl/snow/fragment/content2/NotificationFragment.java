package com.syl.snow.fragment.content2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.NotificationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/6/2.
 *
 * @Describe
 * @Called
 */
public class NotificationFragment extends BaseFragment {
    @Bind(R.id.btn_notification1)
    Button mBtnNotification1;
    @Bind(R.id.btn_notification2)
    Button mBtnNotification2;
    private NotificationUtils mNotificationUtils;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, rootView);
        mNotificationUtils = new NotificationUtils(getContext());
        return rootView;
    }

    @OnClick({R.id.btn_notification1, R.id.btn_notification2,})
    public void onViewClick(View view) {
        switch (view.getId()) {
            default:
            case R.id.btn_notification1:
                //如果不指定notifyId,就不会跳到主页面MainActivity
                mNotificationUtils.sendNotification("test1","test1 content");
                break;
            case R.id.btn_notification2:
                mNotificationUtils.sendNotification(2,"test2","test2 content");
                break;
        }
    }
}
