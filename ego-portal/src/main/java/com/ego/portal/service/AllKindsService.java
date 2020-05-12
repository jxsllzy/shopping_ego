package com.ego.portal.service;

import com.ego.portal.pojo.AllKinds;
import com.ego.portal.pojo.Classification;
import com.ego.portal.pojo.Specifickinds;

import java.util.List;

/**
 * 级联查询
 */
public interface AllKindsService {

    /**
     * 查询顶级分类
     */
    List<AllKinds> selectTopList();
    /**
     * 查询二级分类
     */
    List<Classification> selectTwoList(Integer id);
    /**
     * 查询三级分类
     */
    List<Specifickinds> selectThreeList(Integer id);
}
