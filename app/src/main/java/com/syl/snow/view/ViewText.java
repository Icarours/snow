package com.syl.snow.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/1/13.
 *
 * @Describe 绘制文字
 * @Called
 */
public class ViewText extends View {

    private Paint mPaint;
    private Random mRandom;

    public ViewText(Context context) {
        this(context, null);
    }

    public ViewText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRandom = new Random();
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //当前控件的宽高
        int width = getWidth();
        int height = getHeight();
        String text = "View的宽="+width+"--View的高="+height;
        mPaint.setTextSize(18);
        mPaint.setARGB(mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255));
        canvas.drawText(text, 100, 25, mPaint);
        mPaint.setTextSize(36);
        mPaint.setARGB(mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255));
        canvas.drawText(text, 100, 70, mPaint);
        mPaint.setTextSize(48);
        mPaint.setARGB(mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255));
        canvas.drawText(text, 100, 145, mPaint);
        mPaint.setTextSize(60);
        mPaint.setARGB(mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255));
        canvas.drawText(text, 100, 240, mPaint);

        postInvalidateDelayed(1000);
    }
}
