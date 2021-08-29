package com.fengxin.service;

import com.fengxin.netty.ChatMsg;
import com.fengxin.pojo.Users;
import com.fengxin.pojo.vo.FriendRequestVO;
import com.fengxin.pojo.vo.MyFriendsVO;

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

    public void deleteFriendRequest(String sendUserId, String acceptUserId);

    public void passFriendRequest(String sendUserId, String acceptUserId);

    public List<MyFriendsVO> queryMyFriends(String userId);

    //传入netty 包下的

    /**
     * 保存聊天消息到数据库
     * @param chatMsg
     * @return
     */
    public String saveMsg(ChatMsg chatMsg);

    /**
     * 批量签收
     * @param msgIdList
     */
    public void updateMsgSigned(List<String> msgIdList);
}
