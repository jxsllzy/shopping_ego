package com.ego.order.service;

import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.rpc.vo.CartResult;
import com.ego.rpc.vo.OrderFromVo;

import java.util.List;

public interface OrderService {

    BaseResult saveGoodsCar(CartResult cartResult, UserInformation admin);

    BaseResult saveGoodsOfOrderForm(OrderFromVo orderFrom, CartResult cartResult,UserInformation userInformation);

    List<String> findFormList(UserInformation userInformation);
}
