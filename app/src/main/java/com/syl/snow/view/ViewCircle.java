package com.syl.snow.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Created by Bright on 2019/1/10.
 *
 * @Describe 自定义View, 圆
 * @Called
 */
public class ViewCircle extends View {
    private Paint mPaint;//画笔
    private RectF mRectF;//矩形

    public ViewCircle(Context context) {
        this(context, null);
    }

    public ViewCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //创建画笔,图形,最好放在局部变量位置,因为这些对象如果放在OnDraw(),onLayout()方法中频繁调用会影响性能
        mPaint = new Paint();
        mRectF = new RectF();
    }

    @SuppressLint("DrawAllocation")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*------------------ 圆 -----------------*/
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setARGB(255, 255, 0, 0);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        canvas.drawCircle(600, 600, 380, mPaint);

        /*------------------ 矩形 -----------------*/
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setARGB(255, 0, 255, 0);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        canvas.drawRect(100, 50, 500, 350, mPaint);
        canvas.drawRoundRect(150, 100, 400, 300, 10, 10, mPaint);

        /*------------------ 椭圆 -----------------*/
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setARGB(255, 0, 0, 255);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        canvas.drawOval(50, 500, 550, 700, mPaint);
        mRectF.left = 160;
        mRectF.top = 760;
        mRectF.right = 840;
        mRectF.bottom = 1160;
        canvas.drawOval(mRectF, mPaint);

        /*------------------ 点 -----------------*/
//        canvas.drawColor(Color.WHITE);//清空画布
        mPaint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(50, 50);
        path.lineTo(1000, 50);
        canvas.drawPath(path, mPaint);
        canvas.drawPoint(540, 100, mPaint);
        mPaint.setARGB(255, 0, 255, 255);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        float[] pts = {0, 0,
                100, 200, 100, 250, 100, 300,
                150, 200, 150, 250, 150, 300,
                200, 200, 200, 250, 200, 300};
        canvas.drawPoints(pts, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
