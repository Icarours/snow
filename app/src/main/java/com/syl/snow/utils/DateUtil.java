package com.syl.snow.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bright on 2019/4/6.
 *
 * @Describe 时间工具类
 * 注意到上述代码的时间格式存在大小写字母揉合的情况，为避免混淆，有必要对这些格式字符串进行取值说明，详述如下：
 * 小写的yyyy：表示四位年份数字，如1949、2017等等。
 * 大写的MM：表示两位月份数字，如01表示一月份，12表示12月份。
 * 小写的dd：表示两位日期数字，如08表示当月八号，26表示当月二十六号。
 * 大写的HH：表示24小时制的两位小时数字，如19表示晚上七点。
 * 小写的hh：表示12小时制的两位小时数字，如06可同时表示早上六点与傍晚六点；因为12小时制的表达会引发歧义，所以实际开发中很少这么使用。
 * 小写的mm：表示两位分钟数字，如30表示某点三十分。
 * 小写的ss：表示两位秒钟数字。
 * 大写的SSS：表示三位毫秒数字。
 * 其余的横线“-”、空格“ ”、冒号“:”、点号“.”等字符，仅仅是连接符，方便观看各种单位的时间数字而已；对于中文世界来说，也可采用形如“yyyy年MM月dd日HH时mm分ss秒”的格式。现在使用Kotlin的扩展函数，无需声明专门的DateUtil工具类，直接写几个系统日期Date类的扩展函数，即可实现日期时间格式转换的功能，改写后的Date类扩展函数举例如下：
 * ---------------------
 * 作者：湖前琴亭
 * 来源：CSDN
 * 原文：https://blog.csdn.net/aqi00/article/details/82794535
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
public class DateUtil {
    //获取当前完整的日期和时间
    public static String getNowDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    //获取当前时间
    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    //获取当前时间（精确到毫秒）
    public static String getNowTimeDetail() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        return sdf.format(new Date());
    }
}
