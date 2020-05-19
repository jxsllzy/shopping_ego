package com.ego.search.mapper;

import com.ego.search.pojo.OrderForm;
import com.ego.search.pojo.OrderFormExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderFormMapper {
    long countByExample(OrderFormExample example);

    int deleteByExample(OrderFormExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderForm record);

    int insertSelective(OrderForm record);

    List<OrderForm> selectByExample(OrderFormExample example);

    OrderForm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderForm record, @Param("example") OrderFormExample example);

    int updateByExample(@Param("record") OrderForm record, @Param("example") OrderFormExample example);

    int updateByPrimaryKeySelective(OrderForm record);

    int updateByPrimaryKey(OrderForm record);
}