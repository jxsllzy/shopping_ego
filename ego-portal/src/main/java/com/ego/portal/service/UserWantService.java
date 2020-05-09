package com.ego.portal.service;

import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.UserWant;
import com.ego.portal.pojo.UserWantExample;

/**
 * 求购商品Service接口
 *
 * @author : WangJinDong
 * @version : 1.0.0
 * @date : 2020-05-09 11:48
 **/
public interface UserWantService {

    //查询

    /**
     * 分页搜索--求购商品
     * @param userWantExample
     * @param pageNum
     * @param pageSize
     * @return
     */
    BaseResult select(UserWantExample userWantExample, Integer pageNum, Integer pageSize);


    //更新

    /**
     * 增加或修改求购商品
     * @param userWant
     * @return
     */
    BaseResult update(UserWant userWant);


    //删除

    /**
     * 根据求购商品id删除求购商品
     * @Param [id]
     * @return com.ego.common.result.BaseResult
     */
    BaseResult delete(Integer id);
}
