package com.ego.portal.mapper;


import com.ego.portal.pojo.Specifickinds;
import com.ego.portal.pojo.SpecifickindsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecifickindsMapper {
    long countByExample(SpecifickindsExample example);

    int deleteByExample(SpecifickindsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Specifickinds record);

    int insertSelective(Specifickinds record);

    List<Specifickinds> selectByExample(SpecifickindsExample example);

    Specifickinds selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Specifickinds record, @Param("example") SpecifickindsExample example);

    int updateByExample(@Param("record") Specifickinds record, @Param("example") SpecifickindsExample example);

    int updateByPrimaryKeySelective(Specifickinds record);

    int updateByPrimaryKey(Specifickinds record);
}