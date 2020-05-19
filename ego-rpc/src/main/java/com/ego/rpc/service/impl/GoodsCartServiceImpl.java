package com.ego.rpc.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.common.util.JsonUtil;
import com.ego.rpc.service.GoodsCartService;
import com.ego.rpc.vo.CartResult;
import com.ego.rpc.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service(interfaceClass = GoodsCartService.class)
@Component
public class GoodsCartServiceImpl implements GoodsCartService{

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${user.cart}")
    private String userCart;

    private static HashOperations<String, String, String> hashOperations = null;

    /**
     * 添加购物车
     * @param cart
     * @param admin
     * @return
     */
    @Override
    public BaseResult addCartToRedis(CartVo cart, UserInformation admin) {
        //如果用户信息不存在，直接返回
        if (null==admin||null==admin.getId()){
            return BaseResult.error();
        }
        //获取用户编号
        Integer userId = admin.getId();
        //查询当前用户的购物车信息
        hashOperations = redisTemplate.opsForHash();
        Map<String,String> cartMap = hashOperations.entries(userCart+":"+userId);
        //如果购物车信息不为空，修改购物车信息
        if (!CollectionUtils.isEmpty(cartMap)){
            //根据商品id获取购物车信息
            String cartStr = cartMap.get(String.valueOf(cart.getId()));
            //如果商品存在
            if(!StringUtils.isEmpty(cartStr)){
                CartVo cartVo = JsonUtil.jsonStr2Object(cartStr,CartVo.class);
                //修改商品数量
                cartVo.setGoodsNum(cartVo.getGoodsNum()+cart.getGoodsNum());
                //修改商品最新价格
                cartVo.setPrice(cart.getPrice());
                //重新添加至map,副高之前的商品对象
                cartMap.put(String.valueOf(cart.getId()),JsonUtil.object2JsonStr(cartVo));
            }else {
                //如果商品不存在，新增购物信息
                cartMap.put(String.valueOf(cart.getId()),JsonUtil.object2JsonStr(cart));
            }
        }else {
            //如果购物车信息为空
            cartMap = new HashMap<>();
            cartMap.put(String.valueOf(cart.getId()),JsonUtil.object2JsonStr(cart));
        }
        hashOperations.putAll(userCart+":"+userId,cartMap);
        return BaseResult.success();
    }

    @Override
    public BaseResult addCartToCookie(CartVo goodsVo, UserInformation userInformation, HttpServletRequest request) {

        return null;
    }

    /**
     * 获取购物车信息
     * @param admin
     * @return
     */
    @Override
    public CartResult getCartList(UserInformation admin) {
        //初始化返回对象
        CartResult cartResult = new CartResult();

        //判断用户是否为空
        if ((null==admin||null==admin.getId())){
            return cartResult;
        }

        List<CartVo> cartList = null;
        //准备金额
        BigDecimal totalPrice = new BigDecimal("0");
        hashOperations = redisTemplate.opsForHash();
        Map<String,String> cartMap = hashOperations.entries(userCart + ":" + admin.getId());
        //从redis里获取购物车信息
        if(!CollectionUtils.isEmpty(cartMap)){
            //如果存在
            cartList = new ArrayList<>();
            for (Map.Entry<String,String> entry:cartMap.entrySet()){
                CartVo cartVo = JsonUtil.jsonStr2Object(entry.getValue(),CartVo.class);
                //将购物车对象放入list
                cartList.add(cartVo);
                //计算总价
                BigDecimal singePrice = cartVo.getPrice().multiply(new BigDecimal(String.valueOf(cartVo.getGoodsNum())));
                //相加
                totalPrice = totalPrice.add(singePrice);
            }
            //将购物车list放入返回结果对象
            cartResult.setCartList(cartList);
            //将格式化2位小数，四舍五入
            totalPrice.setScale(2,BigDecimal.ROUND_CEILING);
            cartResult.setTotalPrice(totalPrice);
        }
        return cartResult;
    }

    /**
     * 删除订单
     * @param id
     * @param admin
     * @return
     */
    @Override
    public BaseResult deleteCartById(Integer id,UserInformation admin) {
        //查看用户是否登录
        if ((null==admin||null==admin.getId())){
            return BaseResult.error();
        }
        if (StringUtils.isEmpty(id)){
            return BaseResult.error();
        }
        hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(userCart + ":" + admin.getId(),String.valueOf(id));
        return BaseResult.success();
    }

    @Override
    public BaseResult clearCar(UserInformation admin) {
        //1.判断用户是否存在
        if (null == admin || null == admin.getId()) {
            return BaseResult.error();
        }
        hashOperations = redisTemplate.opsForHash();
        Map<String, String> cartMap = hashOperations.entries(userCart + ":" + admin.getId());
        if (CollectionUtils.isEmpty(cartMap)){
            return BaseResult.error();
        }
        redisTemplate.delete(userCart + ":" + admin.getId());

        return BaseResult.success();
    }

    @Override
    public BaseResult updateCartSum(Integer uid, Integer num, UserInformation admin) {
        hashOperations = redisTemplate.opsForHash();
        Map<String, String> cartMap = hashOperations.entries(userCart + ":" + admin.getId());
        //如果存在
        if(!CollectionUtils.isEmpty(cartMap)){
            CartVo cartVo = JsonUtil.jsonStr2Object(cartMap.get(String.valueOf(uid)),CartVo.class);
            cartVo.setGoodsNum(num);
            cartMap.put(String.valueOf(uid),JsonUtil.object2JsonStr(cartVo));
            hashOperations.putAll(userCart+":"+admin.getId(),cartMap);
            return BaseResult.success();
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


}
