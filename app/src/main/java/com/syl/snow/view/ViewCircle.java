package com.syl.snow.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/1/10.
 *
 * @Describe 自定义View,圆
 * @Called
 */
public class ViewCircle extends View {
    public ViewCircle(Context context) {
        super(context);
    }

    public ViewCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setARGB(255,255,0,0);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        canvas.drawCircle(600,600,100,paint);
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
