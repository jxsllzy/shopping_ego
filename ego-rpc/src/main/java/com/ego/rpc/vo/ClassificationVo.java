package com.ego.rpc.vo;


import com.ego.rpc.pojo.Classification;
import com.ego.rpc.pojo.SpecificKinds;

import java.io.Serializable;
import java.util.List;

/**
 * @author cl 
 * @create 2020-05-09
 * @since 1.0.0
 */
public class ClassificationVo extends Classification implements Serializable {
    private List<SpecificKinds> childrenList;

    public ClassificationVo() {
    }

    public List<SpecificKinds> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<SpecificKinds> childrenList) {
        this.childrenList = childrenList;
    }
}