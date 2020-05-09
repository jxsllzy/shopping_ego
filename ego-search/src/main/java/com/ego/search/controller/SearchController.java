package com.ego.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.result.EgoPageInfo;
import com.ego.rpc.service.GoodsService;
import com.ego.rpc.vo.GoodsVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {

    @Reference(interfaceClass = GoodsService.class)
    private GoodsService goodsService;

    @RequestMapping("findShopByName")
    @ResponseBody
    public EgoPageInfo findShopByName(String name){
        return goodsService.doSearch("孤独", 1, 5);
    }
}
