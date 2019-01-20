package com.syl.snow.base;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Bright on 2019/1/19.
 *
 * @Describe 创建常用的线程池
 * @Called
 */
public class AppExecutors {
    private Executor mDiskIO;
    private Executor mNetworkIO;
    private Executor mMainThread;

    public AppExecutors(Executor mDiskIO, Executor mNetworkIO, Executor mMainThread) {
        this.mDiskIO = mDiskIO;
        this.mNetworkIO = mNetworkIO;
        this.mMainThread = mMainThread;
    }

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3), new MainThreadExecutor());
    }

    public Executor getmDiskIO() {
        return mDiskIO;
    }

    public Executor getmNetworkIO() {
        return mNetworkIO;
    }

    public Executor getmMainThread() {
        return mMainThread;
    }
/*
    public static class MainThreadExecutor implements Executor {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    }*/
}
