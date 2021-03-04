package com.fengxin.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class Users {
    @Id
    private String id;

    /**
     * 鐢ㄦ埛鍚嶏紝璐﹀彿锛屾厱淇″彿
     */
    private String username;

    /**
     * 瀵嗙爜
     */
    private String password;

    /**
     * 鎴戠殑澶村儚锛屽?鏋滄病鏈夐粯璁ょ粰涓?紶
     */
    @Column(name = "face_image")
    private String faceImage;

    @Column(name = "face_image_big")
    private String faceImageBig;

    /**
     * 鏄电О
     */
    private String nickname;

    /**
     * 鏂扮敤鎴锋敞鍐屽悗榛樿?鍚庡彴鐢熸垚浜岀淮鐮侊紝骞朵笖涓婁紶鍒癴astdfs
     */
    private String qrcode;

    private String cid;

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
     * 获取鐢ㄦ埛鍚嶏紝璐﹀彿锛屾厱淇″彿
     *
     * @return username - 鐢ㄦ埛鍚嶏紝璐﹀彿锛屾厱淇″彿
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置鐢ㄦ埛鍚嶏紝璐﹀彿锛屾厱淇″彿
     *
     * @param username 鐢ㄦ埛鍚嶏紝璐﹀彿锛屾厱淇″彿
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取瀵嗙爜
     *
     * @return password - 瀵嗙爜
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置瀵嗙爜
     *
     * @param password 瀵嗙爜
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取鎴戠殑澶村儚锛屽?鏋滄病鏈夐粯璁ょ粰涓?紶
     *
     * @return face_image - 鎴戠殑澶村儚锛屽?鏋滄病鏈夐粯璁ょ粰涓?紶
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * 设置鎴戠殑澶村儚锛屽?鏋滄病鏈夐粯璁ょ粰涓?紶
     *
     * @param faceImage 鎴戠殑澶村儚锛屽?鏋滄病鏈夐粯璁ょ粰涓?紶
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * @return face_image_big
     */
    public String getFaceImageBig() {
        return faceImageBig;
    }

    /**
     * @param faceImageBig
     */
    public void setFaceImageBig(String faceImageBig) {
        this.faceImageBig = faceImageBig;
    }

    /**
     * 获取鏄电О
     *
     * @return nickname - 鏄电О
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置鏄电О
     *
     * @param nickname 鏄电О
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取鏂扮敤鎴锋敞鍐屽悗榛樿?鍚庡彴鐢熸垚浜岀淮鐮侊紝骞朵笖涓婁紶鍒癴astdfs
     *
     * @return qrcode - 鏂扮敤鎴锋敞鍐屽悗榛樿?鍚庡彴鐢熸垚浜岀淮鐮侊紝骞朵笖涓婁紶鍒癴astdfs
     */
    public String getQrcode() {
        return qrcode;
    }

    /**
     * 设置鏂扮敤鎴锋敞鍐屽悗榛樿?鍚庡彴鐢熸垚浜岀淮鐮侊紝骞朵笖涓婁紶鍒癴astdfs
     *
     * @param qrcode 鏂扮敤鎴锋敞鍐屽悗榛樿?鍚庡彴鐢熸垚浜岀淮鐮侊紝骞朵笖涓婁紶鍒癴astdfs
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * @return cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }
}