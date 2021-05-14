package com.fengxin.service;

import com.fengxin.pojo.Users;
import com.fengxin.pojo.vo.FriendRequestVO;

import java.util.List;

public interface UserService {

    /**
     * @param username
     * @return
     */
    public boolean queryUsernameIsExist(String username);

    public Users queryUserForLogin(String username, String password);

    public Users saveUser(Users user);

    //修改用户记录
    public Users updateUserInfo(Users user);

    //搜索朋友前置条件
    public Integer preconditionSearchFriends(String myUserId, String friendUsername);

    // 根据用户名查询对象
    public Users queryUserInfoByUsername(String username);

    //添加好友保存记录
    public void sendFriendRequest(String myUserId, String friendUsername);

    //
    public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);

}
