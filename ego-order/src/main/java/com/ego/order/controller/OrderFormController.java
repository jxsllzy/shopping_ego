package com.ego.order.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.order.service.OrderFormService;
import com.ego.order.service.OrderService;
import com.ego.rpc.service.GoodsCartService;
import com.ego.rpc.service.GoodsService;
import com.ego.rpc.vo.CartResult;
import com.ego.rpc.vo.OrderFromVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("form")
public class OrderFormController {

    @Autowired
    private OrderService orderService;

    @Reference(interfaceClass = GoodsCartService.class)
    private GoodsCartService goodsCartService;

    @Reference(interfaceClass = GoodsService.class)
    private GoodsService goodsService;

    @Autowired
    private OrderFormService orderFormService;

    //生成送货地址 返回快递编号
    @RequestMapping("saveOrderForm")
    public String saveOrderForm(Model model, Integer addressNum, HttpServletRequest request) {
        //自定义用户数据
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("user");
        //查询购物车
        CartResult cartResult = goodsCartService.getCartList(userInformation);
        //选取收货地址
        OrderFromVo orderFrom = orderFormService.findOrderFormById(userInformation, addressNum);
        //存入票据
        BaseResult ba = goodsService.updateGoodsInfo(cartResult);
        //存入货单表
        BaseResult baseResult = orderService.saveGoodsOfOrderForm(orderFrom, cartResult,userInformation);
        //清除购物车
        goodsCartService.clearCar(userInformation);
        System.out.println(baseResult);
        List<String> formList = orderService.findFormList(userInformation);
        model.addAttribute("formList",formList);
        return "form/list";
    }
}