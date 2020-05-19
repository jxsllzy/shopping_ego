package com.ego.portal.service.impl;

import com.ego.common.result.BaseResult;
import com.ego.portal.mapper.ShopInformationMapper;
import com.ego.portal.pojo.ShopInformation;
import com.ego.portal.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 保存商品
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopInformationMapper shopInformationMapper;
    /**
     * 保存商品
     * @param shopInformation
     * @return
     */
    @Override
    public BaseResult save(ShopInformation shopInformation) {
        shopInformation.setUid(1);
        shopInformation.setModified(new Date());
        int result = shopInformationMapper.insertSelective(shopInformation);
        return result>0?BaseResult.success():BaseResult.error();
    }
}
