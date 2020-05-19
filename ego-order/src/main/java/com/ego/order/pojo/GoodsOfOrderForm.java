package com.ego.order.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhiyu 
 * @create 2020-05-13
 * @since 1.0.0
 */
public class GoodsOfOrderForm implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer ofid;

    /**
     * 
     */
    private Integer sid;

    /**
     * 修改的时间
     */
    private Date modified;

    /**
     * 
     */
    private Integer quantity;

    /**
     * 
     */
    private Integer display;

    /**
     * 快递编号
     */
    private String sno;

    /**
     * goodsoforderform
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOfid() {
        return ofid;
    }

    public void setOfid(Integer ofid) {
        this.ofid = ofid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
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
        sb.append(", ofid=").append(ofid);
        sb.append(", sid=").append(sid);
        sb.append(", modified=").append(modified);
        sb.append(", quantity=").append(quantity);
        sb.append(", display=").append(display);
        sb.append(", sno=").append(sno);
        sb.append("]");
        return sb.toString();
    }
}