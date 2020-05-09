package com.ego.portal.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cl 
 * @create 2020-05-08
 * @since 1.0.0
 */
public class ShopInformation implements Serializable {
    /**
     * 商品id
     */
    private Integer id;

    /**
     * 修改时间
     */
    private Date modified;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品成色
     */
    private Integer level;

    /**
     * 商品详细信息
     */
    private String remark;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品类别
     */
    private Integer sort;

    /**
     * 商品是否被下架
     */
    private Integer display;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 交易方式
     */
    private Integer transaction;

    /**
     * 商品销量
     */
    private Integer sales;

    /**
     * 发表的用户id
     */
    private Integer uid;

    /**
     * 商品的图片地址
     */
    private String image;

    /**
     * 
     */
    private String thumbnails;

    /**
     * shopinformation
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTransaction() {
        return transaction;
    }

    public void setTransaction(Integer transaction) {
        this.transaction = transaction;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails == null ? null : thumbnails.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modified=").append(modified);
        sb.append(", name=").append(name);
        sb.append(", level=").append(level);
        sb.append(", remark=").append(remark);
        sb.append(", price=").append(price);
        sb.append(", sort=").append(sort);
        sb.append(", display=").append(display);
        sb.append(", quantity=").append(quantity);
        sb.append(", transaction=").append(transaction);
        sb.append(", sales=").append(sales);
        sb.append(", uid=").append(uid);
        sb.append(", image=").append(image);
        sb.append(", thumbnails=").append(thumbnails);
        sb.append("]");
        return sb.toString();
    }
}