package com.fengxin.controller;
import com.fengxin.enums.SearchFriendsStatusEnum;
import com.fengxin.pojo.Users;
import com.fengxin.pojo.bo.UsersBO;
import com.fengxin.pojo.vo.UsersVO;
import com.fengxin.service.UserService;
import com.fengxin.utils.FastDFSClient;
import com.fengxin.utils.FileUtils;
import com.fengxin.utils.IMoocJSONResult;
import com.fengxin.utils.MD5Utils;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fengxin.service.impl.UserServiceimpl;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("u")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FastDFSClient fastDFSClient;

    @PostMapping("/registOrLogin")
    public IMoocJSONResult registOrLogin(@RequestBody Users user) throws Exception{
        System.out.println("works");
        if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())){
            System.out.println("Your password is null and username too ");
            return  IMoocJSONResult.errorMap("username or password can't be null...");
        }

        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());

        //1.1 login
        Users userResult = null;
        if(usernameIsExist){
            System.out.println("User exist in the database" );
        userResult = userService.queryUserForLogin(user.getUsername(),MD5Utils.getMD5Str(user.getPassword()));
        if(userResult == null){
            return  IMoocJSONResult.errorMap("username or password are not correct");
        }
        }else{
            //1.2 register
            System.out.println("Register for new user" );
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
    @PostMapping("/setnickname")
    public IMoocJSONResult setNickname(@RequestBody UsersBO userBO) throws Exception{

        Users user = new Users();
        user.setId(userBO.getUserId());
        user.setNickname(userBO.getNickName());
        System.out.println("New nick name : "+ userBO.getNickName());
        Users result = userService.updateUserInfo(user);

        return IMoocJSONResult.ok(result);

    }

    /**
     *
     * @paramt
     * @return
     * @throws Exception
     */
    @PostMapping("/search")
    public IMoocJSONResult searchUser(String myUserId, String friendUsername) throws Exception{
        //0 双方名字不能为空,根据账户匹配查询
        if(StringUtils.isBlank(myUserId)||StringUtils.isBlank(friendUsername)){
                return IMoocJSONResult.errorMsg("");
        }
        System.out.println(myUserId+"..."+friendUsername);
        // 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
        // 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
        // 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        Integer status = userService.preconditionSearchFriends(myUserId,friendUsername);
        if(status == 0){
            System.out.println("yes");
        }
        if(status == SearchFriendsStatusEnum.SUCCESS.status){

            Users user = userService.queryUserInfoByUsername(friendUsername);

            UsersVO userVO =new UsersVO();
            BeanUtils.copyProperties(user,userVO);
            return IMoocJSONResult.ok(userVO);

        }else{
            String errorMsg =SearchFriendsStatusEnum.getMsgByKey(status);
            return IMoocJSONResult.errorMsg(errorMsg);
        }
    }

    @PostMapping("/addFriendRequest")
    public IMoocJSONResult addFriendRequest(String myUserId, String friendUsername) throws Exception{
        //0 双方名字不能为空,根据账户匹配查询
        if(StringUtils.isBlank(myUserId)||StringUtils.isBlank(friendUsername)){
            return IMoocJSONResult.errorMsg("");
        }
        System.out.println(myUserId+"..."+friendUsername);
        // 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
        // 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
        // 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        Integer status = userService.preconditionSearchFriends(myUserId,friendUsername);
        if(status == 0){
            System.out.println("yes");
        }
        if(status == SearchFriendsStatusEnum.SUCCESS.status){

            userService.sendFriendRequest(myUserId, friendUsername);

        }else{
            String errorMsg =SearchFriendsStatusEnum.getMsgByKey(status);
            return IMoocJSONResult.errorMsg(errorMsg);
        }

        return IMoocJSONResult.ok();

    }
    @PostMapping("/queryFriendRequests")
    public IMoocJSONResult queryFriendRequests(String userId){

        //0 双方名字不能为空,根据账户匹配查询
        if(StringUtils.isBlank(userId)){
            return IMoocJSONResult.errorMsg("");
        }
        //查询用户收到的申请
        return IMoocJSONResult.ok(userService.queryFriendRequestList(userId));

    }

}
