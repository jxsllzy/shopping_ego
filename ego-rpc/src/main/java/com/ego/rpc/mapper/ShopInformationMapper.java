package com.ego.rpc.mapper;

import com.ego.rpc.pojo.ShopInformation;
import com.ego.rpc.pojo.ShopInformationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopInformationMapper {
    long countByExample(ShopInformationExample example);

    int deleteByExample(ShopInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopInformation record);

    int insertSelective(ShopInformation record);

    List<ShopInformation> selectByExample(ShopInformationExample example);

    ShopInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopInformation record, @Param("example") ShopInformationExample example);

    int updateByExample(@Param("record") ShopInformation record, @Param("example") ShopInformationExample example);

    int updateByPrimaryKeySelective(ShopInformation record);

    int updateByPrimaryKey(ShopInformation record);
}