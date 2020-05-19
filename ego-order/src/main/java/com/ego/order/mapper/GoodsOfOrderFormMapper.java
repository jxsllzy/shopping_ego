package com.ego.order.mapper;

import com.ego.order.pojo.GoodsOfOrderForm;
import com.ego.order.pojo.GoodsOfOrderFormExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsOfOrderFormMapper {
    long countByExample(GoodsOfOrderFormExample example);

    int deleteByExample(GoodsOfOrderFormExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsOfOrderForm record);

    int insertSelective(GoodsOfOrderForm record);

    List<GoodsOfOrderForm> selectByExample(GoodsOfOrderFormExample example);

    GoodsOfOrderForm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsOfOrderForm record, @Param("example") GoodsOfOrderFormExample example);

    int updateByExample(@Param("record") GoodsOfOrderForm record, @Param("example") GoodsOfOrderFormExample example);

    int updateByPrimaryKeySelective(GoodsOfOrderForm record);

    int updateByPrimaryKey(GoodsOfOrderForm record);

    int insertGoodsOfOrderFromList(@Param("goofList") List<GoodsOfOrderForm> collect);
}