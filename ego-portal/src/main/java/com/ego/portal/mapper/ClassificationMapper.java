package com.ego.portal.mapper;

import com.ego.portal.pojo.Classification;
import com.ego.portal.pojo.ClassificationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassificationMapper {
    long countByExample(ClassificationExample example);

    int deleteByExample(ClassificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Classification record);

    int insertSelective(Classification record);

    List<Classification> selectByExample(ClassificationExample example);

    Classification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Classification record, @Param("example") ClassificationExample example);

    int updateByExample(@Param("record") Classification record, @Param("example") ClassificationExample example);

    int updateByPrimaryKeySelective(Classification record);

    int updateByPrimaryKey(Classification record);
}