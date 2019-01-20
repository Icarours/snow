package com.syl.snow.base;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by Bright on 2019/1/19.
 *
 * @Describe
 * @Called
 */

public class MainThreadExecutor implements Executor {
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void execute(Runnable command) {
        handler.post(command);
    }
}