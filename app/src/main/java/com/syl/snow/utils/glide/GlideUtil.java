package com.syl.snow.utils.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.syl.snow.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Bright on 2019/2/17.
 *
 * @Describe Glide工具类
 * @Called
 */
public class GlideUtil {
    private RequestOptions options;
    private Context mContext;

    public GlideUtil(Context context) {
        options = new RequestOptions();
        options.skipMemoryCache(false);
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        options.priority(Priority.HIGH);
        options.error(R.drawable.mm1);
        //设置占位符,默认
        options.placeholder(R.drawable.mm1);
        //设置错误符,默认
        options.error(R.drawable.mm1);
        mContext = context;
    }


    //设置占位符
    public void setPlaceholder(int id) {
        options.placeholder(id);
    }

    public void setPlaceholder(Drawable drawable) {
        options.placeholder(drawable);
    }

    //设置错误符
    public void setError(int id) {
        options.error(id);
    }

    public void setError(Drawable drawable) {
        options.error(drawable);
    }

    public void showImage(String url, ImageView imageView) {

        Glide.with(mContext)
                .load(url)
                .apply(options)
                .into(imageView);

    }

    //以图片宽度为基准
    public void showImageWidthRatio(String url, final ImageView imageView, final int width) {
        Glide.with(mContext)
                .asBitmap()
                .apply(options)
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        int imageWidth = resource.getWidth();
                        int imageHeight = resource.getHeight();
                        int height = width * imageHeight / imageWidth;
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        params.height = height;
                        params.width = width;
                        imageView.setImageBitmap(resource);
                    }
                });
    }

    //以图片高度为基准
    public void showImageHeightRatio(String url, final ImageView imageView, final int height) {
        Glide.with(mContext)
                .asBitmap()
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        int imageWidth = resource.getWidth();
                        int imageHeight = resource.getHeight();
                        int width = height * imageHeight / imageWidth;
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        params.height = height;
                        params.width = width;
                        imageView.setImageBitmap(resource);
                    }
                });
    }

    //设置图片固定的大小尺寸
    public void showImageWH(String url, final ImageView imageView, int height, int width) {

        options.override(width, height);
        Glide.with(mContext)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    //设置图片圆角，以及弧度
    public void showImageRound(String url, final ImageView imageView, int radius) {

        options.transform(new CornersTransform(radius));
//        options.transform(new GlideCircleTransform());
        Glide.with(mContext)
                .load(url)
                .apply(options)
                .into(imageView);

    }

    public void showImageRound(int resId, ImageView imageView) {
        options.circleCrop();
        Glide.with(mContext)
                .load(resId)
                .apply(options)
                .into(imageView);
    }

    public void showImageRound(String url, final ImageView imageView, int radius, int height, int width) {
        //不一定有效，当原始图片为长方形时设置无效
        options.override(width, height);
        options.transform(new CornersTransform(radius));
//        options.centerCrop(); //不能与圆角共存
        if (mContext != null) {
            Glide.with(mContext)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }
    }


    public void showImageRound(String url, final ImageView imageView) {
        //自带圆角方法，显示圆形
        options.circleCrop();
        if (mContext != null) {
            Glide.with(mContext)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }
    }
    /*------------------ 上面是别人的代码 -----------------*/

    /**
     * @author syl
     * create at 2019/8/5
     * description:
     * 加载原型形图片,不需要指定CornersTransform
     */
    public static void loadImage(Activity activity, String imgSrc, ImageView ivTarget, int defaultImg) {
        RequestOptions options = new RequestOptions();
        options.skipMemoryCache(false);
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        options.priority(Priority.HIGH);
        options.error(defaultImg);
        //设置占位符,默认
        options.placeholder(defaultImg);
        //设置错误符,默认
        options.error(defaultImg);

        Glide.with(activity)
                .load(imgSrc)
                .apply(options)
                .into(ivTarget);
    }

    /**
     * @author syl
     * create at 2019/8/5
     * description:
     * 加载普通矩形图片
     */
    public static void loadImageCircle(Activity activity, String imgSrc, ImageView ivTarget, int defaultImg) {
        RequestOptions options = new RequestOptions();
        options.skipMemoryCache(false);
        options.circleCrop();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        options.priority(Priority.HIGH);
        options.error(defaultImg);
        //设置占位符,默认
        options.placeholder(defaultImg);
        //设置错误符,默认
        options.error(defaultImg);

        Glide.with(activity)
                .load(imgSrc)
                .apply(options)
                .into(ivTarget);
    }
}
