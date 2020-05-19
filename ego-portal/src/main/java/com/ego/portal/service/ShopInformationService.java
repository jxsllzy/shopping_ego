package com.ego.portal.service;

import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.ShopInformation;

import java.util.List;


public interface ShopInformationService {

    //首页-轮播图的商品展示-根据商品数量升序前5条数据
    List<ShopInformation> shop();

    //首页-精品的商品展示-根据商品数量升序第5到10条数据
    List<ShopInformation> shops();

    //精品商品分页查询
    BaseResult selectGoodsListByPage(Integer pageNum, Integer pageSize);

    BaseResult queryShopInfoList(ShopInformation shopInformation,Integer pageNum, Integer pageSize);

}

