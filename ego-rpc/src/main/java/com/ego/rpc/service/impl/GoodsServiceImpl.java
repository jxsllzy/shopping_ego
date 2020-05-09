package com.ego.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ego.common.result.EgoPageInfo;
import com.ego.rpc.service.GoodsService;
import com.ego.rpc.vo.GoodsVo;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * elasticasearch搜索
 */
@Service(interfaceClass =  GoodsService.class)
@Component
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public EgoPageInfo<GoodsVo> doSearch(String search,Integer pageNum,Integer pageSize) {

        System.out.println("进来了------------");
        //创建分页
        EgoPageInfo<GoodsVo> egoPageInfo = null;
        try {
            //elasticsearch指定索引库
            SearchRequest searchRequest = new SearchRequest("ershou");
            //构建查询对象
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            //设置查询条件
            searchSourceBuilder.query(QueryBuilders.multiMatchQuery(search,"goodsName"));
            //设置分页条件
            searchSourceBuilder.from(pageNum-1).size(pageSize);
            //设置高亮对象
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            //设置高亮字段和样式
            highlightBuilder.field("goodsName")
                    .preTags("<span style='color:red;'>")
                    .postTags("</span>");
            //把高亮对象放到查询对象
            searchSourceBuilder.highlighter(highlightBuilder);
            //把查询对象放入request中
            searchRequest.source(searchSourceBuilder);
            //执行请求
            SearchResponse response = null;

            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            //拿到结果，处理结果
            //总条数
            Long size = response.getHits().getTotalHits().value;
            //命中的数据
            List<GoodsVo> list = new ArrayList<>();
            //解析es中获得的数据
            SearchHit[] hits = response.getHits().getHits();
            for (SearchHit hit:hits){
//                     "goodsMsg": "来自wsk的爱",
//                    "goodsId": 3,
//                    "goodsName": "爱我的人",
//                    "goodsPrice": 12.00,
//                    "imagrUrl": "http://q946d09ir.bkt.clouddn.com/cont3.jpg",
//                    "@timestamp": "2020-05-08T13:29:00.588Z",
//                    "@version": "1"

                Integer goodsId = (Integer) hit.getSourceAsMap().get("goodsId");
                String goodsName = (String) hit.getSourceAsMap().get("goodsName");
                String goodsMsg = (String) hit.getSourceAsMap().get("goodsMsg");
                String imageUrl = (String) hit.getSourceAsMap().get("imagrUrl");
                BigDecimal goodsPrice = new BigDecimal(String.valueOf(hit.getSourceAsMap().get("goodsPrice")));
                imageUrl = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1589033473&di=dd3c2b81d2cd757aa8adbf0945734f2f&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg";
                GoodsVo goodsVo = new GoodsVo(goodsId,goodsName,goodsMsg,goodsPrice,imageUrl);
                list.add(goodsVo);
            }
            EgoPageInfo<GoodsVo> goodsVoEgoPageInfo = new EgoPageInfo<>(pageNum,pageSize,size.intValue());
            goodsVoEgoPageInfo.setResult(list);
            return goodsVoEgoPageInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
