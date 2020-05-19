package com.ego.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ego.common.result.BaseResult;
import com.ego.common.result.EgoPageInfo;
import com.ego.rpc.mapper.ShopInformationMapper;
import com.ego.rpc.pojo.ShopInformation;
import com.ego.rpc.pojo.ShopInformationExample;
import com.ego.rpc.service.GoodsService;
import com.ego.rpc.vo.CartResult;
import com.ego.rpc.vo.CartVo;
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
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * elasticasearch搜索
 */
@Service(interfaceClass =  GoodsService.class)
@Component
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ShopInformationMapper shopInformationMapper;

    /**
     * 根据商品名称es查询
     * @param search
     * @param pageNum
     * @param pageSize
     * @return
     */
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
                Integer goodsId = (Integer) hit.getSourceAsMap().get("goodsId");
                String goodsName = String.valueOf(hit.getHighlightFields().get("goodsName").fragments()[0]);
                String goodsMsg = (String) hit.getSourceAsMap().get("goodsMsg");
                String imageUrl = (String) hit.getSourceAsMap().get("imageUrl");
                BigDecimal goodsPrice = new BigDecimal(String.valueOf(hit.getSourceAsMap().get("goodsPrice")));
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

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    @Override
    public CartVo findGoodsByCartId(Integer id) {
        try {
            //elasticsearch指定索引库
            SearchRequest searchRequest = new SearchRequest("ershou");
            //构建查询对象
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            //设置查询条件
            searchSourceBuilder.query(QueryBuilders.multiMatchQuery(id,"goodsId"));
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
            if(hits!=null&&hits.length>0){
                SearchHit hit = hits[0];
                Integer goodsId = (Integer) hit.getSourceAsMap().get("goodsId");
                String goodsName = (String) hit.getSourceAsMap().get("goodsName");
                String goodsMsg = (String) hit.getSourceAsMap().get("goodsMsg");
                String imageUrl = (String) hit.getSourceAsMap().get("imagrUrl");
                BigDecimal goodsPrice = new BigDecimal(String.valueOf(hit.getSourceAsMap().get("goodsPrice")));
                imageUrl = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1589033473&di=dd3c2b81d2cd757aa8adbf0945734f2f&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg";
                CartVo cartVo = new CartVo();
                cartVo.setId(goodsId);
                cartVo.setName(goodsName);
                cartVo.setRemark(goodsMsg);
                cartVo.setPrice(goodsPrice);
                cartVo.setImage(imageUrl);
                return cartVo;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public CartResult findGoodsNumById(CartResult cartList) {
        if(CollectionUtils.isEmpty(cartList.getCartList())){
            return null;
        }
        for (CartVo cartVo : cartList.getCartList()) {
            ShopInformation shop = shopInformationMapper.selectByPrimaryKey(cartVo.getId());
            if(shop!=null&&shop.getQuantity()!=null&&shop.getQuantity()-cartVo.getGoodsNum()<0){
                cartVo.setContext("库存不足,请联系厂家");
            }else {
                cartVo.setContext("有货，可当日出货");
            }
        }
        return cartList;
    }

    @Override
    public BaseResult updateGoodsInfo(CartResult cartResult) {

        if (CollectionUtils.isEmpty(cartResult.getCartList())){
            return BaseResult.error();
        }

        for (CartVo cart : cartResult.getCartList()) {
            //查询库存
            ShopInformation shop = shopInformationMapper.selectByPrimaryKey(cart.getId());
            if(shop!=null&&shop.getQuantity()!=null&&shop.getQuantity()-cart.getGoodsNum()>0){
                shop.setModified(new Date());
                shop.setQuantity(shop.getQuantity()-cart.getGoodsNum());
                shopInformationMapper.updateByPrimaryKeySelective(shop);
            }
        }
        return BaseResult.success();
    }
}
