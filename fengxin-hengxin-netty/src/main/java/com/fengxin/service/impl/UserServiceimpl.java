package com.fengxin.service.impl;

import com.fengxin.enums.SearchFriendsStatusEnum;
import com.fengxin.mapper.FriendsRequestMapper;
import com.fengxin.mapper.MyFriendsMapper;
import com.fengxin.mapper.UsersMapper;
import com.fengxin.mapper.UsersMapperCustom;
import com.fengxin.pojo.FriendsRequest;
import com.fengxin.pojo.MyFriends;
import com.fengxin.pojo.Users;
import com.fengxin.pojo.vo.FriendRequestVO;
import com.fengxin.service.UserService;
import com.fengxin.utils.FastDFSClient;
import com.fengxin.utils.FileUtils;
import com.fengxin.utils.QRCodeUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import java.awt.desktop.UserSessionEvent;
import java.awt.desktop.UserSessionListener;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private MyFriendsMapper myFriendsMapper;

    @Autowired
    private FriendsRequestMapper friendsRequestMapper;

    @Autowired
    private UsersMapper userMapper;

    @Autowired
    private UsersMapperCustom usersMapperCustom;

    @Autowired
    private Sid sid;

    @Autowired
    private QRCodeUtils qrCodeUtils;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {

        Users user =new Users();
        user.setUsername(username);

        Users result=userMapper.selectOne(user);

            return result != null ? true : false;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {

        Example userExample = new Example(Users.class);
        Criteria criteria = userExample.createCriteria();

        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", password);

        Users result  =  userMapper.selectOneByExample(userExample);

        return result;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users saveUser(Users user) {

        //给发二维码
        String userId=sid.nextShort();

        // hengxin_qrcode: [username]
        String qrCodePath = "C:"+userId+"qrcode.png";

        qrCodeUtils.createQRCode(qrCodePath,"hengxin_qrcode:"+user.getUsername());

        MultipartFile qrCodeFile = FileUtils.fileToMultipart(qrCodePath);

        String qrCodeUrl = "";
        try{
            qrCodeUrl=fastDFSClient.uploadQRCode(qrCodeFile);
        }catch(IOException e){
            e.printStackTrace();
        }

        user.setQrcode(qrCodeUrl);
        user.setId(userId);

        userMapper.insert(user);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserInfo(Users user) {
      userMapper.updateByPrimaryKeySelective(user);
      return queryUserById(user.getId());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    Users queryUserById(String userId){
        return userMapper.selectByPrimaryKey(userId);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer preconditionSearchFriends(String myUserId, String friendUsername) {

        // 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
        Users user = queryUserInfoByUsername(friendUsername);
        if(user==null){
            return SearchFriendsStatusEnum.USER_NOT_EXIST.status;
        }

        // 2. 搜索账号是你自己，返回[不能添加自己]
        if(user.getId().equals(myUserId)){
            return SearchFriendsStatusEnum.NOT_YOURSELF.status;
        }

        // 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        Example mfe = new Example(MyFriends.class);
        Criteria mfc = mfe.createCriteria();
        mfc.andEqualTo("myUserId",myUserId);
        mfc.andEqualTo("myFriendUserId",user.getId());

        MyFriends myFriendsRel=myFriendsMapper.selectOneByExample(mfe);

        if(myFriendsRel!=null){
            return SearchFriendsStatusEnum.ALREADY_FRIENDS.status;
        }
        return SearchFriendsStatusEnum.SUCCESS.status;


    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserInfoByUsername(String username){
        Example ue = new Example(Users.class);
        Criteria uc = ue.createCriteria();
        uc.andEqualTo("username",username);
        return userMapper.selectOneByExample(ue);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void sendFriendRequest(String myUserId, String friendUsername) {

        //查询朋友信息
        Users friend = queryUserInfoByUsername(friendUsername);

        //1. 查询发送好友请求记录表
        Example fre = new Example(FriendsRequest.class);
        Criteria frc = fre.createCriteria();
        frc.andEqualTo("sendUserId",myUserId);
        frc.andEqualTo("acceptUserId",friend.getId());

      FriendsRequest friendRequest = friendsRequestMapper.selectOneByExample(fre);

      if(friendRequest == null){
          // 2. 如果不是你的好友并且没有记录添加，则发送
          String requestId = sid.nextShort();

          FriendsRequest request = new FriendsRequest();

          request.setId(requestId);

          request.setSendUserId(myUserId);

          request.setAcceptUserId(friend.getId());

          request.setRequestDateTime(new Date());

          friendsRequestMapper.insert(request);
      }

    }

    /**
     * 查询好友请求
     *
     * @param acceptUserId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<FriendRequestVO> queryFriendRequestList(String acceptUserId) {
        return usersMapperCustom.queryFriendRequestList(acceptUserId);
    }


}

