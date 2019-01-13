package com.syl.snow.bean;

import java.io.Serializable;

/**
 * Created by Bright on 2019/1/13.
 *
 * @Describe
 * @Called
 */
public class AccountBean implements Serializable {
    private String name ;
    private String phone;
    private String blog;

    public AccountBean() {
    }

    public AccountBean(String name, String phone, String blog) {
        this.name = name;
        this.phone = phone;
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}
