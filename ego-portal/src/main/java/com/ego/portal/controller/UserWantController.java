package com.ego.portal.controller;

import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.UserWant;
import com.ego.portal.service.UserWantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 求购商品Controller
 *
 * @author : WangJinDong
 * @version : 1.0.0
 * @date : 2020-05-09 11:56
 **/

@Controller
@RequestMapping("userWant")
public class UserWantController {

    @Autowired
    private UserWantService userWantService;

    //一：页面跳转
    /**
     * @Description //TODO 跳转到求购商城页面
     * @Param [model]
     * @return java.lang.String
     */
    @RequestMapping("list")
    public String list(Model model){
        //查询出所有求购商品，并设置到作用域中
        return "list";
    }

    /**
     * @Description //TODO 跳转到添加求购商品的页面
     * @Param [model]
     * @return java.lang.String
     */
    @RequestMapping("addView")
    public String addView(Model model){
        //页面跳转

        return "add";
    }

    //二：查询、更新、删除
    /**
     * @Description //TODO 根据条件查询求购商品
     * @Param [userWant, pageNum, pageSize]
     * @return com.ego.common.result.BaseResult
     */
    @RequestMapping("allList")
    @ResponseBody
    public BaseResult selectAll (UserWant userWant, Integer pageNum, Integer pageSize){
        return userWantService.select(userWant,pageNum,pageSize);
    }

    /**
     * @Description //TODO 保存求购商品信息（根据判断前台传来的UserWant是否拥有id，决定添加或修改求购商品）
     * @Param [userWant]
     * @return com.ego.common.result.BaseResult
     */
    @RequestMapping("update")
    @ResponseBody
    public BaseResult update(UserWant userWant){
        //根据前台提交的求购信息，添加求购商品
        return userWantService.update(userWant);
    }

    /**
     * @Description //TODO 删除求购商品
     * @Param [id]
     * @return com.ego.common.result.BaseResult
     */
    @RequestMapping("delete")
    @ResponseBody
    public BaseResult delete(Integer id){
        //根据id删除求购信息
        return userWantService.delete(id);
    }

}
