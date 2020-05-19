package com.ego.portal.controller;

import com.ego.common.result.BaseResult;
import com.ego.portal.service.ProductInfoService;
import com.ego.portal.service.ShopInformationService;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShopInformationController {

    @Autowired
    private ShopInformationService shopInformationService;
    @Autowired
    private ProductInfoService productInfoService;



    //跳转首页页面
    @RequestMapping({"/","index"})
    public String shop(Model model, HttpServletRequest request){
        //首页-轮播图的商品展示-根据商品数量升序前5条数据
        model.addAttribute("shop",shopInformationService.shop());
        //首页-精品的商品展示-根据商品数量升序第5到10条数据
        //model.addAttribute("shops",shopInformationService.shops());
        return "index";
    }

    //精品商品分页查询
    @RequestMapping("listForPage")
    @ResponseBody
    public BaseResult selectGoodsListByPage(Integer pageNum,Integer pageSize){
        return shopInformationService.selectGoodsListByPage(pageNum,pageSize);
    }


    //商品详情页面跳转
    @RequestMapping("productInfo")
    public String Info(Model model,Integer id,Integer sort){
        //商品详情查询-根据商品id查询对应得信息
        model.addAttribute("productInfo",productInfoService.productInfo(id));
        //根据商品分类id查询分类信息
        model.addAttribute("productName",productInfoService.productName(id,sort));
        //根据商品id查询商品留言和用户名
        model.addAttribute("shopContext",productInfoService.shopContext(id));
        System.out.println(id+","+sort);
        return "product_info";
    }

    //测试询分类信息
   /* @RequestMapping("Info")
    @ResponseBody
    public ProductName Info(Integer id, Integer sort){
        return productInfoService.productName(id,sort);
    }*/
}
