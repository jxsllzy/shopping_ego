package com.ego.rpc.mapper;



import com.ego.rpc.pojo.SpecificKinds;
import com.ego.rpc.pojo.SpecificKindsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecificKindsMapper {
    long countByExample(SpecificKindsExample example);

    int deleteByExample(SpecificKindsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpecificKinds record);

    int insertSelective(SpecificKinds record);

    List<SpecificKinds> selectByExample(SpecificKindsExample example);

    SpecificKinds selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpecificKinds record, @Param("example") SpecificKindsExample example);

    int updateByExample(@Param("record") SpecificKinds record, @Param("example") SpecificKindsExample example);

    int updateByPrimaryKeySelective(SpecificKinds record);

    int updateByPrimaryKey(SpecificKinds record);
}