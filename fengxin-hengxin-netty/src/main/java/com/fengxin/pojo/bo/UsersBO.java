package com.fengxin.pojo.bo;

public class UsersBO {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFaceData() {
        return faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData;
    }

    private String userId;

    private String faceData;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private String nickName;




}