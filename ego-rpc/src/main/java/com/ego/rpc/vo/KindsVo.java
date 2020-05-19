package com.ego.rpc.vo;


import com.ego.rpc.pojo.AllKinds;

import java.io.Serializable;
import java.util.List;

public class KindsVo extends AllKinds implements Serializable {

    private List<ClassificationVo> childrenList;

    public KindsVo() {
    }

    public List<ClassificationVo> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<ClassificationVo> childrenList) {
        this.childrenList = childrenList;
    }
}
