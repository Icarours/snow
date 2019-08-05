package com.syl.snow.fragment.content2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2019/4/13.
 *
 * @Describe
 * @Called
 * 格式化字符串
 */
public class StringFormatFragment2 extends BaseFragment {
    private static final String TAG = StringFormatFragment2.class.getSimpleName();
    @Bind(R.id.btn_format)
    Button mBtnFormat;
    @Bind(R.id.tv_format_result)
    TextView mTvFormatResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_string_format2, container, false);
        ButterKnife.bind(this, rootView);
        mBtnFormat.setText("格式化字符串");
        mTvFormatResult.setText("https://blog.csdn.net/wsc912406860/article/details/82771386");
        return rootView;
    }

    @OnClick(R.id.btn_format)
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_format:
                btnFormat();
                break;
            default:
                break;
        }
    }

    private void btnFormat() {
        String url = "www.xxx.com/s?index=%d";
        for (int i = 1; i <= 5; i++) {
            String format = String.format(url, i);
            System.out.println(format);
        }
        System.out.println("-----------------------------------字符串格式1");
        String str1 = String.format("Hi,%s", "哈士奇");
        System.out.println(str1);
        String str2 = String.format("Hi,%s:%s.%s", "老鹰", "是一种", "鸟类");
        System.out.println(str2);
        System.out.printf("字母h的大写是：%c %n", 'H');
        System.out.printf("12.34>33.22的结果是：%b %n", 12.34 > 33.22);
        System.out.printf("100的一半是：%d %n", 100 / 2);
        System.out.printf("100的16进制数是：%x %n", 100);
        System.out.printf("100的8进制数是：%o %n", 100);
        System.out.printf("100元的书包打8.5折扣是：%f 元%n", 100 * 0.85);
        System.out.printf("100的16进制浮点数是：%a %n", 100 * 0.85);
        System.out.printf("100的指数表示：%e %n", 100 * 0.85);
        System.out.printf("10的指数和浮点数结果的长度较短的是：%g %n", 100 * 0.85);
        System.out.printf("100的折扣是%d%% %n", 85);
        System.out.printf("字母A的散列码是：%h %n", 'A');
        System.out.println("-----------------------------时间日期");
        Date date = new Date();
        //c的使用
        System.out.printf("全部日期和时间信息：%tc%n", date);
        //f的使用
        System.out.printf("年-月-日格式：%tF%n", date);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n", date);
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", date);
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n", date);
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR", date);
        System.out.println("-----------------------时间日期2");
        //H的使用
        System.out.printf("2位数字24时制的小时（不足2位前面补0）:%tH%n", date);
        //I的使用
        System.out.printf("2位数字12时制的小时（不足2位前面补0）:%tI%n", date);
        //k的使用
        System.out.printf("2位数字24时制的小时（前面不补0）:%tk%n", date);
        //l的使用
        System.out.printf("2位数字12时制的小时（前面不补0）:%tl%n", date);
        //M的使用
        System.out.printf("2位数字的分钟（不足2位前面补0）:%tM%n", date);
        //S的使用
        System.out.printf("2位数字的秒（不足2位前面补0）:%tS%n", date);
        //L的使用
        System.out.printf("3位数字的毫秒（不足3位前面补0）:%tL%n", date);
        //N的使用
        System.out.printf("9位数字的毫秒数（不足9位前面补0）:%tN%n", date);
        //p的使用
        String str = String.format(Locale.US, "小写字母的上午或下午标记(英)：%tp", date);
        System.out.println(str);
        System.out.printf("小写字母的上午或下午标记（中）：%tp%n", date);
        //z的使用
        System.out.printf("相对于GMT的RFC822时区的偏移量:%tz%n", date);
        //Z的使用
        System.out.printf("时区缩写字符串:%tZ%n", date);
        //s的使用
        System.out.printf("1970-1-1 00:00:00 到现在所经过的秒数：%ts%n", date);
        //Q的使用
        System.out.printf("1970-1-1 00:00:00 到现在所经过的毫秒数：%tQ%n", date);
    }
}
