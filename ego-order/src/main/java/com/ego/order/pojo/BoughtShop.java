package com.ego.order.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhiyu 
 * @create 2020-05-13
 * @since 1.0.0
 */
public class BoughtShop implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Date modified;

    /**
     * 
     */
    private Integer state;

    /**
     * 
     */
    private Integer uid;

    /**
     * 
     */
    private Integer sid;

    /**
     * 
     */
    private Integer quantity;

    /**
     * boughtshop
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modified=").append(modified);
        sb.append(", state=").append(state);
        sb.append(", uid=").append(uid);
        sb.append(", sid=").append(sid);
        sb.append(", quantity=").append(quantity);
        sb.append("]");
        return sb.toString();
    }
}