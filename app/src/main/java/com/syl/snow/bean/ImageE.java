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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageE)) return false;

        ImageE imageE = (ImageE) o;

        if (getId() != imageE.getId()) return false;
        return getPath() != null ? getPath().equals(imageE.getPath()) : imageE.getPath() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getPath() != null ? getPath().hashCode() : 0);
        return result;
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
