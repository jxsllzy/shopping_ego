package com.ego.rpc.vo;

import java.io.Serializable;

public class OrderFromVo implements Serializable {

    private Integer id;

    private String username;

    private String phone;

    private String address;

    private String context;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "OrderFromVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", context='" + context + '\'' +
                '}';
    }

    public OrderFromVo() {
    }

    public OrderFromVo(Integer id, String username, String phone, String address, String context) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.context = context;
    }
}
