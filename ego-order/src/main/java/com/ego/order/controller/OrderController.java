package com.ego.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.order.config.AlipayConfig;
import com.ego.order.service.OrderFormService;
import com.ego.order.service.OrderService;
import com.ego.rpc.service.GoodsCartService;
import com.ego.rpc.service.GoodsService;
import com.ego.rpc.vo.CartResult;
import com.ego.rpc.vo.OrderFromVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("order")
public class OrderController {

    @Reference(interfaceClass = GoodsCartService.class)
    private GoodsCartService goodsCartService;

    @Autowired
    private OrderFormService orderFormService;

    @Autowired
    private OrderService orderService;

    @Reference(interfaceClass = GoodsService.class)
    private GoodsService goodsService;

    //查看订单详情
    @RequestMapping("preOrder")
    public String toPreOrder(Model model,@RequestParam(required=false) Integer addressNum,HttpServletRequest request){
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("user");
        //查询购物车信息
        CartResult cartList = goodsCartService.getCartList(userInformation);
        //查询货品存货数量
        CartResult newCatList = goodsService.findGoodsNumById(cartList);
        //获取送货地址数据
        if(!CollectionUtils.isEmpty(newCatList.getCartList())){
            //查询收货地址
            OrderFromVo orderFrom = orderFormService.findOrderFormById(userInformation, addressNum);
            model.addAttribute("addressNum",addressNum);
            model.addAttribute("orderForm", orderFrom );
            model.addAttribute("error",newCatList.toString().indexOf("库存不足,请联系厂家")==-1?false:true);
            model.addAttribute("cartResult", null == newCatList ? new CartResult() : newCatList);
            return "order/preOrder";
        }
        return "redirect:http://localhost:9094/ego-search/";
    }

    //提交订单  返回订单编号
    @RequestMapping("submitOrder")
    public String submitOrder(Model model, HttpServletRequest request, Integer addressNum, RedirectAttributes attr){
        //获取用户信息
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("user");
        //获取购物车信息
        CartResult cartResult = goodsCartService.getCartList(userInformation);
        //储存订单信息，并且返回订单编号
        BaseResult baseResult =  orderService.saveGoodsCar(cartResult,userInformation);
        //判断购物车是否添加成功
        if (baseResult.getCode()==200){
            //信息加入订单表
            model.addAttribute("totalPrice",cartResult.getTotalPrice());
            model.addAttribute("goodCarSno",baseResult.getMessage());
            model.addAttribute("addressNum",addressNum);
            return "order/submitOrder";
        }else {
            //添加失败，跳转回订单，并且提示用户错误
            attr.addAttribute("error","参数异常");
            attr.addAttribute("addressNum",addressNum);
            return "redirect:preOrder";
        }
    }

    //跳转支付宝
    @RequestMapping("payment")
    public String payment(HttpServletRequest request, Model model,String orderSn) {
        //获取用户信息
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("user");
        try {
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                    AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
                    AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

            CartResult cartResult = goodsCartService.getCartList(userInformation);

            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = orderSn;
            //付款金额，必填
            String total_amount = String.valueOf(cartResult.getTotalPrice());
            //订单名称，必填
            String subject = "请向"+out_trade_no+"订单支付";
            //商品描述，可空
            String body = " ";

            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
            //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
            //		+ "\"total_amount\":\""+ total_amount +"\","
            //		+ "\"subject\":\""+ subject +"\","
            //		+ "\"body\":\""+ body +"\","
            //		+ "\"timeout_express\":\"10m\","
            //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

            //请求
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            System.out.println(result);
            model.addAttribute("result", result);
            return "order/payment";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
