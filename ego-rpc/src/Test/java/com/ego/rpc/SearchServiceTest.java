package com.ego.rpc;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.UserInformation;
import com.ego.common.result.EgoPageInfo;
import com.ego.rpc.service.GoodsCartService;
import com.ego.rpc.service.GoodsService;
import com.ego.rpc.vo.CartVo;
import com.ego.rpc.vo.GoodsVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SearchServiceTest {

    @Reference(interfaceClass = GoodsService.class)
    private GoodsService goodsService;

    @Test
    public void test01(){
        CartVo cartVo = goodsService.findGoodsByCartId(35);
        System.out.println(cartVo);
    }

    @Test
    public void test02(){
        EgoPageInfo<GoodsVo> egoPageInfo = goodsService.doSearch("孤独", 1, 5);
        List<GoodsVo> result = egoPageInfo.getResult();

        result.forEach(e-> System.out.println(result));
    }

    @Reference(interfaceClass = GoodsCartService.class)
    private GoodsCartService goodsCartService;

    @Test
    public void test03(){
        UserInformation userInformation = new UserInformation();
        userInformation.setId(99999);
        goodsCartService.getCartList(userInformation);
    }

}
