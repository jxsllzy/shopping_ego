package com.ego.portal.service.impl;

import com.ego.common.pojo.UserInformation;
import com.ego.common.pojo.UserInformationExample;
import com.ego.common.result.BaseResult;

import com.ego.portal.mapper.UserInformationMapper;
import com.ego.portal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInformationMapper userInformationMapper;


    /**
     * 获取用户信息回显
     * @param userInformation
     * @return
     */
    @Override
    public UserInformation selectById(UserInformation userInformation) {
        Integer id = userInformation.getId();
        return  userInformationMapper.selectByPrimaryKey(id);

    }

    /**
     * @Description //TODO 根据用户名查询用户对象，用于第一次登录反显
     * @Param [userName]
     * @return com.ego.common.pojo.UserInformation
     * @author wangjindong
     */
    @Override
    public UserInformation selectByUserName(String userName){

        UserInformationExample userInformationExample = new UserInformationExample();
        UserInformationExample.Criteria criteria = userInformationExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<UserInformation> list = userInformationMapper.selectByExample(userInformationExample);

        if(null != list){
            return list.get(0);
        }
        return null;
    }

    /**
     * 修改用户名
     * @param id
     * @param userName
     * @return
     */
    @Override
    public BaseResult updateName(String id, String userName) {
        UserInformationExample userInformationExample = new UserInformationExample();
        userInformationExample.createCriteria().andIdEqualTo(Integer.parseInt(id));
        UserInformation userInformation = new UserInformation();
        userInformation.setUsername(userName);
        userInformation.setModified(new Date());
        int result = userInformationMapper.updateByExampleSelective(userInformation, userInformationExample);
        return result>0?BaseResult.success():BaseResult.error();
    }

    /**
     * 修改真实用户名
     * @param id
     * @param realName
     * @return
     */
    @Override
    public BaseResult updateRealName(String id, String realName) {
        UserInformationExample userInformationExample = new UserInformationExample();
        userInformationExample.createCriteria().andIdEqualTo(Integer.parseInt(id));
        UserInformation userInformation = new UserInformation();
        userInformation.setRealname(realName);
        userInformation.setModified(new Date());
        int result = userInformationMapper.updateByExampleSelective(userInformation, userInformationExample);
        return result>0?BaseResult.success():BaseResult.error();
    }

    /**
     * 修改性别
     * @param id
     * @param gender
     * @return
     */
    @Override
    public BaseResult updateGender(String id, String gender) {
        //判断前台传过来性别转化成字符串存入数据库
        int i = Integer.parseInt(gender);
        String sex=null;
        if(i==1){
            sex="男";
        }
        if(i==2){
            sex="女";
        }
        UserInformationExample userInformationExample = new UserInformationExample();
        userInformationExample.createCriteria().andIdEqualTo(Integer.parseInt(id));
        UserInformation userInformation = new UserInformation();
        userInformation.setGender(sex);
        userInformation.setModified(new Date());
        int result = userInformationMapper.updateByExampleSelective(userInformation, userInformationExample);
        return result>0?BaseResult.success():BaseResult.error();
    }

    /**
     * 修改学号
     * @param id
     * @param sno
     * @return
     */
    @Override
    public BaseResult updateSno(String id, String sno) {
        UserInformationExample userInformationExample = new UserInformationExample();
        userInformationExample.createCriteria().andIdEqualTo(Integer.parseInt(id));
        UserInformation userInformation = new UserInformation();
        userInformation.setSno(sno);
        userInformation.setModified(new Date());
        int result = userInformationMapper.updateByExampleSelective(userInformation, userInformationExample);
        return result>0?BaseResult.success():BaseResult.error();
    }

    /**
     * 修改宿舍号
     * @param id
     * @param dormitory
     * @return
     */
    @Override
    public BaseResult updateDormitory(String id, String dormitory) {
        UserInformationExample userInformationExample = new UserInformationExample();
        userInformationExample.createCriteria().andIdEqualTo(Integer.parseInt(id));
        UserInformation userInformation = new UserInformation();
        userInformation.setDormitory(dormitory);
        userInformation.setModified(new Date());
        int result = userInformationMapper.updateByExampleSelective(userInformation, userInformationExample);
        return result>0?BaseResult.success():BaseResult.error();
    }

    @Override
    public BaseResult updatePhone(String id, String phone) {
        UserInformationExample userInformationExample = new UserInformationExample();
        userInformationExample.createCriteria().andIdEqualTo(Integer.parseInt(id));
        UserInformation userInformation = new UserInformation();
        userInformation.setPhone(phone);
        userInformation.setModified(new Date());
        int result = userInformationMapper.updateByExampleSelective(userInformation, userInformationExample);
        return result>0?BaseResult.success():BaseResult.error();
    }


}