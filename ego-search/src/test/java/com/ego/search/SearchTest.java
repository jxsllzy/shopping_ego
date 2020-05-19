package com.ego.search;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.common.result.EgoPageInfo;
import com.ego.rpc.service.GoodsCartService;
import com.ego.rpc.service.GoodsService;
import com.ego.rpc.vo.GoodsVo;
import com.ego.rpc.vo.OrderFromVo;
import com.ego.search.mapper.OrderFormMapper;
import com.ego.search.service.impl.OrderFormServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SearchTest {

    @Test
    public void test01(){
        System.out.println("11111");
    }

    @Reference(interfaceClass = GoodsService.class)
    private GoodsService goodsService;

    @Test
    public void test02(){
        EgoPageInfo<GoodsVo> egoPageInfo = goodsService.doSearch("孤独", 1, 5);
        List<GoodsVo> result = egoPageInfo.getResult();
        result.forEach(e-> System.out.println(result));
    }

    @Reference
    private GoodsCartService goodsCartService;

    @Test
    public void test03(){
        UserInformation userInformation = new UserInformation();
        userInformation.setId(99999);
        BaseResult baseResult = goodsCartService.deleteCartById(6, userInformation);
        System.out.println(baseResult);
    }

    @Autowired
    OrderFormServiceImpl orderFormService;

    @Test
    public void test04(){
        UserInformation userInformation = new UserInformation();
        userInformation.setId(99999);
        List<OrderFromVo> orderForm = orderFormService.findOrderForm(userInformation);
        System.out.println(orderForm);
    }

    @Autowired
    private OrderFormMapper orderFormMapper;

    @Test
    public void test05(){
        orderFormMapper.selectByPrimaryKey(1);
    }



}
