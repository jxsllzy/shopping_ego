package com.ego.portal.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PortalCommonInterceptor implements HandlerInterceptor {

    @Value("${ego.order.url}")
    private String egoOrderUrl;

    @Value("${ego.search.url}")
    private String egoSearchUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        ServletContext servletContext = request.getSession().getServletContext();
        String orderUrl = (String) servletContext.getAttribute("orderUrl");
        if (StringUtils.isEmpty(orderUrl)){
            System.out.println("存储orderUrl");
            servletContext.setAttribute("orderUrl",egoOrderUrl);
        }
        String searchUrl = (String) servletContext.getAttribute("searchUrl");
        if (StringUtils.isEmpty(searchUrl)){
            System.out.println("存储searchUrl");
            servletContext.setAttribute("searchUrl",egoSearchUrl);
        }
        return true;
    }
}
