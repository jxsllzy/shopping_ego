package com.ego.order.service.impl;

import com.ego.common.pojo.UserInformation;
import com.ego.common.util.JsonUtil;
import com.ego.order.service.OrderFormService;
import com.ego.rpc.vo.OrderFromVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderFormServiceImpl implements OrderFormService{

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Value("${user.form}")
    private String userForm;

    @Override
    public OrderFromVo findOrderFormById(UserInformation admin, Integer addressNum) {
        //判断用户数据是否存在
        if (admin==null&&admin.getId()==null){
            return null;
        }
        //查询redis数据
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        Integer userId = admin.getId();
        String str = (String) valueOperations.get(userForm+":"+userId);
        //如果有值，直接转成list返回，没有就取数据库查询
        if(!StringUtils.isEmpty(str)){
            List<OrderFromVo> list = JsonUtil.jsonToList(str,OrderFromVo.class);
            return list.get(addressNum);
        }
        return null;
    }

}
