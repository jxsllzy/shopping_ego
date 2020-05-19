package com.ego.search.service.impl;

import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.common.util.JsonUtil;
import com.ego.rpc.vo.OrderFromVo;
import com.ego.search.mapper.OrderFormMapper;
import com.ego.search.pojo.OrderForm;
import com.ego.search.pojo.OrderFormExample;
import com.ego.search.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFormServiceImpl implements OrderFormService {


    @Value("${user.form}")
    private String userForm;

    @Autowired
    private OrderFormMapper orderFormMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<OrderFromVo> findOrderForm(UserInformation userInformation) {
        List<OrderFromVo> list = null;
        //查询redis
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        Integer userId = userInformation.getId();
        String str = (String) valueOperations.get(userForm+":"+userId);
        //如果有值，直接转成list返回，没有就取数据库查询
        if(!StringUtils.isEmpty(str)){
            System.out.println("redis查询");
            list = JsonUtil.jsonToList(str,OrderFromVo.class);
            return list;
        }
        //创建返回数据
        BaseResult baseResult = new BaseResult();
        //根据用户id查询地址数据
        OrderFormExample example = new OrderFormExample();
        example.createCriteria().andUidEqualTo(userInformation.getId());
        List<OrderForm> orderForms = orderFormMapper.selectByExample(example);
        String username = userInformation.getUsername();
        String phone = userInformation.getPhone();
        if(orderForms==null){
            return null;
        }
        list = orderForms.stream()
                .map(e->{
                    OrderFromVo orderFromVo = new OrderFromVo();
                    orderFromVo.setId(e.getId());
                    orderFromVo.setAddress(e.getAddress());
                    orderFromVo.setContext(e.getContext());
                    orderFromVo.setPhone(phone);
                    orderFromVo.setUsername(username);
                    return orderFromVo;
                }).collect(Collectors.toList());
        valueOperations.set(userForm+":"+userId,JsonUtil.object2JsonStr(list));

        return list;
    }

    @Override
    public BaseResult saveOrderForm(UserInformation userInformation) {
        OrderForm orderFrom = new OrderForm();
        orderFrom.setDisplay(1);
        orderFrom.setModified(new Date());
        orderFrom.setUid(userInformation.getId());
        orderFrom.setAddress("尚学堂");
        orderFrom.setContext("35期学生");
        int i = orderFormMapper.insertSelective(orderFrom);
        if (i<1){
            return BaseResult.error();
        }
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        redisTemplate.delete(userForm+":"+userInformation.getId());
        return BaseResult.success();
    }
}
