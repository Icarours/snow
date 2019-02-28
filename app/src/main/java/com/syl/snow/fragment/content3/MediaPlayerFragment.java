package com.syl.snow.fragment.content3;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.LogUtils;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/2/28.
 *
 * @Describe MediaPlayer播放网络音乐
 * @Called
 */
public class MediaPlayerFragment extends BaseFragment {
    private static final String TAG = MediaPlayerFragment.class.getSimpleName();
    @Bind(R.id.btn_start)
    Button mBtnStart;
    @Bind(R.id.btn_stop)
    Button mBtnStop;
    private MediaPlayer mMediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media_player, container, false);
        ButterKnife.bind(this, rootView);

        try {
            mMediaPlayer = new MediaPlayer();
            String url = "http://59.175.153.46:8068/EmpPicUpload/musicList/%E9%99%88%E9%B8%BF%E5%AE%87-%E7%90%86%E6%83%B3%E4%B8%89%E6%97%AC.mp3";
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mBtnStart.setText("准备完成,可以播放");
                    mBtnStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMediaPlayer.start();
                        }
                    });
                }
            });
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (mMediaPlayer != null) {
                        mMediaPlayer.release();
                        mMediaPlayer = null;
                    }
                }
            });
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootView;
    }


    @OnClick({R.id.btn_start, R.id.btn_stop})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_stop:
                Toast.makeText(getContext(), "btn_stop was clicked..", Toast.LENGTH_SHORT).show();
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    int duration = mMediaPlayer.getDuration();
                    LogUtils.d(TAG, "duration==" + duration);
                    MediaPlayer.TrackInfo[] trackInfos = mMediaPlayer.getTrackInfo();
                    for (MediaPlayer.TrackInfo info :
                            trackInfos) {
                        LogUtils.d(TAG, info.toString());
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
