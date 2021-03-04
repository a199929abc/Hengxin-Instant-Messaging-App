package com.fengxin.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "friends_request")
public class FriendsRequest {
    @Id
    private String id;

    @Column(name = "send_user_id")
    private String sendUserId;

    @Column(name = "accept_user_id")
    private String acceptUserId;

    /**
     * 鍙戦?璇锋眰鐨勪簨浠
     */
    @Column(name = "request_date_time")
    private Date requestDateTime;

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
     * 获取鍙戦?璇锋眰鐨勪簨浠
     *
     * @return request_date_time - 鍙戦?璇锋眰鐨勪簨浠
     */
    public Date getRequestDateTime() {
        return requestDateTime;
    }

    /**
     * 设置鍙戦?璇锋眰鐨勪簨浠
     *
     * @param requestDateTime 鍙戦?璇锋眰鐨勪簨浠
     */
    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }
}