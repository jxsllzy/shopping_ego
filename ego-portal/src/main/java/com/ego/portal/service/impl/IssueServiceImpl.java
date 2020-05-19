package com.ego.portal.service.impl;

import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.common.result.EgoPageInfo;
import com.ego.common.util.JsonUtil;
import com.ego.portal.mapper.AllKindsMapper;
import com.ego.portal.mapper.ClassificationMapper;
import com.ego.portal.mapper.ShopInformationMapper;
import com.ego.portal.mapper.SpecifickindsMapper;
import com.ego.portal.pojo.*;
import com.ego.portal.service.IssueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 搜索自己有的的商品
 */
@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private AllKindsMapper allKindsMapper;
    @Autowired
    private ClassificationMapper classificationMapper;
    @Autowired
    private SpecifickindsMapper specifickindsMapper;
    @Autowired
    private ShopInformationMapper shopInformationMapper;
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public BaseResult selectPage(UserInformation userInformation, Integer pageNum, Integer pageSize) {
        //校验参数
        if(null == pageNum){
            pageNum = 1;
        }
        if(null == pageSize){
            pageSize = 12;
        }
        //开启分页
        PageHelper.startPage(pageNum, pageSize);

        //确定redis得key
        String[] goodsKeyArr= new String[]{
                "shopUserInfo:list:",
                "pageNum_"+pageNum+":",
                "pageSize_"+pageSize+":"
        };
        StringBuilder sg= new StringBuilder();
        for (String s : goodsKeyArr) {
            sg.append(s);
        }
        String shopInfoListKey= sg.toString();
        //从redis里面获取数据，数据不为空，直接返回，数据为空去数据库里面查询
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        String shopJsonList = stringStringValueOperations.get(shopInfoListKey);
        if(!StringUtils.isEmpty(shopJsonList)){
            PageInfo pageInfo = JsonUtil.jsonStr2Object(shopJsonList, PageInfo.class);
            return BaseResult.success(pageInfo);
        }
        //创建查询条件
        ShopInformationExample shopInformationExample = new ShopInformationExample();
        shopInformationExample.createCriteria().andUidEqualTo(userInformation.getId()).andDisplayEqualTo(1);
        //得到分页对象
        List<ShopInformation> shopInformations = shopInformationMapper.selectByExample(shopInformationExample);
        shopInformations.forEach(a->{
            String kind=null;
            Integer sort = a.getSort();
            Specifickinds specifickinds = selectOne(sort);
            Classification classification = selectTwo(specifickinds.getCid());
            AllKinds allKinds = selectThree(classification.getAid());
            kind=allKinds.getName()+"-"+classification.getName()+"-"+specifickinds.getName();
            a.setKind(kind);

        });
        //查询出结果，放入分页对象中。返回结果
        if (!CollectionUtils.isEmpty(shopInformations)){
            PageInfo<ShopInformation> pageInfo = new PageInfo<>(shopInformations);
            //将数据放入redis
            stringStringValueOperations.set(shopInfoListKey,JsonUtil.object2JsonStr(pageInfo));

            return BaseResult.success(pageInfo);
        }

        //返回错误结果
        return BaseResult.error();
    }

    /**
     * 删除发布商品信息
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteInfo(Integer id) {
        ShopInformation shopInformation = new ShopInformation();
        shopInformation.setDisplay(0);
        ShopInformationExample shopInformationExample = new ShopInformationExample();
        shopInformationExample.createCriteria().andIdEqualTo(id);
        int result = shopInformationMapper.updateByExampleSelective(shopInformation, shopInformationExample);
        //清除redis里面得商品信息使其保持最
        if(result>0){
            redisTemplate.delete("shopUserInfo:list:pageNum_1:pageSize_6:");
            return BaseResult.success();
        }else {
            return BaseResult.error();
        }
    }

    /**
     * 查询最低级的名称
     * @param sid
     * @return
     */
    public Specifickinds selectOne(Integer sid) {
        return specifickindsMapper.selectByPrimaryKey(sid);

    }


    public Classification selectTwo(Integer cid) {
        return  classificationMapper.selectByPrimaryKey(cid);
    }


    public AllKinds selectThree(Integer aid) {
        return  allKindsMapper.selectByPrimaryKey(aid);
    }
}
