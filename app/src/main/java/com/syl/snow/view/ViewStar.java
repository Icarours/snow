package com.syl.snow.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/1/13.
 *
 * @Describe 别人的代码,innerRadius是啥,没搞明那你
 * @Called
 */
public class ViewStar extends View {

    private Paint mPaint;

    public ViewStar(Context context) {
        this(context, null);
    }

    public ViewStar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewStar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);

        drawStar(canvas, mPaint, Color.RED, getHeight() / 12, 3, false);
        canvas.translate(getHeight() / 6, 0);
        drawStar(canvas, mPaint, Color.RED, getHeight() / 12, 3, true);
        canvas.translate(-getHeight() / 6, 0);
        canvas.translate(0, getHeight() / 6);
        drawStar(canvas, mPaint, Color.YELLOW, getHeight() / 12, 4, false);
        canvas.translate(getHeight() / 6, 0);
        drawStar(canvas, mPaint, Color.YELLOW, getHeight() / 12, 4, true);
        canvas.translate(-getHeight() / 6, 0);
        canvas.translate(0, getHeight() / 6);
        drawStar(canvas, mPaint, Color.GREEN, getHeight() / 12, 5, false);
        canvas.translate(getHeight() / 6, 0);
        drawStar(canvas, mPaint, Color.GREEN, getHeight() / 12, 5, true);
        canvas.translate(-getHeight() / 6, 0);
        canvas.translate(0, getHeight() / 6);
        drawStar(canvas, mPaint, Color.CYAN, getHeight() / 12, 6, false);
        canvas.translate(getHeight() / 6, 0);
        drawStar(canvas, mPaint, Color.CYAN, getHeight() / 12, 6, true);
        canvas.translate(-getHeight() / 6, 0);
        canvas.translate(0, getHeight() / 6);
        drawStar(canvas, mPaint, Color.BLUE, getHeight() / 12, 7, false);
        canvas.translate(getHeight() / 6, 0);
        drawStar(canvas, mPaint, Color.BLUE, getHeight() / 12, 7, true);
        canvas.translate(-getHeight() / 6, 0);
        canvas.translate(0, getHeight() / 6);
        drawStar(canvas, mPaint, Color.BLACK, getHeight() / 12, 8, false);
        canvas.translate(getHeight() / 6, 0);
        drawStar(canvas, mPaint, Color.BLACK, getHeight() / 12, 8, true);
        canvas.translate(0, getHeight() / 6);

    }

    /**
     * 绘制彩色多边形或星形
     *
     * @param canvas Canvas画布
     * @param paint  Paint画笔
     * @param color  颜色
     * @param radius 外接圆半径
     * @param count  外顶点数
     * @param isStar 是否为星形
     */
    private void drawStar(Canvas canvas, Paint paint, @ColorInt int color, float radius, int count, boolean isStar) {
        canvas.translate(radius, radius);
        if ((!isStar) && count < 3) {
            canvas.translate(-radius, -radius);
            return;
        }
        if (isStar && count < 5) {
            canvas.translate(-radius, -radius);
            return;
        }
        canvas.rotate(-90);
        if (paint == null) {
            paint = new Paint();
        } else {
            paint.reset();
        }
        Path path = new Path();
        //innerRadius是啥,没搞明那你
        float innerRadius = count%2==0?
                (radius*(cos(360/count/2)-sin(360/count/2)*sin(90-360/count)/cos(90-360/count)))
                :(radius*sin(360/count/4)/sin(180-360/count/2-360/count/4));
//        float innerRadius = radius * cos(360 / count / 2);//内接多边形的内接圆
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                path.moveTo(radius * cos(360 / count * i), radius * sin(360 / count * i));
            } else {
                path.lineTo(radius * cos(360 / count * i), radius * sin(360 / count * i));
            }
            if (isStar) {
                path.lineTo(innerRadius * cos(360 / count * i + 360 / count / 2), innerRadius * sin(360 / count * i + 360 / count / 2));
            }
        }
        path.close();
        paint.setColor(color);
        canvas.drawPath(path, paint);
        canvas.rotate(90);
        canvas.translate(-radius, -radius);
    }

    /**
     * Math.sin的参数为弧度，使用起来不方便，重新封装一个根据角度求sin的方法
     *
     * @param num 角度
     * @return
     */
    float sin(int num) {
        return (float) Math.sin(num * Math.PI / 180);
    }

    /**
     * 与sin同理
     */
    float cos(int num) {
        return (float) Math.cos(num * Math.PI / 180);
    }
}
