package com.ego.sso.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoubin 
 * @create 2020-05-14
 * @since 1.0.0
 */
public class UserPassword implements Serializable {
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
    private String password;

    /**
     * 
     */
    private Integer uid;

    /**
     * userpassword
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
        sb.append(", password=").append(password);
        sb.append(", uid=").append(uid);
        sb.append("]");
        return sb.toString();
    }
}