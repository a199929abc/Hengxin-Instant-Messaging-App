package com.fengxin.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.logging.SocketHandler;

public class chatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //用于记录和管理所有客户端的channel
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override

    /**
     * 当客户端链接服务端后获取客户端的channel 并且放到channel group中进行管理
     *
     */
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        clients.add(ctx.channel());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //移除失效的channnel
        //clients.remove(ctx.channel());
        System.out.println("Channel closed. Channel Long ID: "+ctx.channel().id().asLongText());
        System.out.println("Channel closed. Channel short ID: "+ctx.channel().id().asShortText());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String content=msg.text();//get client message
        System.out.println( "receive data : "+content);

  /**  for (Channel channel : clients) {
      channel.writeAndFlush(
          new TextWebSocketFrame(
              "Server receive data : "
                  + LocalDateTime.now()
                  + " information is : "
                  + content)); // can't be String
  }**/
    clients.writeAndFlush(new TextWebSocketFrame(  " Server receive data : "
            + LocalDateTime.now()
            + " information is :  "
            + content));

    }
}
