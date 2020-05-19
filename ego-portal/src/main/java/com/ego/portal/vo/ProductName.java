package com.ego.portal.vo;

import java.io.Serializable;

public class ProductName implements Serializable {
    //商品分类字段
    private String Aname;
    private String Cname;
    private String Kname;

    public ProductName() {
    }

    public ProductName(String aname, String cname, String kname) {
        Aname = aname;
        Cname = cname;
        Kname = kname;
    }

    public String getAname() {
        return Aname;
    }

    public void setAname(String aname) {
        Aname = aname;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getKname() {
        return Kname;
    }

    public void setKname(String kname) {
        Kname = kname;
    }

    @Override
    public String toString() {
        return "ProductName{" +
                "Aname='" + Aname + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Kname='" + Kname + '\'' +
                '}';
    }
}
