package com.ego.portal.service.impl;

import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.portal.mapper.ShopInformationMapper;
import com.ego.portal.pojo.ShopInformation;
import com.ego.portal.pojo.ShopInformationExample;
import com.ego.portal.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 保存商品
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopInformationMapper shopInformationMapper;
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 保存商品
     * @param shopInformation
     * @return
     */
    @Override
    public BaseResult save(ShopInformation shopInformation, String Aid, UserInformation user) {
        //获取登陆者得id
        shopInformation.setUid(user.getId());
        shopInformation.setModified(new Date());
        if(!StringUtils.isEmpty(Aid)){
            ShopInformationExample shopInformationExample = new ShopInformationExample();
            shopInformationExample.createCriteria().andIdEqualTo(Integer.parseInt(Aid));
            int result = shopInformationMapper.updateByExampleSelective(shopInformation, shopInformationExample);
            //清除redis里面得商品信息使其保持最新
            if(result>0){
                redisTemplate.delete("shopUserInfo:list:pageNum_1:pageSize_6:");
                return BaseResult.success();
            }else {
                return BaseResult.error();
            }
        }
        //清除里面得商品信息
        int result = shopInformationMapper.insertSelective(shopInformation);
        if(result>0){
            redisTemplate.delete("shopUserInfo:list:pageNum_1:pageSize_6:");
            return BaseResult.success();
        }else {
            return BaseResult.error();
        }
    }

    /**
     * 查询当前类的信息
     * @param id
     * @return
     */
    @Override
    public ShopInformation selectById(String id) {
        Integer AId=Integer.parseInt(id);
        return shopInformationMapper.selectByPrimaryKey(AId);
    }
}
