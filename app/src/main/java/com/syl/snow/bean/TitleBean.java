package com.syl.snow.bean;

import java.io.Serializable;

/**
 * Created by Bright on 2018/12/8.
 *
 * @Describe
 * @Called
 */
public class TitleBean implements Serializable {
    private Integer id;
    private String title;
    private String description;

    public TitleBean() {
    }

    public TitleBean(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "TitleBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
