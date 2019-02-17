package com.syl.snow.bean;

import java.io.Serializable;

/**
 * Created by Bright on 2019/2/17.
 *
 * @Describe 图片bean
 * @Called
 */
public class ImageE implements Serializable {
    private int id;
    private String path;

    public ImageE(int id, String path) {
        this.id = id;
        this.path = path;
    }

    @Override
    public String toString() {
        return "ImageE{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
