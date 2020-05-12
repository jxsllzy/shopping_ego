package com.ego.rpc.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsVo implements Serializable {

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

    public GoodsVo() {
    }

    public GoodsVo(Integer id, String name, String remark, BigDecimal price, String image) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.price = price;
        this.image = image;
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

    @Override
    public String toString() {
        return "GoodsVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
