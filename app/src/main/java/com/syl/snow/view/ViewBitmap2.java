package com.syl.snow.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.syl.snow.R;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Created by Bright on 2019/1/12.
 *
 * @Describe 绘制bitmap2,canvas.drawXXX+BitmapShader,修剪绘制图片
 * @Called
 */
public class ViewBitmap2 extends View {

    private Bitmap mBitmap;
    private Paint mPaint;
    private Matrix mMatrix;
    private BitmapShader mBitmapShader;

    public ViewBitmap2(Context context) {
        this(context, null);
    }

    public ViewBitmap2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewBitmap2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mm1);
        mMatrix = new Matrix();
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*------------------ bitmap -----------------*/
//        canvas.drawBitmap(mBitmap,100,200,mPaint);//可以指定绘制位置,但是没办法设置图片的绘制位置
        mPaint.setShader(mBitmapShader);
        mMatrix.postScale(0.2f, 0.2f);
//        canvas.drawBitmap(mBitmap, mMatrix, mPaint);//从画布的左上角开始绘制
        canvas.setMatrix(mMatrix);//使用矩阵将图片缩小
        canvas.drawCircle(mBitmap.getWidth() / 2, mBitmap.getWidth() / 2, mBitmap.getWidth() / 2, mPaint);

        mMatrix.postTranslate(0, mBitmap.getHeight() * 0.2f);
        canvas.setMatrix(mMatrix);
        canvas.drawArc(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), 0, 360f, false, mPaint);

        mMatrix.postTranslate(mBitmap.getWidth()*0.3f, 0);
        canvas.setMatrix(mMatrix);
        canvas.drawRoundRect(0,0,mBitmap.getWidth(),mBitmap.getHeight(),mBitmap.getWidth()*0.1f,mBitmap.getHeight()*0.1f,mPaint);

        mMatrix.postTranslate(0, -mBitmap.getHeight() * 0.2f);
        canvas.setMatrix(mMatrix);
        canvas.drawOval(0,0,mBitmap.getWidth(),mBitmap.getHeight(),mPaint);
    }
}
