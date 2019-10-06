package com.syl.snow.fragment.content4.mvc.v;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.utils.LogUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * author   Bright
 * date     2019/10/6 10:41
 * desc
 * 网易新闻详情
 * WebView
 */
public class WangYiNewsActivity extends AppCompatActivity {
    private static final String TAG = WangYiNewsActivity.class.getSimpleName();
    @Bind(R.id.webView)
    WebView mWebView;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private Activity mActivity = WangYiNewsActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wang_yi_news);
        ButterKnife.bind(this);
        mToolbar.setNavigationOnClickListener(v -> finish());
        String path = getIntent().getStringExtra("path");
        LogUtils.d(TAG, path);
        mWebView.loadUrl(path);
//        mWebView.loadUrl("https://www.baidu.com/");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    /**
     * 重写onKeyDown,避免一按返回键就直接调用finish方法,回到上一个Activity.
     * 但是这样一来,返回键貌似就会彻底被屏蔽,只能用ToolBar的导航阿牛返回
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
