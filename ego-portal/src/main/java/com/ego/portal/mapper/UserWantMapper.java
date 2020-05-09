package com.ego.portal.mapper;


import com.ego.portal.pojo.UserWant;
import com.ego.portal.pojo.UserWantExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserWantMapper {
    long countByExample(UserWantExample example);

    int deleteByExample(UserWantExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserWant record);

    int insertSelective(UserWant record);

    List<UserWant> selectByExample(UserWantExample example);

    UserWant selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserWant record, @Param("example") UserWantExample example);

    int updateByExample(@Param("record") UserWant record, @Param("example") UserWantExample example);

    int updateByPrimaryKeySelective(UserWant record);

    int updateByPrimaryKey(UserWant record);
}