package com.syl.snow.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.syl.snow.R;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/1/12.
 *
 * @Describe 绘制bitmap
 * @Called
 */
public class ViewBitmap extends View {

    private Bitmap mBitmap;
    private Paint mPaint;

    public ViewBitmap(Context context) {
        this(context,null);
    }

    public ViewBitmap(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewBitmap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mm1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*------------------ bitmap -----------------*/
        canvas.drawBitmap(mBitmap,100,200,mPaint);//可以指定绘制位置,但是没办法设置图片的绘制位置
        Matrix matrix = new Matrix();
        matrix.postScale(0.5f,0.5f);
        canvas.drawBitmap(mBitmap,matrix,mPaint);//从画布的左上角开始绘制
    }
}
