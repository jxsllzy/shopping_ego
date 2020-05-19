package com.ego.portal.service.impl;

import com.ego.portal.mapper.AllKindsMapper;
import com.ego.portal.mapper.ClassificationMapper;
import com.ego.portal.mapper.SpecifickindsMapper;
import com.ego.portal.pojo.*;
import com.ego.portal.service.AllKindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllKindsServiceImpl implements AllKindsService {

    @Autowired
    private AllKindsMapper allKindsMapper;
    @Autowired
    private ClassificationMapper classificationMapper;
    @Autowired
    private SpecifickindsMapper specifickindsMapper;
    /**
     * 查询顶级分类
     * @return
     */
    @Override
    public List<AllKinds> selectTopList() {
        AllKindsExample allKindsExample = new AllKindsExample();

        return allKindsMapper.selectByExample(allKindsExample);
    }

    /**
     * 查询二级分类
     * @return
     */
    @Override
    public List<Classification> selectTwoList(Integer id) {
        ClassificationExample classificationExample = new ClassificationExample();
        classificationExample.createCriteria().andAidEqualTo(id);
        return classificationMapper.selectByExample(classificationExample);

    }

    /**
     * 三级
     * @param id
     * @return
     */
    @Override
    public List<Specifickinds> selectThreeList(Integer id) {
        SpecifickindsExample specifickindsExample = new SpecifickindsExample();
        specifickindsExample.createCriteria().andCidEqualTo(id);
        return specifickindsMapper.selectByExample(specifickindsExample);
    }
}
