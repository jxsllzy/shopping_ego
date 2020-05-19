package com.ego.search.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class SearchCommonInterceptor implements HandlerInterceptor {

    @Value("${ego.search.url}")
    private String egoSearchUrl;

    @Value("${ego.order.url}")
    private String egoOrderUrl;

    @Value("${ego.portal.url}")
    private String egoPortalUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ServletContext servletContext = request.getSession().getServletContext();
        String searchUrl = (String) servletContext.getAttribute("searchUrl");
        String orderUrl = (String) servletContext.getAttribute("orderUrl");
        String portalUrl = (String) servletContext.getAttribute("portalUrl");
        if (StringUtils.isEmpty(searchUrl)){
            servletContext.setAttribute("searchUrl",egoSearchUrl);
        }
        if (StringUtils.isEmpty(orderUrl)){
            servletContext.setAttribute("orderUrl",egoOrderUrl);
        }
        if (StringUtils.isEmpty(portalUrl)){
            servletContext.setAttribute("portalUrl",egoPortalUrl);
        }
        return true;
    }
}

