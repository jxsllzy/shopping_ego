package com.ego.portal.controller;


import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.*;
import com.ego.portal.service.AllKindsService;
import com.ego.portal.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    public String index(Model model, HttpServletRequest request){
        String id = request.getParameter("id");
        System.out.println(id);
        if(!StringUtils.isEmpty(id)){
            ShopInformation shops = shopService.selectById(id);
            model.addAttribute("Aid",id);
            model.addAttribute("shops",shops);
        }
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
    public BaseResult save(ShopInformation shopInformation,HttpServletRequest request){
        //获取登陆者id
        String aid = request.getParameter("AId");
        //获取登录用户
        UserInformation user= (UserInformation) request.getSession().getAttribute("user");
        return  shopService.save(shopInformation,aid,user);
    }
}
