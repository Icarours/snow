package com.syl.snow.bean;

import java.io.Serializable;

/**
 * Created by Bright on 2018/12/27.
 *
 * @Describe 网络请求参数
 * @Called
 */
public class Params implements Serializable {
    /**
     * {"pageNumber":"1","pageSize":"16"}
     */
    private String pageNumber;
    private String pageSize;
    private String param3;
    private String param4;

    public Params() {
    }

    public Params(String pageNumber, String pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Params(String pageNumber, String pageSize, String param3, String param4) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.param3 = param3;
        this.param4 = param4;
    }

    @Override
    public String toString() {
        return "Params{" +
                "pageNumber='" + pageNumber + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", param3='" + param3 + '\'' +
                ", param4='" + param4 + '\'' +
                '}';
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }
}
