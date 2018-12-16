package com.syl.snow.bean;

/**
 * Created by Bright on 2018/12/8.
 *
 * @Describe
 * @Called
 */
public class BaseApi {
    private Integer code;
    private String data;
    private String message;

    public BaseApi() {
    }

    public boolean isOk() {
        return code==200;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
