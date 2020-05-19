package com.ego.rpc.mapper;


import com.ego.rpc.pojo.AllKinds;
import com.ego.rpc.pojo.AllKindsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AllKindsMapper {
    long countByExample(AllKindsExample example);

    int deleteByExample(AllKindsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AllKinds record);

    int insertSelective(AllKinds record);

    List<AllKinds> selectByExample(AllKindsExample example);

    AllKinds selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AllKinds record, @Param("example") AllKindsExample example);

    int updateByExample(@Param("record") AllKinds record, @Param("example") AllKindsExample example);

    int updateByPrimaryKeySelective(AllKinds record);

    int updateByPrimaryKey(AllKinds record);
}