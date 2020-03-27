package com.syl.snow.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseActivity;
import com.syl.snow.bean.TitleBean;
import com.syl.snow.receiver.MyReceiver1;
import com.syl.snow.receiver.MyReceiver2;
import com.syl.snow.receiver.MyReceiver3;
import com.syl.snow.receiver.MyReceiver4;
import com.syl.snow.receiver.MyReceiver5;
import com.syl.snow.utils.LogUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Bright
 * @date 2020/3/26 11:51
 * @describe 发送广播
 * @called
 */
public class BroadcastReceiverActivity extends BaseActivity {
    private static final String TAG = BroadcastReceiverActivity.class.getSimpleName();
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    public final String RECEIVER_NORMAL = "com.syl.snow.receiver_normal";
    public final String RECEIVER_ORDER = "com.syl.snow.receiver_order";
    public final String RECEIVER_LOCAL = "com.syl.snow.receiver_local";
    private BroadcastReceiver mBroadcastReceiver;
    private MyReceiver1 mMyReceiver1;
    private MyReceiver2 mMyReceiver2;
    private MyReceiver3 mMyReceiver3;
    private MyReceiver4 mMyReceiver4;
    private MyReceiver5 mMyReceiver5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        ButterKnife.bind(this);
        TitleBean titleBean = (TitleBean) getIntent().getSerializableExtra("title");
        initToolBar(mToolbar, titleBean);
        findViewById(R.id.btn_broadcast_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAllReceiver();
            }
        });
        findViewById(R.id.btn_broadcast1).setOnClickListener(v -> {
            Intent intent = new Intent(RECEIVER_NORMAL);
            intent.putExtra("msg1", "value1");
            intent.putExtra("msg2", "标准广播");
            sendBroadcast(intent);
            LogUtils.d(TAG, "发送标准广播");
        });
        findViewById(R.id.btn_broadcast2).setOnClickListener(v -> {
            Intent intent = new Intent(RECEIVER_ORDER);
            intent.putExtra("msg1", "value1");
            intent.putExtra("msg2", "有序广播");
            //如果receiverPermission的值不设置为null,BroadcastReceiver可能就收不到广播
//            sendOrderedBroadcast(intent,null);
            //发送一个有序广播
            //sendOrderedBroadcast(Intent intent,  //发送广播的意图对象（可以携带数据）
            //          String receiverPermission, //接收权限（如果为空，则不需要权限）
            //          BroadcastReceiver resultReceiver, //广播接收者对象(自己创建的最终的广播接收者，可以无须在清单文件中配置，也会接收到广播 ),最终接收者不能修改广播中的信息
            //          Handler scheduler,         //若传null，则默认是在主线程中
            //          int initialCode,           //初始化的一个值。可默认：Activity.RESULT_OK
            //          String initialData,        //可发送的初始化数据（相当于一条广播数据）。可为null
            //          Bundle initialExtras)      //可绑定数据传递（Intent对象也可以，所以可为null）
//            sendOrderedBroadcast(intent,null,new MyReceiver1(),null, Activity.RESULT_OK,"广播携带的信息:国家发放补贴2000",null);
            sendOrderedBroadcast(intent, null, null, null, Activity.RESULT_OK, "广播携带的信息:国家发放补贴2000", null);
            LogUtils.d(TAG, "发送有序广播");
        });
        findViewById(R.id.btn_broadcast3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RECEIVER_LOCAL);
                intent.putExtra("msg1", "value1");
                intent.putExtra("msg2", "本地广播-同步");
                //发送本地广播
                LocalBroadcastManager.getInstance(BroadcastReceiverActivity.this).sendBroadcast(intent);
                LogUtils.d(TAG, "发送本地广播-同步");
            }
        });
        findViewById(R.id.btn_broadcast4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RECEIVER_LOCAL);
                intent.putExtra("msg1", "value1");
                intent.putExtra("msg2", "本地广播-异步");
                //发送本地广播
                LocalBroadcastManager.getInstance(BroadcastReceiverActivity.this).sendBroadcastSync(intent);
                LogUtils.d(TAG, "发送本地广播-异步");
            }
        });
    }

    private void registerAllReceiver() {
        //注册标准广播
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                StringBuffer sb = new StringBuffer();
                sb.append(TAG)
                        .append("-----")
                        .append(intent.getStringExtra("msg1"))
                        .append(intent.getStringExtra("msg2"));
                LogUtils.d(TAG, sb.toString());
            }
        };
        registerReceiver(mBroadcastReceiver, new IntentFilter(RECEIVER_NORMAL));

        //注册有序广播
        mMyReceiver1 = new MyReceiver1();
        IntentFilter filter1 = new IntentFilter(RECEIVER_ORDER);
        filter1.setPriority(990);
        registerReceiver(mMyReceiver1, filter1);
        mMyReceiver2 = new MyReceiver2();
        IntentFilter filter2 = new IntentFilter(RECEIVER_ORDER);
        filter2.setPriority(900);
        registerReceiver(mMyReceiver2, filter2);
        mMyReceiver3 = new MyReceiver3();
        IntentFilter filter3 = new IntentFilter(RECEIVER_ORDER);
        filter3.setPriority(800);
        registerReceiver(mMyReceiver3, filter3);

        //注册本地广播
        mMyReceiver4 = new MyReceiver4();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMyReceiver4, new IntentFilter(RECEIVER_LOCAL));
        mMyReceiver5 = new MyReceiver5();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMyReceiver5, new IntentFilter(RECEIVER_LOCAL));

        LogUtils.d(TAG, "注册完成所有广播");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
        unregisterReceiver(mMyReceiver1);
        unregisterReceiver(mMyReceiver2);
        unregisterReceiver(mMyReceiver3);
        //解注册本地广播
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMyReceiver4);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMyReceiver5);
    }
}
