package com.ego.portal.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.UserInformation;
import com.ego.common.util.CookieUtil;
import com.ego.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PortalLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Reference(interfaceClass = SSOService.class)
    private SSOService ssoService;

    @Value("${user.ticket}")
    private String userTicket;

    // 请求处理的方法之前执行
    // 如果返回 true ，执行下一个拦截器或者目标程序，如果返回 false ，不执行 下一个拦截器或者目标程序
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从cookie中获取票据
        String ticket = CookieUtil.getCookieValue(request, "userTicket");
        //如果存在票据，根据票据信息从单点登陆系统里获取用户信息
        if (!StringUtils.isEmpty(ticket)) {
            UserInformation userInformationn = ssoService.validate(ticket);
            //如果用户信息不为空
            if (null != userInformationn) {
                //将用户信息放入session中，用户页面显示
                request.getSession().setAttribute("user", userInformationn);
                return true;
            }
        }
        //如果票据信息为空，跳转至登陆页面
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }
}
