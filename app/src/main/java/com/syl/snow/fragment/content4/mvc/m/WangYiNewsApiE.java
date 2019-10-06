package com.syl.snow.fragment.content4.mvc.m;

import java.io.Serializable;

/**
 * Created by Bright on 2019/10/6.
 *
 * @Describe
 * @Called
 */
public class WangYiNewsApiE implements Serializable {
    private int code;
    private String message;
    private String result;

    public WangYiNewsApiE() {
    }

    @Override
    public String toString() {
        return "WangYiNewsApiE{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
