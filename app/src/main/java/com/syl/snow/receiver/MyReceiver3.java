package com.syl.snow.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.syl.snow.utils.LogUtils;

/**
 * @author Bright
 * @date 2020/3/26 15:08
 * @describe
 */
public class MyReceiver3 extends BroadcastReceiver {
    private static final String TAG = MyReceiver3.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        setResultData("国家发放补贴400");
        StringBuffer sb = new StringBuffer();
        sb.append(TAG)
                .append("-----")
                .append(resultData)
                .append(intent.getStringExtra("msg1"))
                .append(intent.getStringExtra("msg2"));
        LogUtils.d(TAG,sb.toString());
    }
}
