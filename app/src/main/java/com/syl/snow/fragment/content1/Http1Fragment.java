package com.syl.snow.fragment.content1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.BaseApi;
import com.syl.snow.bean.WarnMessage;
import com.syl.snow.utils.LogUtils;
import com.syl.snow.utils.StringUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2018/12/8.
 *
 * @Describe
 * @Called
 */
public class Http1Fragment extends BaseFragment {
    private static final String TAG = Http1Fragment.class.getSimpleName();
    @Bind(R.id.btn_http1)
    Button mBtnHttp1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_http1, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn_http1})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_http1:
                Toast.makeText(getContext(), "http1 was clicked..", Toast.LENGTH_SHORT).show();
                new Thread() {
                    @Override
                    public void run() {
                        readContentFromPost();
                    }
                }.start();
                break;
        }
    }

    //    String url1 = "http://cloud.lanlyc.cn/new_gongdi/warnMessage/getWarnMessageList?paramJson=";
    String url1 = "http://cloud.lanlyc.cn/new_gongdi/warnMessage/getWarnMessageList";

    HttpURLConnection connection;

    public void readContentFromPost() {
        // Post请求的url，与get不同的是不需要带参数
        URL postUrl = null;
        try {
            postUrl = new URL(url1);

            // 打开连接
            connection = (HttpURLConnection) postUrl.openConnection();
            // 设置是否向connection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true
            connection.setDoOutput(true);
            // Read from the connection. Default is true.
            connection.setDoInput(true);
            // 默认是 GET方式
            connection.setRequestMethod("POST");
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            //设置本次连接是否自动重定向
            connection.setInstanceFollowRedirects(true);
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            // 意思是正文是urlencoded编码过的form参数
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());
            // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
            //{"pageNumber":"1","pageSize":"16"}
            String content = "paramJson=" + URLEncoder.encode("{\"pageNumber\":\"1\",\"pageSize\":\"16\"}", "UTF-8");
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
            out.writeBytes(content);
            //流用完记得关
            out.flush();
            out.close();

            //获取响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                String s = StringUtils.inputStream2String(inputStream);
//                LogUtils.d(TAG, "result==" + s);
                BaseApi baseApi = JSONObject.parseObject(s, BaseApi.class);
                if (baseApi.isOk()) {
                    String data = baseApi.getData();
                    if (data != null && !"".equals(data)) {
                        List<WarnMessage> warnMessages = JSONObject.parseArray(data, WarnMessage.class);
                        LogUtils.d(TAG, "size==" + warnMessages.size());
                        for (int i = 0; i < warnMessages.size(); i++) {
                            LogUtils.d(TAG, warnMessages.get(i).toString() + "-------" + i);
                        }
                    } else {
                        LogUtils.d(TAG, "data 数据集为空");
                    }
                } else {
                    LogUtils.d(TAG, "code 返回值不是200");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            LogUtils.d(TAG, "url 格式不对");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LogUtils.d(TAG, "不支持这种编码格式");
        } catch (ProtocolException e) {
            e.printStackTrace();
            LogUtils.d(TAG, "传输协议异常");
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.d(TAG, "io读写异常");
        } finally {
            //该干的都干完了,记得把连接断了
            connection.disconnect();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
