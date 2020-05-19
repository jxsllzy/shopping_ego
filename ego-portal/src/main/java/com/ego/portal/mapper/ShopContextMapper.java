package com.ego.portal.mapper;

import com.ego.portal.pojo.ShopContext;
import com.ego.portal.pojo.ShopContextExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopContextMapper {
    long countByExample(ShopContextExample example);

    int deleteByExample(ShopContextExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopContext record);

    int insertSelective(ShopContext record);

    List<ShopContext> selectByExample(ShopContextExample example);

    ShopContext selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopContext record, @Param("example") ShopContextExample example);

    int updateByExample(@Param("record") ShopContext record, @Param("example") ShopContextExample example);

    int updateByPrimaryKeySelective(ShopContext record);

    int updateByPrimaryKey(ShopContext record);
    //根据商品id查询商品留言和用户名
    List<ShopContext> selectBySid(Integer sid);
}