package com.imooc.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "chat_msg")
public class ChatMsg {
    @Id
    private String id;

    @Column(name = "send_user_id")
    private String sendUserId;

    @Column(name = "accept_user_id")
    private String acceptUserId;

    private String msg;

    /**
     * 娑堟伅鏄?惁绛炬敹鐘舵?\r
1锛氱?鏀禱r
0锛氭湭绛炬敹

     */
    @Column(name = "sign_flag")
    private Integer signFlag;

    /**
     * 鍙戦?璇锋眰鐨勪簨浠
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * @return send_user_id
     */
    public String getSendUserId() {
        return sendUserId;
    }

    /**
     * @param sendUserId
     */
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    /**
     * @return accept_user_id
     */
    public String getAcceptUserId() {
        return acceptUserId;
    }

    /**
     * @param acceptUserId
     */
    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    /**
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取娑堟伅鏄?惁绛炬敹鐘舵?\r
1锛氱?鏀禱r
0锛氭湭绛炬敹

     *
     * @return sign_flag - 娑堟伅鏄?惁绛炬敹鐘舵?\r
1锛氱?鏀禱r
0锛氭湭绛炬敹

     */
    public Integer getSignFlag() {
        return signFlag;
    }

    /**
     * 设置娑堟伅鏄?惁绛炬敹鐘舵?\r
1锛氱?鏀禱r
0锛氭湭绛炬敹

     *
     * @param signFlag 娑堟伅鏄?惁绛炬敹鐘舵?\r
1锛氱?鏀禱r
0锛氭湭绛炬敹

     */
    public void setSignFlag(Integer signFlag) {
        this.signFlag = signFlag;
    }

    /**
     * 获取鍙戦?璇锋眰鐨勪簨浠
     *
     * @return create_time - 鍙戦?璇锋眰鐨勪簨浠
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置鍙戦?璇锋眰鐨勪簨浠
     *
     * @param createTime 鍙戦?璇锋眰鐨勪簨浠
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}