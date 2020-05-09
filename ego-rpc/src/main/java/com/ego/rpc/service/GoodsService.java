package com.ego.rpc.service;

import com.ego.common.result.EgoPageInfo;
import com.ego.rpc.vo.GoodsVo;

public interface GoodsService {

    /**
     * 搜索
     */
    EgoPageInfo<GoodsVo> doSearch(String search,Integer pageNum,Integer pageSize);

}
