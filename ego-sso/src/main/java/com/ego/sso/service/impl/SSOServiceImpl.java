package com.ego.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ego.common.pojo.User;
import com.ego.common.pojo.UserInformation;
import com.ego.common.pojo.UserInformationExample;
import com.ego.common.result.BaseResult;
import com.ego.common.util.Md5Util;
import com.ego.common.util.UUIDUtil;
import com.ego.sso.mapper.UserInformationMapper;
import com.ego.sso.mapper.UserPasswordMapper;
import com.ego.sso.pojo.*;
import com.ego.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service(interfaceClass = SSOService.class)
@Component
public class SSOServiceImpl implements SSOService {

    @Resource
    private UserInformationMapper userInformationMapper;

    @Resource
    private UserPasswordMapper userPasswordMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${user.ticket}")
    private String userTicket;

    @Override
    public String login(User user) {
        if(StringUtils.isEmpty(user.getUsername())){
            //作为异常抛出
            System.out.println("用户名和密码不能为空");

        }
        if(StringUtils.isEmpty(user.getPassword())){
            //作为异常抛出
            System.out.println("用户名和密码不能为空");

        }

        //构建查询对象
        UserInformationExample userInformationExample = new UserInformationExample();
        userInformationExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<UserInformation> userInformations = userInformationMapper.selectByExample(userInformationExample);
        if(CollectionUtils.isEmpty(userInformations)||userInformations.size()>1){
            //作为异常抛出
            System.out.println("用户名和密码不能为空");
            return null;
        }
        //查出的用户账户对象
        UserInformation userInformationFromDB =  userInformations.get(0);

        UserPasswordExample userPasswordExample = new UserPasswordExample();
        userPasswordExample.createCriteria().andUidEqualTo(userInformationFromDB.getId());
        List<UserPassword> userPasswords = userPasswordMapper.selectByExample(userPasswordExample);
        if(CollectionUtils.isEmpty(userPasswords)||userPasswords.size()>1){
            //作为异常抛出
            System.out.println("用户名和密码不能为空");
            return null;
        }
        //查出的用户密码对象
        UserPassword userPassword1 = userPasswords.get(0);
        if(!userPassword1.getPassword().equals(Md5Util.encode(user.getPassword()))){
            //作为异常抛出
            System.out.println("用户名和密码不能为空");
            return null;
        }
        //生成票据信息
        String ticket = UUIDUtil.getUUID();
        //将用户信息存到redis中
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        stringObjectValueOperations.set(userTicket+":"+ticket,userInformationFromDB);
        return ticket;
    }

    @Override
    public UserInformation validate(String ticket) {
        //判断票据信息是否为空
        if(StringUtils.isEmpty(ticket)){
            System.out.println("票据信息不能为空");

        }
        //根据票据去拿用户信息
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        UserInformation userInformation = (UserInformation)stringObjectValueOperations.get(userTicket + ":" + ticket);
        if(null == userInformation||null == userInformation.getId()){
            return null;
        }
        return userInformation;
    }

    @Override
    public void logout(String ticket) {
        redisTemplate.delete(userTicket+":"+ticket);
    }

    @Override
    public Boolean register(String Nickname, String username, String password, String confirmpassword) {

        if(StringUtils.isEmpty(Nickname)|StringUtils.isEmpty(username)|StringUtils.isEmpty(password)|StringUtils.isEmpty(confirmpassword)){
            return false;
        }
        if(!password.equals(confirmpassword)){
            return false;
        }
        //构建对象对象
        UserInformationExample userInformationExample = new UserInformationExample();
        userInformationExample.createCriteria().andUsernameEqualTo(username);

        List<UserInformation> userInformations = userInformationMapper.selectByExample(userInformationExample);
        //用户名不为空或大于1个
        if(!CollectionUtils.isEmpty(userInformations)||userInformations.size()>1){
            //作为异常抛出
            System.out.println("用户名已存在");

            return false;
        }
        //数据库中用户名不存在，存入数据
        else{
            UserInformation userInformaton =new UserInformation();
            //注册username存入数据库
            userInformaton.setUsername(username);
            //注册失败
            if(userInformationMapper.insertSelective(userInformaton)!=1){
                //作为异常抛出
                System.out.println("插入失败");
                return false;
            }
            //注册成功
            else{
                userInformationExample.clear();
                userInformationExample.createCriteria().andUsernameEqualTo(username);
                List<UserInformation> userInformations1 = userInformationMapper.selectByExample(userInformationExample);
                //获得主键
                Integer key = userInformations1.get(0).getId();

                UserPassword userPassword = new UserPassword();
                userPassword.setPassword(Md5Util.encode(password));
                userPassword.setUid(key);
                //注册userid,password存入数据库
                //注册失败
                if(userPasswordMapper.insertSelective(userPassword)!=1){
                    System.out.println("插入失败");
                    return false;
                }
            }

        }
        return true;
    }
}
