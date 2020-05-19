package com.ego.order.mapper;

import com.ego.order.pojo.BoughtShop;
import com.ego.order.pojo.BoughtShopExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoughtShopMapper {
    long countByExample(BoughtShopExample example);

    int deleteByExample(BoughtShopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoughtShop record);

    int insertSelective(BoughtShop record);

    List<BoughtShop> selectByExample(BoughtShopExample example);

    BoughtShop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BoughtShop record, @Param("example") BoughtShopExample example);

    int updateByExample(@Param("record") BoughtShop record, @Param("example") BoughtShopExample example);

    int updateByPrimaryKeySelective(BoughtShop record);

    int updateByPrimaryKey(BoughtShop record);

    int insertBoughtShopList(@Param("boughtShopList") List<BoughtShop> boughtShopList);
}