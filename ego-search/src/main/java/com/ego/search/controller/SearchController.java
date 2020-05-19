package com.ego.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.result.BaseResult;
import com.ego.common.result.EgoPageInfo;
import com.ego.rpc.service.GoodsService;
import com.ego.rpc.vo.GoodsVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchController {

    @Reference(interfaceClass = GoodsService.class)
    private GoodsService goodsService;

    /**
     * 跳转搜索页面
     * @param model
     * @param name
     * @return
     */
    @RequestMapping("findShopByName")
    public String findShopByName(Model model,String name){
        model.addAttribute("searchStr",name);
        return "page/mall_page";
    }

    /**
     * 根据商品名获取商品
     * @param model
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search/searchShopByName")
    @ResponseBody
    public EgoPageInfo findShopByName(Model model ,@RequestParam("searchStr") String name,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "6") Integer pageSize){
        EgoPageInfo<GoodsVo> egoPageInfo = goodsService.doSearch(name, pageNum, pageSize);
        return egoPageInfo;
    }


}
