package com.ego.portal.controller;

import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.ShopInformation;
import com.ego.portal.service.ShopInformationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("shopInformation")
public class ShopInformationController {

    @Resource
    private ShopInformationService shopInformationService;

    @RequestMapping("list")
    @ResponseBody
    public BaseResult queryShopInfoList(ShopInformation shopInformation, Integer pageNum, Integer pageSize){
        return shopInformationService.queryShopInfoList(shopInformation, pageNum, pageSize);
    }
}
