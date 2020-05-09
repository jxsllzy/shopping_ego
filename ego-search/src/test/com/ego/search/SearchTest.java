package com.ego.search;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.result.EgoPageInfo;
import com.ego.rpc.service.GoodsService;
import com.ego.rpc.vo.GoodsVo;
import org.junit.jupiter.api.Test;
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

}
