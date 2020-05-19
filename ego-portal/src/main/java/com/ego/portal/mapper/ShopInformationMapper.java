package com.ego.portal.mapper;

import com.ego.portal.pojo.ShopInformation;
import com.ego.portal.pojo.ShopInformationExample;
import com.ego.portal.vo.ProductName;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

    //首页-轮播图的商品展示-根据商品数量升序前5条数据
    List<ShopInformation> select();
    //首页-精品的商品展示-根据商品数量升序第5到10条数据
    List<ShopInformation> selectbySales();
    //根据商品分类id查询分类信息
    ProductName selectbySort(Integer id, Integer sort);

}