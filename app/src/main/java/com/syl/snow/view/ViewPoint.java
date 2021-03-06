package com.syl.snow.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/1/12.
 *
 * @Describe 点阵
 * @Called
 */
public class ViewPoint extends View {
    //画笔
    private Paint mPaint;
    //点间隔(步长)
    private int step = 15;
    //生成随机数
    private Random random ;
    //points点数组
    private float[] mPoints;
    //是否第一次绘制
    private boolean isFrist = true;

    public ViewPoint(Context context) {
        this(context,null);
    }

    public ViewPoint(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewPoint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        random = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //同步块
        synchronized (this) {
            if (isFrist) {
                //获取X轴上要画多少个点
                int xCount = getWidth() / step;
                //获取Y轴上要画多少个点
                int yCount = getHeight() / step;
                //初始化数组，一个点要两个值(x,y)
                mPoints = new float[xCount * yCount * 2];
                //填充数组
                for (int j = 0; j < yCount; j++)
                    for (int i = 0; i < xCount * 2; i++) {
                        if (i % 2 == 0)//绘制横坐标
                            mPoints[j * xCount * 2 + i] = i / 2 * step;
                        else//绘制纵坐标
                            mPoints[j * xCount * 2 + i] = j * step;

                    }
                isFrist = !isFrist;
            }
            mPaint.setARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));

            //用给出的坐标点数组画出所有的点
            canvas.drawPoints(mPoints, mPaint);
            //从50行开始,连续20行
            mPaint.setARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
            canvas.drawPoints(mPoints, 50*getWidth() / step, 20*getWidth() / step, mPaint);
            //延迟1秒刷新界面
            postInvalidateDelayed(1000);
        }
    }
}
