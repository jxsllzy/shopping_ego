package com.ego.portal.service.impl;

import com.ego.common.result.BaseResult;
import com.ego.common.util.JsonUtil;
import com.ego.portal.mapper.ShopInformationMapper;
import com.ego.portal.pojo.ShopInformation;
import com.ego.portal.pojo.ShopInformationExample;
import com.ego.portal.service.ShopInformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private ShopInformationMapper shopInformationMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${shopgoods.productInfo.list.key}")
    private String shopGoodsRedisKey;

    //首页-轮播图的商品展示-根据商品数量升序前5条数据
    @Override
    public List<ShopInformation> shop() {
        //先从redis查询是否有数据
        ValueOperations<String, Object> stringObjectClusterOperations = redisTemplate.opsForValue();
        String gList = (String) stringObjectClusterOperations.get(shopGoodsRedisKey);
        //如果有值，直接转成list返回，没有就去数据库查询
        if(!StringUtils.isEmpty(gList)){
            List<ShopInformation> gLists =JsonUtil.jsonToList(gList, ShopInformation.class);
            //将查询结果设置至分页对象
            return gLists;
        }

        //redis没有查询到，从数据库查询
        List<ShopInformation> list = shopInformationMapper.select();
        //将查询结果设置至分页对象
        if (!CollectionUtils.isEmpty(list)){
            //将数据库查到的数据转成json字符串存入redis
            stringObjectClusterOperations.set(shopGoodsRedisKey, JsonUtil.object2JsonStr(list));
            return list;
        }

        return null;
    }


    //精品商品分页查询
    @Override
    public BaseResult selectGoodsListByPage(Integer pageNum, Integer pageSize) {
        //构建分页对象
        PageHelper.startPage(pageNum,pageSize);
        //redis
        String[] goodsKeyArr = new String[]{
                "shopgoods:productInfo:list:",
                "pageNum_:" };
        //如果分类id不为空
        if (null != pageNum) {
            //相当于pageNum_1:
            goodsKeyArr[1] = "pageNum_" + pageNum + ":";
        }
        //循环把所有的key拼接好
        StringBuilder sb = new StringBuilder();
        for (String s : goodsKeyArr) {
            sb.append(s);
        }
        String shopGoodsRedisKey = sb.toString();

        //先从redis查询是否有数据
        ValueOperations<String, Object> stringObjectClusterOperations = redisTemplate.opsForValue();
        String gList = (String) stringObjectClusterOperations.get(shopGoodsRedisKey);
        //如果有值，直接转成list返回，没有就去数据库查询
        if(!StringUtils.isEmpty(gList)){
            PageInfo pageInfo = JsonUtil.jsonStr2Object(gList, PageInfo.class);
            //将查询结果设置至分页对象
            return BaseResult.success(pageInfo);
        }

        //redis没有查询到，从数据库查询
        List<ShopInformation> list = shopInformationMapper.selectByExample(new ShopInformationExample());

        //将查询结果设置至分页对象
        if (!CollectionUtils.isEmpty(list)){
            PageInfo<ShopInformation> pageInfo = new PageInfo<>(list,5);
            //将数据库查到的数据转成json字符串存入redis
            stringObjectClusterOperations.set(shopGoodsRedisKey, JsonUtil.object2JsonStr(pageInfo));
            return BaseResult.success(pageInfo);
        }else {
            //如果没有查到数据，将null放入redis,并设置失效时间1分钟
            stringObjectClusterOperations.set(shopGoodsRedisKey, new PageInfo<>(new ArrayList<ShopInformation>()),60, TimeUnit.SECONDS);
        }
        return BaseResult.error();
    }


    //首页-精品的商品展示-根据商品数量升序第5到10条数据
    @Override
    public List<ShopInformation> shops() {

        return shopInformationMapper.selectbySales();
    }

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
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String shopInfosListStr = (String) valueOperations.get(shopInfosKeysListStr);
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
