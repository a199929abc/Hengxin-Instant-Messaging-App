package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "my_friends")
public class MyFriends {
    @Id
    private String id;

    /**
     * 鐢ㄦ埛id
     */
    @Column(name = "my_user_id")
    private String myUserId;

    /**
     * 鐢ㄦ埛鐨勫ソ鍙媔d
     */
    @Column(name = "my_friend_user_id")
    private String myFriendUserId;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取鐢ㄦ埛id
     *
     * @return my_user_id - 鐢ㄦ埛id
     */
    public String getMyUserId() {
        return myUserId;
    }

    /**
     * 设置鐢ㄦ埛id
     *
     * @param myUserId 鐢ㄦ埛id
     */
    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    /**
     * 获取鐢ㄦ埛鐨勫ソ鍙媔d
     *
     * @return my_friend_user_id - 鐢ㄦ埛鐨勫ソ鍙媔d
     */
    public String getMyFriendUserId() {
        return myFriendUserId;
    }

    /**
     * 设置鐢ㄦ埛鐨勫ソ鍙媔d
     *
     * @param myFriendUserId 鐢ㄦ埛鐨勫ソ鍙媔d
     */
    public void setMyFriendUserId(String myFriendUserId) {
        this.myFriendUserId = myFriendUserId;
    }
}