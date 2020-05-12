package com.ego.portal.service.impl;

import com.ego.common.result.BaseResult;
import com.ego.portal.mapper.UserWantMapper;
import com.ego.portal.pojo.UserWant;
import com.ego.portal.pojo.UserWantExample;
import com.ego.portal.service.UserWantService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 求购商品Service实现类
 *
 * @author : WangJinDong
 * @version : 1.0.0
 * @date : 2020-05-09 11:50
 **/
@Service
public class UserWantServiceImpl implements UserWantService {

    @Autowired
    private UserWantMapper userWantMapper;

    /**
     * @Description //TODO 查询
     * @Param [userWantExample, pageNum, pageSize]
     * @return com.ego.common.result.BaseResult
     */
    @Override
    public BaseResult select(UserWant userWant, Integer pageNum, Integer pageSize) {

        //校验参数
        if(null == pageNum){
            pageNum = 1;
        }
        if(null == pageSize){
            pageSize = 12;
        }

        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        //创建查询对象
        UserWantExample userWantExample = new UserWantExample();
        //创建查询条件
        UserWantExample.Criteria criteria = userWantExample.createCriteria();

        //如果userWant为空，查询所有
        if (null == userWant){
            List<UserWant> list = userWantMapper.selectByExample(userWantExample);
            PageInfo<UserWant> pageInfo = new PageInfo<>(list);
            return BaseResult.success(pageInfo);
        }

        //设置查询条件
        //如果种类不为空
        if(null != userWant.getSort()){
            criteria.andSortEqualTo(userWant.getSort());
        }
        //关键词不为空
        if(null != userWant.getName() && userWant.getName().length() == 0){
            criteria.andNameLike("%"+userWant.getName()+"%");
        }
        if(null != userWant.getUid()){
            criteria.andUidEqualTo(userWant.getUid());
        }
        //只查询有效
        criteria.andDisplayEqualTo(1);

        //执行查询
        List<UserWant> list = userWantMapper.selectByExample(userWantExample);
        //查询出结果，放入分页对象中。返回结果
        if (!CollectionUtils.isEmpty(list)){
            PageInfo<UserWant> pageInfo = new PageInfo<>(list);
            return BaseResult.success(pageInfo);
        }

        //返回错误结果
        return BaseResult.error();
    }

    /**
     * @Description //TODO 更新
     * @Param [userWant]
     * @return com.ego.common.result.BaseResult
     */
    @Override
    public BaseResult update(UserWant userWant) {

        //校验参数
        if(null==userWant || null==userWant.getName() || null==userWant.getUid()){
            return BaseResult.error();
        }

        //校验userWant的Id是否为空
        if(null != userWant.getId()){
            //查询是否存在这个求购商品
            if(null != userWantMapper.selectByPrimaryKey(userWant.getId())){
                //都不为空，则修改
                return userWantMapper.updateByPrimaryKeySelective(userWant)==1 ? BaseResult.success() : BaseResult.error();
            }
        }
        //userWant的id为空，则添加
        return userWantMapper.insertSelective(userWant)==1 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * @Description //TODO 删除
     * @Param [id]
     * @return com.ego.common.result.BaseResult
     */
    @Override
    public BaseResult delete(Integer id) {
        if(null != id){
            UserWant userWant = userWantMapper.selectByPrimaryKey(id);
            if(null != userWant){
                userWant.setDisplay(0);
                return userWantMapper.updateByPrimaryKeySelective(userWant)==1 ? BaseResult.success() : BaseResult.error();
            }
        }
        return BaseResult.error();
    }
}
