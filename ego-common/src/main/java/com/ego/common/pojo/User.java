package com.ego.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 	修改时间
     */
    private Date modified;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户手机号码
     */
    private String phone;

    /**
     * 用户真实姓名
     */
    private String realname;

    /**
     * 用户所在班级
     */
    private String clazz;

    /**
     * 用户学号
     */
    private String sno;

    /**
     * 宿舍号
     */
    private String dormitory;

    /**
     * 性别
     */
    private String gender;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 头像
     */
    private String avatar;

    /**
     *用户密码
     */
    private String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", modified=" + modified +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", realname='" + realname + '\'' +
                ", clazz='" + clazz + '\'' +
                ", sno='" + sno + '\'' +
                ", dormitory='" + dormitory + '\'' +
                ", gender='" + gender + '\'' +
                ", createtime=" + createtime +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
