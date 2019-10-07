package com.syl.snow.fragment.content4.mvp.m;

import java.io.Serializable;

/**
 * Created by Bright on 2019/10/7.
 *
 * @Describe
 * @Called
 * https://api.apiopen.top/getImages?page=1&count=12
 */
public class ImageE implements Serializable {

    /**
     * id : 673
     * time : 2019-05-05 04:00:00
     * img : http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg
     */

    private String id;
    private String time;
    private String img;

    public ImageE() {
    }

    @Override
    public String toString() {
        return "ImageE{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
