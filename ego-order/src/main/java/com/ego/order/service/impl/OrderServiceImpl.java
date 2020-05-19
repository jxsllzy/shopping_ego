package com.ego.order.service.impl;

import com.ego.common.enums.PayStatus;
import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.common.util.JsonUtil;
import com.ego.order.mapper.GoodsOfOrderFormMapper;
import com.ego.order.pojo.GoodsOfOrderForm;
import com.ego.order.service.OrderService;
import com.ego.order.mapper.BoughtShopMapper;
import com.ego.order.mapper.GoodsCarMapper;
import com.ego.order.pojo.BoughtShop;
import com.ego.order.pojo.GoodsCar;
import com.ego.rpc.vo.CartResult;
import com.ego.rpc.vo.OrderFromVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GoodsOfOrderFormMapper goodsOfOrderFromMapper;

    @Autowired
    private GoodsCarMapper goodsCarMapper;

    @Autowired
    private BoughtShopMapper boughtShopMapper;


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${user.formSno}")
    private String userFormSno;

    @Value("${redis.order.increment}")
    private String orderIncrement;

    /**
     * 存入订单信息
     * @param cartResult
     * @param admin
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public BaseResult saveGoodsCar(CartResult cartResult, UserInformation admin) {
        //非空判断
        if((null==admin||null==admin.getId())){
            return BaseResult.error();
        }
        if(CollectionUtils.isEmpty(cartResult.getCartList())){
            return BaseResult.error();
        }
        //生成sno
        String orderSn = "esorder" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + getIncrement(orderIncrement);
        //将数据存入"已购买的商品"表
        List<BoughtShop> boughtShopList = cartResult.getCartList().stream().map(cartVo -> {
            BoughtShop boughtShop = new BoughtShop();
            boughtShop.setModified(new Date());
            boughtShop.setState(Integer.valueOf(PayStatus.no_pay.getStatus()));
            boughtShop.setQuantity(cartVo.getGoodsNum());
            boughtShop.setSid(cartVo.getId());
            boughtShop.setUid(admin.getId());
            return boughtShop;
        }).collect(Collectors.toList());

        int len = boughtShopMapper.insertBoughtShopList(boughtShopList);
        if(len!=boughtShopList.size()){
            return BaseResult.error();
        }
        //将数据存入"购物车"表
        List<GoodsCar> goodsCarList = cartResult.getCartList().stream().map(cartVo -> {
            GoodsCar goodsCar = new GoodsCar();
            goodsCar.setModified(new Date());
            goodsCar.setSid(cartVo.getId());
            goodsCar.setUid(admin.getId());
            goodsCar.setQuantity(cartVo.getGoodsNum());
            goodsCar.setDisplay(1);
            goodsCar.setSno(orderSn);

            return goodsCar;
        }).collect(Collectors.toList());

        len = goodsCarMapper.insertGoodsCarList(goodsCarList);

        if(len==goodsCarList.size()){
            BaseResult baseResult = new BaseResult();
            baseResult.setMessage(orderSn);
            baseResult.setCode(200);
            return baseResult;
        }
        return BaseResult.error();
    }

    /**
     * 存储送货地址和商品关系表
     * @param orderFrom
     * @param cartResult
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public BaseResult saveGoodsOfOrderForm(OrderFromVo orderFrom, CartResult cartResult,UserInformation userInformation) {

        if(userInformation==null|| userInformation.getId()==null){
            return BaseResult.error();
        }
        if (orderFrom==null){
            return BaseResult.error();
        }
        if (CollectionUtils.isEmpty(cartResult.getCartList())){
            return BaseResult.error();
        }
        List<String> snoList = new ArrayList<>();
        //开启redis存储
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        List<GoodsOfOrderForm> collect = cartResult.getCartList().stream().map(cart -> {
            String orderSn = "esform" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + getIncrement(orderIncrement);
            GoodsOfOrderForm ofOrderFrom = new GoodsOfOrderForm();
            //送货地址
            ofOrderFrom.setOfid(orderFrom.getId());
            //购物车id
            ofOrderFrom.setSid(cart.getId());
            //更新时间
            ofOrderFrom.setModified(new Date());
            //数量
            ofOrderFrom.setQuantity(cart.getGoodsNum());
            //显示
            ofOrderFrom.setDisplay(1);
            snoList.add(orderSn);
            ofOrderFrom.setSno(orderSn);

            return ofOrderFrom;
        }).collect(Collectors.toList());

        valueOperations.set(userFormSno+":"+userInformation.getId(), JsonUtil.object2JsonStr(snoList));
        //存入数据库
        Integer len = goodsOfOrderFromMapper.insertGoodsOfOrderFromList(collect);
        System.out.println(len);
        //判断是否添加成功
        if (len == collect.size()){
            BaseResult baseResult = new BaseResult();
            baseResult.setCode(200);
            return baseResult;
        }




        return BaseResult.error();
    }

    /**
     * redis自增key
     */
    private Long getIncrement(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return entityIdCounter.getAndIncrement();
    }

    /**
     * 获取所有订单
     */
    public List<String> findFormList(UserInformation userInformation){
        //开启redis存储
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get(userFormSno + ":" + userInformation.getId());
        List<String> list = JsonUtil.jsonToList(String.valueOf(o), String.class);
        System.out.println(list);
        return list;
    }

}
