package com.ego.portal.service;

import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.ShopInformation;

/**
 * 商品信息保存
 */
public interface ShopService {
    BaseResult save(ShopInformation shopInformation);
}
