package com.ego.rpc.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CartVo implements Serializable {

    private static final long serialVersionUID = -1905915184535584387L;

    //商品id
    private Integer id;

    //商品名称
    private String name;

    //商品信息
    private String remark;

    //商品价格
    private BigDecimal price;

    //商品地址
    private String image;

    //商品数量
    private Integer goodsNum;

    //添加时间
    private Date datetime;

    private String context;

    public CartVo() {
    }

    public CartVo(Integer id, String name, String remark, BigDecimal price, String image, Integer goodsNum, Date datetime) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.price = price;
        this.image = image;
        this.goodsNum = goodsNum;
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", goodsNum=" + goodsNum +
                ", datetime=" + datetime +
                ", context='" + context + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
