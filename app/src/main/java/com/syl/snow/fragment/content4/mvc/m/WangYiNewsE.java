package com.syl.snow.fragment.content4.mvc.m;

import java.io.Serializable;

/**
 * Created by Bright on 2019/10/5.
 *
 * @Describe
 * @Called 网易新闻
 * https://api.apiopen.top/getWangYiNews?page=1&count=12
 */
public class WangYiNewsE implements Serializable {

    /**
     * path : https://news.163.com/19/1005/09/EQNDFVKM0001899N.html
     * image : http://cms-bucket.ws.126.net/2019/10/05/3bdd8d2b11b44711a9d9ba6b62638cb4.png?imageView&thumbnail=140y88&quality=85
     * title : 弹劾调查升级！美国众议院民主党向白宫发出传票
     * passtime : 2019-10-05 10:00:34
     */

    private String path;
    private String image;
    private String title;
    private String passtime;

    public WangYiNewsE() {
    }

    @Override
    public String toString() {
        return "WangYiNewsE{" +
                "path='" + path + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", passtime='" + passtime + '\'' +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPasstime() {
        return passtime;
    }

    public void setPasstime(String passtime) {
        this.passtime = passtime;
    }
}
