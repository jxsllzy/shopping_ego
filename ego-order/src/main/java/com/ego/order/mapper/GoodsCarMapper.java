package com.ego.order.mapper;

import com.ego.order.pojo.GoodsCar;
import com.ego.order.pojo.GoodsCarExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCarMapper {
    long countByExample(GoodsCarExample example);

    int deleteByExample(GoodsCarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsCar record);

    int insertSelective(GoodsCar record);

    List<GoodsCar> selectByExample(GoodsCarExample example);

    GoodsCar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsCar record, @Param("example") GoodsCarExample example);

    int updateByExample(@Param("record") GoodsCar record, @Param("example") GoodsCarExample example);

    int updateByPrimaryKeySelective(GoodsCar record);

    int updateByPrimaryKey(GoodsCar record);

    int insertGoodsCarList(@Param("goodsCarList") List<GoodsCar> goodsCarList);
}