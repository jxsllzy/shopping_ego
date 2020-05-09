package com.ego.portal.service.impl;

import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.UserWant;
import com.ego.portal.pojo.UserWantExample;
import com.ego.portal.service.UserWantService;
import org.springframework.stereotype.Service;

/**
 * 求购商品Service实现类
 *
 * @author : WangJinDong
 * @version : 1.0.0
 * @date : 2020-05-09 11:50
 **/
@Service
public class UserWantServiceImpl implements UserWantService {

    @Override
    public BaseResult select(UserWantExample userWantExample, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public BaseResult update(UserWant userWant) {
        return null;
    }

    @Override
    public BaseResult delete(Integer id) {
        return null;
    }
}
