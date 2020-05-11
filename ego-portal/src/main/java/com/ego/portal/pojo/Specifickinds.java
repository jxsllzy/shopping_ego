package com.ego.portal.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoubin 
 * @create 2020-05-11
 * @since 1.0.0
 */
public class Specifickinds implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Date modified;

    /**
     * 
     */
    private Integer cid;

    /**
     * specifickinds
     */
    private static final long serialVersionUID = 1L;

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
        this.name = name == null ? null : name.trim();
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", modified=").append(modified);
        sb.append(", cid=").append(cid);
        sb.append("]");
        return sb.toString();
    }
}