package com.ego.search.service;

import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.rpc.vo.OrderFromVo;

import java.util.List;

public interface OrderFormService {
    /**
     * 查找该用户所用的地址
     * @param userInformation
     * @return
     */
    List<OrderFromVo> findOrderForm(UserInformation userInformation);

    BaseResult saveOrderForm(UserInformation userInformation);
}
