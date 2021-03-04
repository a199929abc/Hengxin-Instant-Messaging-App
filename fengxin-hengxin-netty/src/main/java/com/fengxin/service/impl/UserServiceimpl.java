package com.fengxin.service.impl;

import com.fengxin.mapper.UsersMapper;
import com.fengxin.pojo.Users;


import com.fengxin.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example.Criteria;

import tk.mybatis.mapper.entity.Example;
import java.awt.desktop.UserSessionEvent;
import java.awt.desktop.UserSessionListener;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UsersMapper userMapper;

    @Autowired
    private Sid sid;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        System.out.println("fdafafafdaffdafdasfsfasffasfasfdasasfdsafadsfasasd");
        Users user =new Users();
        user.setUsername(username);

        Users result=userMapper.selectOne(user);

            return result != null ? true : false;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {

        Example userExample=new Example(Users.class);


        Criteria  criteria = userExample.createCriteria();

        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password",password);


        Users result =userMapper.selectOneByExample(userExample);

        return result;


    }

    @Override
    public Users saveUser(Users user) {
        //给发二维码
        String userId=sid.nextShort();

        user.setQrcode("");
        user.setId(userId);

        userMapper.insert(user);
        return user;
    }
}

