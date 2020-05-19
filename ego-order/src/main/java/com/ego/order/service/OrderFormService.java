package com.ego.order.service;


import com.ego.common.pojo.UserInformation;
import com.ego.rpc.vo.OrderFromVo;

public interface OrderFormService {

    //查询收货地址
    public OrderFromVo findOrderFormById(UserInformation admin, Integer addressNum);
}
