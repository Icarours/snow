package com.syl.snow.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Bright
 * @date 2020/3/28 16:28
 * @describe 线程池
 */
public class ExecutorFragment extends BaseFragment {
    private static final String TAG = ExecutorFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_executor, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_executor1, R.id.btn_executor2, R.id.btn_executor3, R.id.btn_executor4, R.id.btn_executor5, R.id.btn_executor6})
    public void onViewClick(View view) {
        switch (view.getId()) {
            default:
            case R.id.btn_executor1:
                executor1();
                break;
            case R.id.btn_executor2:
                executor2();
                break;
            case R.id.btn_executor3:
                executor3();
                break;
            case R.id.btn_executor4:
                executor4();
                break;
            case R.id.btn_executor5:
                executor5();
                break;
            case R.id.btn_executor6:
                executor6();
                break;
        }
    }

    private void executor1() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        task1(executorService, 2000);
    }


    private void executor2() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        task1(executorService, 1000);
    }

    private void executor3() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        task1(executorService, 2000);
    }

    private void executor4() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//        scheduledExecutorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//                String format = simpleDateFormat.format(new Date());
//                LogUtils.d(TAG, "当前线程---" + Thread.currentThread().getName() + "---日期===" + format + "---任务1==");
//            }
//        }, 2000, TimeUnit.MILLISECONDS);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//                String format = simpleDateFormat.format(new Date());
//                LogUtils.d(TAG, "当前线程---" + Thread.currentThread().getName() + "---日期===" + format + "---任务2");
//            }
//        }, 2, 3, TimeUnit.MINUTES);

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String format = simpleDateFormat.format(new Date());
                LogUtils.d(TAG, "当前线程---" + Thread.currentThread().getName() + "---日期===" + format + "---任务3");
            }
        }, 2, 1, TimeUnit.MINUTES);
    }

    private void executor5() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//                String format = simpleDateFormat.format(new Date());
//                LogUtils.d(TAG, "当前线程---" + Thread.currentThread().getName() + "---日期===" + format + "---任务1==");
//            }
//        }, 2, TimeUnit.MINUTES);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//                String format = simpleDateFormat.format(new Date());
//                LogUtils.d(TAG, "当前线程---" + Thread.currentThread().getName() + "---日期===" + format + "---任务2");
//            }
//        }, 2, 1, TimeUnit.MINUTES);
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String format = simpleDateFormat.format(new Date());
                LogUtils.d(TAG, "当前线程---" + Thread.currentThread().getName() + "---日期===" + format + "---任务3");
            }
        }, 2, 0, TimeUnit.MINUTES);
    }

    private void executor6() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        String format = simpleDateFormat.format(new Date());
//        LogUtils.d(TAG, "当前线程---" + Thread.currentThread().getName() + "---日期===" + format + "---任务" );
        for (int i = 0; i <= 10; i++) {
            int finalI = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    String format = simpleDateFormat.format(new Date());
                    LogUtils.d(TAG, "当前线程---" + Thread.currentThread().getName() + "---日期===" + format + "---任务" + finalI);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    private void task1(ExecutorService executorService, int seconds) {
        for (int i = 0; i <= 10; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    String format = simpleDateFormat.format(new Date());
                    LogUtils.d(TAG, "当前线程---" + Thread.currentThread().getName() + "---日期===" + format + "---任务" + finalI);
                    try {
                        Thread.sleep(seconds);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
