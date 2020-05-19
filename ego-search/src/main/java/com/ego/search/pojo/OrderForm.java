package com.ego.search.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoubin 
 * @create 2020-05-11
 * @since 1.0.0
 */
public class OrderForm implements Serializable {
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
    private Integer display;

    /**
     * 
     */
    private Integer uid;

    /**
     * 
     */
    private String address;

    /**
     * 
     */
    private String context;

    /**
     * orderform
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
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
        sb.append(", uid=").append(uid);
        sb.append(", address=").append(address);
        sb.append(", context=").append(context);
        sb.append("]");
        return sb.toString();
    }
}