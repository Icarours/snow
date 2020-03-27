package com.syl.snow.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.syl.snow.utils.LogUtils;

/**
 * @author Bright
 * @date 2020/3/27 11:58
 * @describe
 */
public class MyReceiver4 extends BroadcastReceiver {
    private static final String TAG = MyReceiver4.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuffer sb = new StringBuffer();
        sb.append(TAG)
                .append("-----收到本地广播")
                .append(intent.getStringExtra("msg1"))
                .append(intent.getStringExtra("msg2"));
        LogUtils.d(TAG, sb.toString());
    }
}
