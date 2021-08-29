package com.fengxin.netty;

import com.fengxin.SpringUtil;
import com.fengxin.enums.MsgActionEnum;
import com.fengxin.service.UserService;
import com.fengxin.utils.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SocketHandler;

public class chatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //用于记录和管理所有客户端的channel
    private static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override

    /**
     * 当客户端链接服务端后获取客户端的channel 并且放到channel group中进行管理
     *
     */
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //移除失效的channnel
        users.remove(ctx.channel());

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生异常后关闭连接关闭channel 随后从 channel group移除

        ctx.channel().close();
        users.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String content=msg.text();//get client message

        Channel currentChannel = ctx.channel();



        //1. 获取客户端消息
        DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);

        Integer action = dataContent.getAction();

        //2. 判断消息类型 通过枚举定义

        if(action == MsgActionEnum.CONNECT.type){
            // 2.1 当 websocket 第一次open的时候  初始化channel把用户channel 和userid 关联起来
           String senderId = dataContent.getChatMsg().getSenderId();
           UserChannelRel.put(senderId,currentChannel);
            for(Channel c: users){
                System.out.println(c.id().asLongText());
            }
            UserChannelRel.output();

        }else if(action == MsgActionEnum.CHAT.type){
            // 2.2 聊天类型消息， 把聊天记录保存数据库，同时标记聊天记录签收状态【未签收】
            ChatMsg chatMsg = dataContent.getChatMsg();
            String msgText = chatMsg.getMsg();
            String receiverId = chatMsg.getReceiverId();
            String senderId = chatMsg.getSenderId();

            //保存消息到数据库 标记未签收
            UserService userService = (UserService) SpringUtil.getBean("UserServiceimpl");
            String msgId = userService.saveMsg(chatMsg);
            System.out.println(msgId);

            chatMsg.setMsgId(msgId);

            //发送消息

             Channel receiverChannel = UserChannelRel.get(receiverId);
            if(receiverChannel == null){
                //TODO channel 为空 表示离线，推送消息(JPush, 个推，)
            }else {
                // 当 receiver Channel 不为空的时候，从channelGroup去查找对应的channel是否存在
                Channel findChannel = users.find(receiverChannel.id());
                if (findChannel != null){
                    //用户在线
                    receiverChannel.writeAndFlush(
                            new TextWebSocketFrame(
                                    JsonUtils.objectToJson(chatMsg)));

                }else{
                    //用户离线

                }
            }



        }else if(action == MsgActionEnum.SIGNED.type){
            //2.3 签收消息类型， 针对具体的消息进行签收，修改数据库中对应消息的签收状态【已签收】
            //接受到了就算签收并不是已读
            UserService userService = (UserService) SpringUtil.getBean("UserServiceimpl");
            //拓展字段在signed类型中的消息中，需要签收的消息id，逗号间隔
            String msgIdsStr = dataContent.getExtand();

            String msgIds[] = msgIdsStr.split(",");

            List<String> msgIdList = new ArrayList<>();

            for(String mid : msgIds) {
                if(StringUtils.isNotBlank(mid)){
                    msgIdList.add(mid);
                }
            }
            System.out.println(msgIdList.toString());

            if(msgIdList != null && msgIdList.isEmpty() && msgIdList.size()>0){
                userService.updateMsgSigned(msgIdList);
            }



        }else if(action == MsgActionEnum.KEEPALIVE.type){
            //2.4 心跳类型的消息
        }


    }
}
