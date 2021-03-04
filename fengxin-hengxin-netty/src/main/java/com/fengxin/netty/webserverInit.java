package com.fengxin.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class webserverInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new HttpServerCodec());

        pipeline.addLast(new ChunkedWriteHandler());//大数据切片

        pipeline.addLast(new HttpObjectAggregator(1024*64));

        //===========================all for HTTP =========================
        //handle handshake process including close,ping, pong,
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        pipeline.addLast(new chatHandler());

    //142.104.17.117(Preferred)

    }
}
