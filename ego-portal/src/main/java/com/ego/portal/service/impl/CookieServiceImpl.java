package com.ego.portal.service.impl;

import com.ego.common.util.CookieUtil;
import com.ego.portal.service.CookieService;
import com.ego.portal.service.CookieService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieServiceImpl implements CookieService {
    /**
     * 将ticket设置到cookie中
     * @param ticket
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean setCookie(String ticket, HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return true;
    }

    @Override
    public boolean deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtil.deleteCookie(request,response,"userTicket");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getCookie(HttpServletRequest request) {
        String userTicket = CookieUtil.getCookieValue(request, "userTicket");
        return userTicket;
    }
}
