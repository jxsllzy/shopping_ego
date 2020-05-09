package com.ego.portal.service;

import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.ShopInformation;

public interface ShopInformationService {
    BaseResult queryShopInfoList(ShopInformation shopInformation,Integer pageNum, Integer pageSize);
}
