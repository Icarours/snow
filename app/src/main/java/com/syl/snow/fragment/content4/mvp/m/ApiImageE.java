package com.syl.snow.fragment.content4.mvp.m;

import java.io.Serializable;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 */
public class ApiImageE implements Serializable {
    private int code;
    private String message;
    private String result;

    public ApiImageE() {
    }

    @Override
    public String toString() {
        return "ApiImageE{" +
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
