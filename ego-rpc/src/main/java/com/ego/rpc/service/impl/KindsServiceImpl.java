package com.ego.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ego.common.util.JsonUtil;
import com.ego.rpc.mapper.AllKindsMapper;
import com.ego.rpc.mapper.ClassificationMapper;
import com.ego.rpc.mapper.SpecificKindsMapper;
import com.ego.rpc.pojo.*;
import com.ego.rpc.service.KindsService;
import com.ego.rpc.vo.ClassificationVo;
import com.ego.rpc.vo.KindsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = KindsService.class)
@Component
public class KindsServiceImpl implements KindsService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //一级分类
    @Resource
    private AllKindsMapper allKindsMapper;

    //二级分类
    @Resource
    private ClassificationMapper classificationMapper;

    //三级分类
    @Resource
    private SpecificKindsMapper specificKindsMapper;

    @Value("${goods.category.list.key}")
    private String goodsCategoryList;


    /**
     * 查询所有分类
     * @return
     */
    @Override
    public List<KindsVo> queryAllKindsList() {
        /**
         * 先判断redis中有无商品分类数据
         *      有数据：则返回数据
         *      无数据：则从数据库中查询
         */
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //redis中查询返回json字符串
        String goodsCategoryJson = (String) valueOperations.get(goodsCategoryList);
        if(!StringUtils.isEmpty(goodsCategoryJson)){
            //将json字符串转化成list
            List<KindsVo> goodsCategoryVoList = JsonUtil.jsonToList(goodsCategoryJson, KindsVo.class);
            return goodsCategoryVoList;
        }

        //顶级分类
        List<KindsVo> gcvList01 = new ArrayList<>();
        AllKindsExample goodsCategoryExample = new AllKindsExample();
        List<AllKinds> gcList01 = allKindsMapper.selectByExample(goodsCategoryExample);

        gcList01.forEach(gc01->{
            KindsVo gcv01 = new KindsVo();
            //拷贝
            BeanUtils.copyProperties(gc01,gcv01);

            //二级分类
            List<ClassificationVo> gcvList02 = new ArrayList<>();
            ClassificationExample classificationExample = new ClassificationExample();
            classificationExample.createCriteria().andAidEqualTo(gc01.getId());
            List<Classification> gcList02 = classificationMapper.selectByExample(classificationExample);
            gcList02.forEach(gc02->{
                ClassificationVo gcv02 = new ClassificationVo();
                BeanUtils.copyProperties(gc02,gcv02);


                //三级分类
                List<SpecificKinds> gcvList03 = new ArrayList<>();
                SpecificKindsExample specificKindsExample = new SpecificKindsExample();
                specificKindsExample.createCriteria().andCidEqualTo(gc02.getId());
                List<SpecificKinds> gcList03 =specificKindsMapper.selectByExample(specificKindsExample);
                gcList03.forEach(gc03->{
                    SpecificKinds gcv03 = new SpecificKinds();
                    BeanUtils.copyProperties(gc03,gcv03);
                    gcvList03.add(gcv03);
                });
                //将三级分类放入二级分类的list
                gcv02.setChildrenList(gcvList03);
                gcvList02.add(gcv02);

            });
            //将二级分类放入顶级分类的list
            gcv01.setChildrenList(gcvList02);
            gcvList01.add(gcv01);
        });
        //list转化为json字符串
        String  gcvListStr= JsonUtil.object2JsonStr(gcvList01);
        //将从数据库中查询的商品分类数据存储到redis中
        valueOperations.set(goodsCategoryList,gcvListStr);
        return gcvList01;
    }
}
