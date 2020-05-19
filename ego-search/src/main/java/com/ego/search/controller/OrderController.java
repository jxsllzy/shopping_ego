package com.ego.search.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.rpc.service.GoodsCartService;
import com.ego.rpc.service.GoodsService;
import com.ego.rpc.vo.CartVo;
import com.ego.search.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("order")
public class OrderController {

    @Reference(interfaceClass = GoodsCartService.class)
    private GoodsCartService goodsCartService;

    @Reference(interfaceClass = GoodsService.class)
    private GoodsService goodsService;

    @Autowired
    private OrderFormService orderFromService;

    /**
     * 添加购物车
     * @return
     */
    @RequestMapping("insertGoodsCar")
    @ResponseBody
    public BaseResult insertGoodsCar(Integer id, HttpServletRequest request,@RequestParam(defaultValue = "1") Integer goodsNum){
        //根据id获取商品信息
        CartVo cartVo = goodsService.findGoodsByCartId(id);
        if (cartVo==null){
            return BaseResult.error();
        }
        cartVo.setGoodsNum(goodsNum);
        /**
         * 如果登录
         *  存入redis
         * 没有登录
         *  存入cooke
         */
        //true代表未登录
        BaseResult baseResult = null;
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("user");
        if(userInformation!=null&&userInformation.getId()!=null){
            baseResult = goodsCartService.addCartToRedis(cartVo,userInformation);
        }else{
            baseResult = goodsCartService.addCartToCookie(cartVo,userInformation,request);
        }
        return baseResult;
    }

    /**
     * 查看购物车
     */
    @RequestMapping({"/","index"})
    public String index(Model model,HttpServletRequest request){
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("user");
        model.addAttribute("cartResult", goodsCartService.getCartList(userInformation));
        model.addAttribute("addressList",orderFromService.findOrderForm(userInformation));
        return "page/shopping_cart";
    }

    /**
     * 删除购物车
     */
    @RequestMapping("deleteGoodsCar")
    @ResponseBody
    public BaseResult deleteGoodsCar(Integer id,HttpServletRequest request){
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("user");
        return goodsCartService.deleteCartById(id,userInformation);
    }

    /**
     * 跳转订单页面
     */
    @RequestMapping("toPreOrder")
    public String toPreOrder(
            HttpServletRequest request,
            @RequestParam(defaultValue = "-1") Integer toPreOrderNum){
        return "redirect:"+request.getSession().getServletContext().getAttribute("orderUrl")+"/order/preOrder?addressNum="+toPreOrderNum;
    }

    /**
     *  更新购物车
     */
    @RequestMapping("updateNum")
    @ResponseBody
    public BaseResult updateSum(Integer uid,Integer num,HttpServletRequest request){
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("user");
        BaseResult baseResult = goodsCartService.updateCartSum(uid,num,userInformation);
        return baseResult;
    }

    //添加购物车
    @RequestMapping("addAddress")
    @ResponseBody
    public BaseResult addAddress(HttpServletRequest request){
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("user");
        BaseResult baseResult = orderFromService.saveOrderForm(userInformation);
        return baseResult;
    }

}
