package com.ego.portal.service;

import com.ego.common.pojo.UserInformation;
import com.ego.common.result.BaseResult;

/**
 * 用户信息
 */
public interface UserService {

    UserInformation selectById(UserInformation userInformation);

    UserInformation selectByUserName(String userName);

    BaseResult updateName(String id, String userName);

    BaseResult updateRealName(String id, String realName);

    BaseResult updateGender(String id, String gender);

    BaseResult updateSno(String id, String sno);

    BaseResult updateDormitory(String id, String dormitory);

    BaseResult updatePhone(String id, String phone);
}
