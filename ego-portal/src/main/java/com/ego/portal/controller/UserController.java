
package com.ego.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.User;
import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;
import com.ego.portal.service.CookieService;
import com.ego.portal.service.UserService;


import com.ego.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class UserController {

    @Reference(interfaceClass = SSOService.class)
    private SSOService ssoService;
    @Autowired
    private UserService userService;
    @Resource
    private CookieService cookieService;

    @RequestMapping("login")
    @ResponseBody
    public BaseResult login(User user, HttpServletRequest request, HttpServletResponse response){

        //去单点登陆系统验证用户信息,返回票据信息
        String ticket =ssoService.login(user);

        if(StringUtils.isEmpty(ticket)){
            return BaseResult.error();
        }
        //把票据信息放入cookie
        boolean result = cookieService.setCookie(ticket, request, response);

        //查询用户
        UserInformation userInformation = userService.selectByUserName(user.getUsername());

        //将用户信息存入session,用于返显
        request.getSession().setAttribute("user",userInformation);
        return result?BaseResult.success():BaseResult.error();
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        //通过request拿cookie的值，就是用户的ticket
        String ticket = cookieService.getCookie(request);
        //如果ticket不为空
        if(!StringUtils.isEmpty(ticket)){
            //清除redis
            ssoService.logout(ticket);
            //清除session
            request.getSession().removeAttribute("user");
            //清楚cookie
            cookieService.deleteCookie(request,response);
        }
        return "login";
    }
    //Todo这里

    @RequestMapping("register")
    @ResponseBody
    public BaseResult register(String Nickname, String username, String password, String confirmpassword){
        Boolean registered = ssoService.register(Nickname, username, password, confirmpassword);
        return registered?BaseResult.success():BaseResult.error();
    }


    @RequestMapping("info")
    public String info(HttpServletRequest request, Model model){
        //查询作用域的用户信息
        UserInformation userInformation= (UserInformation) request.getSession().getAttribute("user");
        UserInformation user = userService.selectById(userInformation);
        model.addAttribute("user",user);
        return "user_info";
    }

    /**
     * 修改方法
     */
    @RequestMapping("userName")
    @ResponseBody
    public BaseResult updateName(HttpServletRequest request){
        String id = request.getParameter("id");
        String userName = request.getParameter("userName");
        return userService.updateName(id,userName);
    }

    /**
     * 修改真实姓名
     * @param request
     * @return
     */
    @RequestMapping("realName")
    @ResponseBody
    public BaseResult updaterealName(HttpServletRequest request){
        String id = request.getParameter("id");
        String realName = request.getParameter("realName");
        return userService.updateRealName(id,realName);
    }
    /**
     * 修改真实姓名
     * @param request
     * @return
     */
    @RequestMapping("gender")
    @ResponseBody
    public BaseResult updategender(HttpServletRequest request){
        String id = request.getParameter("id");
        String gender = request.getParameter("gender");
        return userService.updateGender(id,gender);
    }

    /**
     * 修改学号
     * @param request
     * @return
     */
    @RequestMapping("sno")
    @ResponseBody
    public BaseResult updatesno(HttpServletRequest request){
        String id = request.getParameter("id");
        String sno = request.getParameter("sno");
        return userService.updateSno(id,sno);
    }

    /**
     * 修改宿舍号
     * @param request
     * @return
     */
    @RequestMapping("dormitory")
    @ResponseBody
    public BaseResult updatedormitory(HttpServletRequest request){
        String id = request.getParameter("id");
        String dormitory = request.getParameter("dormitory");
        return userService.updateDormitory(id,dormitory);
    }

    /**
     * 修改电话号码
     * @param request
     * @return
     */
    @RequestMapping("phone")
    @ResponseBody
    public BaseResult updatephone(HttpServletRequest request){
        String id = request.getParameter("id");
        String phone = request.getParameter("phone");
        return userService.updatePhone(id,phone);
    }

}
