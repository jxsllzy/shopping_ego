package com.ego.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoubin 
 * @create 2020-05-14
 * @since 1.0.0
 */
public class UserInformation implements Serializable {
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
     * userinformation
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz == null ? null : clazz.trim();
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory == null ? null : dormitory.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
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
        this.avatar = avatar == null ? null : avatar.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modified=").append(modified);
        sb.append(", username=").append(username);
        sb.append(", phone=").append(phone);
        sb.append(", realname=").append(realname);
        sb.append(", clazz=").append(clazz);
        sb.append(", sno=").append(sno);
        sb.append(", dormitory=").append(dormitory);
        sb.append(", gender=").append(gender);
        sb.append(", createtime=").append(createtime);
        sb.append(", avatar=").append(avatar);
        sb.append("]");
        return sb.toString();
    }
}