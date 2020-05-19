package com.ego.portal.service;

import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.*;

import java.util.List;

/**
 * 查询所有商品信息
 */
public interface IssueService {
    //分页查询
    BaseResult selectPage(UserInformation userInformation, Integer pageNum, Integer pageSize);

    //删除发布信息
    BaseResult deleteInfo(Integer id);
}
