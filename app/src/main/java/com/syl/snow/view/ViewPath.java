package com.syl.snow.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/1/13.
 *
 * @Describe 自定义View中的Path
 * @Called
 */
public class ViewPath extends View {

    private Paint mPaint;
    private Path mPath;
    private Paint mPaint2;
    private Path mPath2;
    private Paint mPaint3;
    private Path mPath3;
    private Paint mPaint4;
    private Path mPath4;
    private Random mRandom;

    public ViewPath(Context context) {
        super(context,null);
    }

    public ViewPath(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRandom = new Random();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPath = new Path();

        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPath2 = new Path();

        mPaint3 = new Paint();
        mPaint3.setAntiAlias(true);
        mPath3 = new Path();

        mPaint4 = new Paint();
        mPaint4.setAntiAlias(true);
        mPath4 = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setARGB(mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255));
        mPath.addCircle(200,200,150,Path.Direction.CW);
        mPath.addCircle(350,200,150,Path.Direction.CW);
        mPath.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(mPath,mPaint);

        mPaint2.setARGB(mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255));
        mPath2.addCircle(700,200,150,Path.Direction.CW);
        mPath2.addCircle(850,200,150,Path.Direction.CW);
        mPath2.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        canvas.drawPath(mPath2,mPaint2);

        mPaint3.setARGB(mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255));
        mPath3.addCircle(200,600,150,Path.Direction.CCW);
        mPath3.addCircle(350,600,150,Path.Direction.CCW);
        mPath3.setFillType(Path.FillType.WINDING);
        canvas.drawPath(mPath3,mPaint3);

        //画笔颜色设置必须放在onDraw()方法中,否则不会有刷新页面的效果
        mPaint4.setARGB(mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255));
        mPath4.addCircle(700,600,150,Path.Direction.CW);
        mPath4.addCircle(850,600,150,Path.Direction.CCW);
        mPath4.setFillType(Path.FillType.INVERSE_WINDING);
        canvas.drawPath(mPath4,mPaint4);

        //每隔1s刷新一次界面
        postInvalidateDelayed(1000);
    }
}
