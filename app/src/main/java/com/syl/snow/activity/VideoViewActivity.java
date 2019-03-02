package com.syl.snow.activity;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

import com.syl.snow.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2019/3/2 14:58
 * desc
 * 使用VideoView播放视频,本地视频,网络视屏
 */
public class VideoViewActivity extends AppCompatActivity {
    @Bind(R.id.video_view)
    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_vide_view);
        ButterKnife.bind(this);
        //使用MediaController之后全屏失效
        mVideoView.setMediaController(new MediaController(this));
        int pathType = getIntent().getIntExtra("path_type", 1);
        setupVideo();
        if (1 == pathType) {
            btnLocal();
        } else if (2 == pathType) {
            btnNet();
        }
    }

    /**
     * 播放本地视频
     */
    private void btnLocal() {
        try {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.video_480x360_h264);
            mVideoView.setVideoURI(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放网络视屏
     */
    private void btnNet() {
        String url = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        mVideoView.setVideoPath(url);
    }

    private void setupVideo() {
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlaybackVideo();
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                stopPlaybackVideo();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mVideoView.isPlaying()) {
            mVideoView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView.canPause()) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlaybackVideo();
    }

    private void stopPlaybackVideo() {
        try {
            mVideoView.stopPlayback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
