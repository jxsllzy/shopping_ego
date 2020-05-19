package com.ego.portal.controller;

import com.ego.common.pojo.User;
import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.AllKinds;

import com.ego.portal.pojo.UserWant;
import com.ego.portal.service.AllKindsService;
import com.ego.portal.service.UserWantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 求购商品Controller
 *
 * @author : WangJinDong
 * @version : 1.0.0
 * @date : 2020-05-09 11:56
 **/

@Controller
@RequestMapping("userwant")
public class UserWantController {

    @Autowired
    private UserWantService userWantService;
    @Autowired
    private AllKindsService allKindsService;


    //一：页面跳转
    /**
     * @Description //TODO 跳转到用户的求购列表页面
     * @Param [model]
     * @return java.lang.String
     */
    @RequestMapping("userlist")
    public String list(Model model){
        //页面跳转
        return "user_require_list";
    }

    /**
     * @Description //TODO 跳转到添加求购商品的页面
     * @Param [model]
     * @return java.lang.String
     */
    @RequestMapping("updateview")
    public String addView(Model model, HttpServletRequest request, Integer id){

        if(null != id){
            request.getSession().setAttribute("Aid",id);
        }

        //查询顶级分类信息
        List<AllKinds> allKinds = allKindsService.selectTopList();
        model.addAttribute("gcList",allKinds);
        //页面跳转
        return "update_user_want";
    }

    //二：查询、更新、删除

    /**
     * @Description //TODO 根据条件查询求购商品
     * @Param [userWant, pageNum, pageSize]
     * @return com.ego.common.result.BaseResult
     */
    @RequestMapping("select")
    @ResponseBody
    public BaseResult selectAll (HttpServletRequest request,
                                 UserWant userWant, Integer pageNum, Integer pageSize){


        UserInformation currentUser = (UserInformation) request.getSession().getAttribute("user");
        userWant.setUid(currentUser.getId());
        return userWantService.select(userWant,pageNum,pageSize);
    }

    /**
     * @Description //TODO 保存求购商品信息（根据判断前台传来的UserWant是否拥有id，决定添加或修改求购商品）
     * @Param [userWant]
     * @return com.ego.common.result.BaseResult
     */
    @RequestMapping("update")
    @ResponseBody
    public BaseResult update(UserWant userWant, HttpServletRequest request){
        //查看作用域中是否有id
        Integer aid = (Integer)request.getSession().getAttribute("Aid");
        //根据前台提交的求购信息，添加求购商品
        if (null != aid){
            userWant.setId(aid);
            request.getSession().setAttribute("Aid",null);
        }

        UserInformation currentUser = (UserInformation) request.getSession().getAttribute("user");
        userWant.setUid(currentUser.getId());
        return userWantService.update(userWant);
    }

    /**
     * @Description //TODO 删除求购商品
     * @Param [id]
     * @return com.ego.common.result.BaseResult
     */
    @RequestMapping("delete/{id}")
    @ResponseBody
    public BaseResult delete(@PathVariable Integer id){
        //根据id删除求购信息
        return userWantService.delete(id);
    }

}
