package com.fengxin.controller;

import com.fengxin.pojo.Users;
import com.fengxin.pojo.vo.UsersVO;
import com.fengxin.service.UserService;
import com.fengxin.utils.IMoocJSONResult;
import com.fengxin.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fengxin.service.impl.UserServiceimpl;

@RestController
@RequestMapping("u")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registOrLogin")
    public IMoocJSONResult registOrLogin(@RequestBody Users user) throws Exception{

        if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())){
            System.out.println("Your password is null and username too ");
            return  IMoocJSONResult.errorMap("username and password can't be null...");

        }

        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());


        //1.1 login
        Users userResult =null;
        if(usernameIsExist){
        userService.queryUserForLogin(user.getUsername(),MD5Utils.getMD5Str(user.getPassword()));
        if(userResult== null){
            return  IMoocJSONResult.errorMap("username or password are not correct");
        }
        }else{
            //1.2 register
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            userResult= userService.saveUser(user);
        }
        UsersVO userVO =new UsersVO();
        BeanUtils.copyProperties(userResult,userVO);


        return IMoocJSONResult.ok(userResult);
    }
}
