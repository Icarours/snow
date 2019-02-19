package com.syl.snow.fragment.content1;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.LogUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/2/19.
 *
 * @Describe 简易画板
 * @Called
 */
public class DrawBoardFragment extends BaseFragment implements SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    private static final String TAG = DrawBoardFragment.class.getSimpleName();
    @Bind(R.id.btn_red)
    Button mBtnRed;
    @Bind(R.id.btn_green)
    Button mBtnGreen;
    @Bind(R.id.btn_blue)
    Button mBtnBlue;
    @Bind(R.id.btn_save)
    Button mBtnSave;
    @Bind(R.id.iv)
    ImageView mIv;
    @Bind(R.id.btn_clear)
    Button mBtnClear;
    @Bind(R.id.seekBar)
    SeekBar mSeekBar;
    private Paint mPaint;
    private float mDownX;
    private float mDownY;
    private Bitmap mBitmap;
    private Canvas mCanvas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_draw_board, container, false);
        ButterKnife.bind(this, rootView);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        mPaint.setStrokeWidth(5f);
        mPaint.setColor(0x33ff0000);//设置默认颜色
        mIv.setOnTouchListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);

        //保证控件mIvImg绘制完成,注册全局监听
        ViewTreeObserver viewTreeObserver = mIv.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mBitmap = Bitmap.createBitmap(mIv.getWidth(), mIv.getHeight(), Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mBitmap);
                mCanvas.drawARGB(255, 255, 255, 255);//先绘制一层白色,否则,保存得到图片的背景是黑色的
                mIv.getViewTreeObserver().removeOnGlobalLayoutListener(this);//注销自己
            }
        });
        return rootView;
    }

    @OnClick({R.id.btn_red, R.id.btn_green, R.id.btn_blue, R.id.btn_save, R.id.btn_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_red:
                mPaint.setColor(Color.RED);
                break;
            case R.id.btn_green:
                mPaint.setColor(Color.GREEN);
                break;
            case R.id.btn_blue:
                mPaint.setColor(Color.BLUE);
                break;
            case R.id.btn_save:
                saveImg();
                break;
            case R.id.btn_clear:
                mCanvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);//清空画布
                break;
            default:
                break;
        }
    }

    /**
     * 保存图片
     */
    private void saveImg() {
        File file = new File("/mnt/sdcard/" + System.currentTimeMillis() + ".jpg");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            LogUtils.d(TAG, "file==" + file.getAbsolutePath());
            Intent intent = new Intent();
            //搜索一遍文件,保证新保存的文件出现在文件夹中
            intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            getActivity().sendBroadcast(intent);
            Toast.makeText(getContext(), "图片保存成功..", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();//获得当前的x轴点
                mDownY = event.getY();//获得当前的y轴点
                // Log.d(this.getClass().getSimpleName(), "ACTION_DOWN---mDownX==" + mDownX + "---mDownY===" + mDownY);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                mCanvas.drawLine(mDownX, mDownY, moveX, moveY, mPaint);
                mIv.setImageBitmap(mBitmap);

                //再次赋值起始位置
                mDownX = moveX;
                mDownY = moveY;
                // Log.d(this.getClass().getSimpleName(), "ACTION_MOVE---mDownX==" + mDownX + "---mDownY===" + mDownY +
                //        "moveX==" + moveX + "---moveY===" + moveY);
                break;
            case MotionEvent.ACTION_UP:
                //Log.d(this.getClass().getSimpleName(), "ACTION_UP");
                break;
            default:
                break;
        }
        //返回值是true代表自己消费touch事件,不再向下或者向上传递
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //设置画笔宽度,
        mPaint.setStrokeWidth(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
