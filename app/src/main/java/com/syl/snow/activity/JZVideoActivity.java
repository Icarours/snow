package com.syl.snow.activity;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.syl.snow.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerManager;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * author   Bright
 * date     2019/2/19 22:32
 * desc
 * 饺子播放器,有时间自己写个控制器
 */
public class JZVideoActivity extends AppCompatActivity {

    @Bind(R.id.mJC)
    JZVideoPlayerStandard mMJC;
    private JZVideoPlayer.JZAutoFullscreenListener mJzAutoFullscreenListener;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_jzvideo);
        ButterKnife.bind(this);
        JZVideoPlayerManager jzVideoPlayerManager = new JZVideoPlayerManager();
        JZMediaManager jzMediaManager = new JZMediaManager();
        // 2  重力感应切换横竖屏
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mJzAutoFullscreenListener = new JZVideoPlayer.JZAutoFullscreenListener();
        //   3设置图片为全屏
        mMJC.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //4设置内置视频的高度， 可以去除黑边
        JZVideoPlayer.setVideoImageDisplayType(JZVideoPlayer.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
        //5.播放视频
        mMJC.TOOL_BAR_EXIST = false;
        mMJC.setUp("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
                , JZVideoPlayerStandard.SCROLL_AXIS_HORIZONTAL, "这视频真好，去除黑边了");
//        6 视频的缩略图地址
        Glide.with(getApplicationContext()).load("http://p0.so.qhmsg.com/bdr/_240_/t01c10808f98a39bd4f.jpg").into(mMJC.thumbImageView);
//        7播放比例,可以设置为16:9,4:3
        mMJC.widthRatio = 4;
        mMJC.heightRatio = 3;

//        8设置全屏播放
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;  //纵向
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mJzAutoFullscreenListener);
        JZVideoPlayer.releaseAllVideos();
    }


    @Override
    protected void onResume() {
        super.onResume();
//        播放器重力感应
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mJzAutoFullscreenListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

}
