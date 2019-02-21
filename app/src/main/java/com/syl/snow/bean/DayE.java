package com.syl.snow.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by Bright on 2019/2/21.
 *
 * @Describe 日历控件每天的bean
 * @Called
 */
public class DayE implements Serializable, MultiItemEntity {
    private String day;
    private String msg1;
    private String msg2;
    private int itemType;

    public DayE() {
    }

    @Override
    public String toString() {
        return "DayE{" +
                "day='" + day + '\'' +
                ", msg1='" + msg1 + '\'' +
                ", msg2='" + msg2 + '\'' +
                '}';
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
