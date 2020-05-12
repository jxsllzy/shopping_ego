package com.ego.portal.controller;


import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.AllKinds;
import com.ego.portal.pojo.Classification;
import com.ego.portal.pojo.ShopInformation;
import com.ego.portal.pojo.Specifickinds;
import com.ego.portal.service.AllKindsService;
import com.ego.portal.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("release")
public class ReleaseController {
    @Autowired
    private AllKindsService allKindsService;
    @Autowired
    private ShopService shopService;
    /**
     * 跳入发布主页面
     * @return
     */
    @RequestMapping("RIndex")
    public String index(Model model){
        //查询顶级分类信息
        List<AllKinds> allKinds = allKindsService.selectTopList();
        model.addAttribute("gcList",allKinds);
        return "RIndex";
    }

    /**
     * 查询二级分类
     */
    @RequestMapping("/{id}")
    @ResponseBody
    public List<Classification> selectTwoList(@PathVariable Integer id){
       return allKindsService.selectTwoList(id);
    }
    /**
     * 查询三级分类
     */
    @RequestMapping("/three/{id}")
    @ResponseBody
    public List<Specifickinds> selectThreeList(@PathVariable Integer id){
        return allKindsService.selectThreeList(id);
    }

    /**
     * 保存商品
     */
    @RequestMapping("save")
    @ResponseBody
    public BaseResult save(ShopInformation shopInformation){
        return  shopService.save(shopInformation);
    }

}
