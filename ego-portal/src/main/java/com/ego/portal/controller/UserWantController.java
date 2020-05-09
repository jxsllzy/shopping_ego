package com.ego.portal.controller;

import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.UserWant;
import com.ego.portal.service.UserWantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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


    /**
     * @Description //TODO 跳转到求购商城页面（显示所有的求购商品）
     * @Param [model]
     * @return java.lang.String
     */
    @RequestMapping("list")
    public String list(Model model){
        //查询出所有求购商品，并设置到作用域中

        return "require_mall";
    }

    /**
     * @Description //TODO 通过当前登录用户的id，查询出此用户的所有求购商品
     * @Param [userId]
     * @return java.lang.String
     */
    @RequestMapping("listByUserId")
    public String listByUserId(Integer userId){
        //根据当前登录用户的id，查询出此用户的所有求购商品，并设置到作用域中

        return "wodeqiugou";
    }

    @RequestMapping("add")
    public BaseResult add(UserWant userWant){
        //根据前台提交的求购信息，添加求购商品
        return null;
    }


}
