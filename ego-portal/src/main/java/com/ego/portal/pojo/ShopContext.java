package com.ego.portal.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoubin 
 * @create 2020-05-11
 * @since 1.0.0
 */
public class ShopContext implements Serializable {
    /**
     * 商品留言id
     */
    private Integer id;

    /**
     * 修改时间
     */
    private Date modified;

    /**
     * 商品的id
     */
    private Integer sid;

    /**
     * 留言内容
     */
    private String context;

    /**
     * 是否可见
     */
    private Integer display;

    /**
     * 评论的用户id
     */
    private Integer uid;

    /**
     * shopcontext
     */
    private String uname;

    @Override
    public String toString() {
        return "ShopContext{" +
                "id=" + id +
                ", modified=" + modified +
                ", sid=" + sid +
                ", context='" + context + '\'' +
                ", display=" + display +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                '}';
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
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


}