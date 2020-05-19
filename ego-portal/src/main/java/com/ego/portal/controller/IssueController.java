package com.ego.portal.controller;


import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.portal.pojo.*;
import com.ego.portal.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 我发布的页面
 */
@Controller
@RequestMapping("issue")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @RequestMapping("index")
    public String index(Model model){
        return "fa_bu";
    }

    /**
     * 查询登陆的发布商品信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public BaseResult queryShopInfoList(HttpServletRequest request, Integer pageNum, Integer pageSize){
        //用户id
        UserInformation user= (UserInformation) request.getSession().getAttribute("user");
        //登录验证
        if(StringUtils.isEmpty(user)){
            BaseResult baseResult = new BaseResult();
            baseResult.setCode(405);
            baseResult.setMessage("你还没有登陆快去登陆把!!");
            return baseResult;
        }
        return issueService.selectPage(user, pageNum, pageSize);
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public BaseResult delete(@PathVariable Integer id){
       return issueService.deleteInfo(id);
    }
}
