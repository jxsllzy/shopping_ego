package com.ego.rpc.service;

import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.rpc.vo.CartResult;
import com.ego.rpc.vo.CartVo;

import javax.servlet.http.HttpServletRequest;

/**
 * 购物车
 */
public interface GoodsCartService {

    /**
     * 添加购物车至redis
     */
    public BaseResult addCartToRedis(CartVo goodsVo, UserInformation userInformation);

    /**
     * 添加购物车cookie
     */
    public BaseResult addCartToCookie(CartVo goodsVo, UserInformation userInformation, HttpServletRequest request);

    /**
     * 获取购物车数据
     * @return
     */
    public CartResult getCartList(UserInformation userInformation);

    /**
     * 删除购物车
     */
    public BaseResult deleteCartById(Integer id,UserInformation userInformation);

    BaseResult clearCar(UserInformation userInformation);

    //更新购物车
    BaseResult updateCartSum(Integer uid, Integer num, UserInformation userInformation);
}
