package com.ego.sso.service;

import com.ego.common.pojo.User;
import com.ego.common.pojo.UserInformation;

/**
     * 单点登陆系统
     */
    public interface SSOService {
        /**
         * 验证用户信息，返回token
         * @param user
         * @return
         */
        String login(User user);

        /**
         * 验证token，返回用户信息
         * @param ticket
         * @return
         */
        UserInformation validate(String ticket);
        /**
         * 用户退出
         */

        public void logout(String ticket);

    /**验证用户注册信息
     *
     * @param Nickname
     * @param username
     * @param password
     * @param confirmpassword
     * @return
     */
        Boolean register(String Nickname, String username, String password, String confirmpassword);
          }

