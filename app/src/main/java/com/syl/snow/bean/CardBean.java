package com.syl.snow.bean;


import com.syl.snow.interfaces.IPickerViewData;

/**
 * Created by KyuYi on 2017/3/2.
 * E-Mail:kyu_yi@sina.com
 * 功能：
 *
 * 不知道为啥,升级到as3.4之后,这个第三方接口就没了,我只好自己写了个,但是使用的时候出现的是地址值
 */

public class CardBean implements IPickerViewData {
    int id;
    String cardNo;

    public CardBean(int id, String cardNo) {
        this.id = id;
        this.cardNo = cardNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String getPickerViewText() {
        return cardNo;
    }

}

