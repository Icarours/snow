package com.syl.snow;

import com.alibaba.fastjson.JSONArray;
import com.syl.snow.bean.ImageE;
import com.syl.snow.config.Constant;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//        int gapCount = getBetweenDays(new Date(2019, 1, 1), new Date(2019, 2, 1));
//        System.out.println(gapCount);
//        dateUseful();
//        String str = "22";
//        System.out.println(str.split(",").length);
//        subStringTest1();
//        new Weather();
        System.out.println(System.nanoTime());
        System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
    }

    /**
     * 判断引用数据类型是否相等
     */
    private void fun1() {
        ImageE imageE1 = new ImageE(1,"11");
        ImageE imageE2 = new ImageE(1,"11");
        System.out.println(imageE1.equals(imageE2));
    }

    private void subStringTest1() {
        String string = "123456789";
        System.out.println(string.substring(0,string.length()-1));
    }

    private void dateUseful() {
        String startDate = "2019-03-14 11:12:04";
        String endDate = "2019-12-01 01:02:04";
        DateFormat dateInstance = DateFormat.getDateInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            System.out.println("start==" + start + "--end==" + end);
            boolean b = start.getTime() > end.getTime();
            System.out.println("----"+b);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * post请求json转换为字符串
     */
    private void params() {
        Map<String, String> params = new HashMap<>();
        String url = "http://cloud.lanlyc.cn/new_gongdi/warnMessage/getWarnMessageList";
//        Params params = new Params(pageNumber + "", Constant.PAGE_SIZE);
        params.put(Constant.MOBILE, "15989469069");
        params.put("departmentId", "-1");
//        String string = JSONObject.toJSONString(params);
        System.out.println(JSONArray.toJSONString(params));
    }

    public int[] twoSum(int[] nums, int target) {

        int x = 0, y = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        return new int[]{x, y};
    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getBetweenDays(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }
}