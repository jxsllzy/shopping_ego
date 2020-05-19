package com.ego.portal.service.impl;

import com.ego.portal.mapper.ShopContextMapper;
import com.ego.portal.mapper.ShopInformationMapper;
import com.ego.portal.pojo.ShopContext;
import com.ego.portal.pojo.ShopInformation;
import com.ego.portal.service.ProductInfoService;
import com.ego.portal.vo.ProductName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ShopInformationMapper shopInformationMapper;
    @Autowired
    private ShopContextMapper shopContextMapper;


    //商品详情查询-根据商品id查询对应得信息
    @Override
    public ShopInformation productInfo(Integer id) {
        return shopInformationMapper.selectByPrimaryKey(id);
    }

    //根据商品分类id查询分类信息
    @Override
    public ProductName productName(Integer id,Integer sort) {
        return shopInformationMapper.selectbySort(id, sort);
    }

    //根据商品id查询商品留言和用户名
    @Override
    public List<ShopContext> shopContext(Integer id) {
        return shopContextMapper.selectBySid(id);
    }


}
