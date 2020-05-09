package com.ego.portal.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangjindong 
 * @create 2020-05-09
 * @since 1.0.0
 */
public class UserWant implements Serializable {
    /**
     * 求购商品id
     */
    private Integer id;

    /**
     * 求购商品最后一次修改时间，可为空
     */
    private Date modified;

    /**
     * 求购商品信息是否被删除
     */
    private Integer display;

    /**
     * 求购商品name
     */
    private String name;

    /**
     * 求购商品类别id
     */
    private Integer sort;

    /**
     * 求购商品数量
     */
    private Integer quantity;

    /**
     * 求购商品价格
     */
    private BigDecimal price;

    /**
     * 求购商品详情，可为空
     */
    private String remark;

    /**
     * 求购商品的用户id
     */
    private Integer uid;

    /**
     * userwant
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

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modified=").append(modified);
        sb.append(", display=").append(display);
        sb.append(", name=").append(name);
        sb.append(", sort=").append(sort);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", remark=").append(remark);
        sb.append(", uid=").append(uid);
        sb.append("]");
        return sb.toString();
    }
}