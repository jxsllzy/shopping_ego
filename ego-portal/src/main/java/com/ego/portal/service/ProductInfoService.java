package com.ego.portal.service;

import com.ego.portal.pojo.ShopContext;
import com.ego.portal.pojo.ShopInformation;
import com.ego.portal.vo.ProductName;

import java.util.List;

public interface ProductInfoService {
    //商品详情查询-根据商品id查询对应得信息
    ShopInformation productInfo(Integer id);
    //商品留言信息查询-根据商品id

    //根据商品分类id查询分类信息
    ProductName productName(Integer id, Integer sort);

    //根据商品id查询商品留言和用户用户名
    List<ShopContext> shopContext(Integer id);
}
