package com.ego.rpc.service;

import com.ego.common.result.BaseResult;
import com.ego.common.result.EgoPageInfo;
import com.ego.rpc.vo.CartResult;
import com.ego.rpc.vo.CartVo;
import com.ego.rpc.vo.GoodsVo;

import java.util.List;

public interface GoodsService {

    /**
     * 搜索
     */
    EgoPageInfo<GoodsVo> doSearch(String search,Integer pageNum,Integer pageSize);

    /**
     * 根据id查询购物车数据
     * @param id
     * @return
     */
    CartVo findGoodsByCartId(Integer id);

    CartResult findGoodsNumById(CartResult cartList);

    BaseResult updateGoodsInfo(CartResult cartResult);
}
