package com.syl.snow.bean;

import java.util.List;

/**
 * Created by Bright on 2019/3/31.
 *
 * @Describe RecyclerView嵌套,RecyclerView的条目还是RecyclerView
 * @Called
 */
public class DoubleRvE {
    private Integer tieleId;
    private String titleName;
    private String titleDesc;
    private List<TitleBean> titleList;

    public DoubleRvE() {
    }

    @Override
    public String toString() {
        return "DoubleRvE{" +
                "tieleId=" + tieleId +
                ", titleName='" + titleName + '\'' +
                ", titleDesc='" + titleDesc + '\'' +
                ", titleList=" + titleList +
                '}';
    }

    public Integer getTieleId() {
        return tieleId;
    }

    public void setTieleId(Integer tieleId) {
        this.tieleId = tieleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public List<TitleBean> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<TitleBean> titleList) {
        this.titleList = titleList;
    }
}
