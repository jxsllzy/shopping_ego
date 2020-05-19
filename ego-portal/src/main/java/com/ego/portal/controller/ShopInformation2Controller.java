package com.ego.portal.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.ShopInformation;
import com.ego.portal.service.ShopInformationService;
import com.ego.rpc.service.KindsService;
import com.ego.rpc.vo.KindsVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@Controller
@RequestMapping("shopInformation")
public class ShopInformation2Controller {

    @Resource
    private ShopInformationService shopInformationService;

    @Reference(interfaceClass = KindsService.class)
    private KindsService kindsService;

    @RequestMapping("list")
    @ResponseBody
    public BaseResult queryShopInfoList(ShopInformation shopInformation, Integer pageNum, Integer pageSize){
        return shopInformationService.queryShopInfoList(shopInformation, pageNum, pageSize);
    }

    @RequestMapping("kinds")
    @ResponseBody
    public List<KindsVo> queryAllKindsList(){
        return kindsService.queryAllKindsList();
    }



}
