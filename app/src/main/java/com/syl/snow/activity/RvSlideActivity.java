package com.syl.snow.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import com.syl.snow.R;
import com.syl.snow.view.rv_itm_slide.SlideAdapter;
import com.syl.snow.view.rv_itm_slide.SlideRecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2019/4/26 16:33
 * desc
 * 自定义RecyclerView侧滑菜单
 */
public class RvSlideActivity extends AppCompatActivity {

    @Bind(R.id.rv)
    SlideRecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_slide);
        ButterKnife.bind(this);

        SlideAdapter adapter = new SlideAdapter(this);
        mRv.setAdapter(adapter);
        mRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 2;
                outRect.bottom = 2;
            }
        });
        //监听RecyclerView绘制完成
        ViewTreeObserver viewTreeObserver = mRv.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //需要在RecyclerView绘制完成后进行的操作
                // TODO: 2019/4/26
                //移除监听
                mRv.getViewTreeObserver().removeOnGlobalLayoutListener(this::onGlobalLayout);
            }
        });
    }
}
