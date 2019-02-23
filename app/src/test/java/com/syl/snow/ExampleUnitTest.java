package com.syl.snow;

import com.alibaba.fastjson.JSONArray;
import com.syl.snow.config.Constant;

import org.junit.Test;

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
        int gapCount = getBetweenDays(new Date(2019, 1, 1), new Date(2019, 2, 1));
        System.out.println(gapCount);
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