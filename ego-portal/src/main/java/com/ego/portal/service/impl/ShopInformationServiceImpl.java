package com.ego.portal.service.impl;

import com.ego.common.result.BaseResult;
import com.ego.common.util.JsonUtil;
import com.ego.portal.mapper.ShopInformationMapper;
import com.ego.portal.pojo.ShopInformation;
import com.ego.portal.pojo.ShopInformationExample;
import com.ego.portal.service.ShopInformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ShopInformationServiceImpl implements ShopInformationService {

    @Resource
    private ShopInformationMapper shopInformationMapper;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public BaseResult queryShopInfoList(ShopInformation shopInformation, Integer pageNum, Integer pageSize) {
        String[] shopInfosKeysList = new String[]{
                "goods:pageNum_"+pageNum+":pageSize_"+pageSize+":",
                "name_:"
        };
        ShopInformationExample shopInformationExample = new ShopInformationExample();

        if(!StringUtils.isEmpty(shopInformation.getName())){
            shopInformationExample.createCriteria().andNameLike("%"+shopInformation.getName()+"%");
            shopInfosKeysList[1]="name_"+shopInformation.getName()+":";
        }
        //循环并拼接为字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : shopInfosKeysList) {
            stringBuilder.append(s);
        }
        String shopInfosKeysListStr = stringBuilder.toString();
        /**
         * 先判断redis中有无商品数据
         *      有数据：则返回数据
         *      无数据：则从数据库中查询
         */
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String shopInfosListStr = valueOperations.get(shopInfosKeysListStr);
        if(!com.alibaba.druid.util.StringUtils.isEmpty(shopInfosListStr)){
            PageInfo pageInfo = JsonUtil.jsonStr2Object(shopInfosListStr, PageInfo.class);
            return BaseResult.success(pageInfo);
        }

        PageHelper.startPage(pageNum,pageSize);
        List<ShopInformation> shopInformations = shopInformationMapper.selectByExample(shopInformationExample);
        if(null!=shopInformations){
            PageInfo<ShopInformation> shopInformationPageInfo = new PageInfo<>(shopInformations);
            //list转化为json字符串
            String pageInfoJsonStr = JsonUtil.object2JsonStr(shopInformationPageInfo);
            //将从数据库中查询的商品数据存储到redis中
            valueOperations.set(shopInfosKeysListStr,pageInfoJsonStr);

            return BaseResult.success(shopInformationPageInfo);
        }else {
            //如果数据库查询数据为空，将查询的空值返回给redis,并设置失效时间
            valueOperations.set(shopInfosKeysListStr,JsonUtil.object2JsonStr(new PageInfo<>(new ArrayList<ShopInformation>())),60, TimeUnit.SECONDS);
            return BaseResult.error();
        }

    }
}
