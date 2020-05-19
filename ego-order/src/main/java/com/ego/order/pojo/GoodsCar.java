package com.ego.order.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhiyu 
 * @create 2020-05-13
 * @since 1.0.0
 */
public class GoodsCar implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 修改时间
     */
    private Date modified;

    /**
     * 商品id
     */
    private Integer sid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品是否被删除
     */
    private Integer display;

    /**
     * 订单编号
     */
    private String sno;

    /**
     * goodscar
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

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modified=").append(modified);
        sb.append(", sid=").append(sid);
        sb.append(", uid=").append(uid);
        sb.append(", quantity=").append(quantity);
        sb.append(", display=").append(display);
        sb.append(", sno=").append(sno);
        sb.append("]");
        return sb.toString();
    }
}