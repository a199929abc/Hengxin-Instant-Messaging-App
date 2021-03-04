package com.fengxin.netty;

import io.netty.channel.ChannelHandler;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class WSServer {

	private static class SingletionWSServer {
		static final WSServer instance = new WSServer();
	}
	
	public static WSServer getInstance() {
		return SingletionWSServer.instance;
	}
	
	private EventLoopGroup mainGroup;
	private EventLoopGroup subGroup;
	private ServerBootstrap server;
	private ChannelFuture future;
	
	public WSServer() {
		mainGroup = new NioEventLoopGroup();
		subGroup = new NioEventLoopGroup();
		server = new ServerBootstrap();
		server.group(mainGroup, subGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new webserverInit());
	}
	
	public void start() {
		this.future = server.bind(8081);
		System.out.println("netty websocket server 启动完毕...");
	}
}
