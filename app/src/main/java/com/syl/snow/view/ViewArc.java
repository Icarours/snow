package com.syl.snow.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/1/12.
 *
 * @Describe 椭圆, 扇形
 * @Called
 */
public class ViewArc extends View {

    private Paint mPaint;
    private RectF mRectF;
    private Random mRandom;

    public ViewArc(Context context) {
        this(context, null);
    }

    public ViewArc(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewArc(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRandom = new Random();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        mRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRectF.left = 100f;
        mRectF.top = 100f;
        mRectF.right = 500f;
        mRectF.bottom = 300f;
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        canvas.drawArc(mRectF, -30f, -120f, true, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        mRectF.left = 100f;
        mRectF.top = 400f;
        mRectF.right = 500f;
        mRectF.bottom = 600f;
        canvas.drawArc(mRectF, -30f, -120f, true, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        mRectF.left = 600f;
        mRectF.top = 100f;
        mRectF.right = 1000f;
        mRectF.bottom = 400f;
        canvas.drawArc(mRectF, -30f, -120f, true, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        mRectF.left = 600f;
        mRectF.top = 400f;
        mRectF.right = 1000f;
        mRectF.bottom = 600f;
        canvas.drawArc(mRectF, 30f, 120f, true, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        mRectF.left = 600f;
        mRectF.top = 650f;
        mRectF.right = 1000f;
        mRectF.bottom = 850f;
        canvas.drawArc(mRectF, 0f, 270f, true, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        mRectF.left = 100f;
        mRectF.top = 650f;
        mRectF.right = 500f;
        mRectF.bottom = 850f;
        canvas.drawArc(mRectF, 0f, -90f, true, mPaint);
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        canvas.drawArc(mRectF, -90f, -60f, true, mPaint);
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        canvas.drawArc(mRectF, 0f, 120f, true, mPaint);
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        canvas.drawArc(mRectF, 150f, 30f, true, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mRectF.left = 100f;
        mRectF.top = 900f;
        mRectF.right = 500f;
        mRectF.bottom = 1100f;
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        canvas.drawArc(mRectF, 0f, 360f, true, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20f);
        mRectF.left = 600f;
        mRectF.top = 900f;
        mRectF.right = 1000f;
        mRectF.bottom = 1100f;
        mPaint.setARGB(mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250),mRandom.nextInt(250));
        canvas.drawArc(mRectF, 0f, 360f, true, mPaint);

        //延迟1秒刷新界面
        postInvalidateDelayed(1000);
    }
}
