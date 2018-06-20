package com.kotall.learn.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {
	
	public static void main(String[] args) throws Exception {
        new NettyClient().start();
		
	}
	
	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group)
		.channel(NioSocketChannel.class)
		.option(ChannelOption.TCP_NODELAY, true)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast("decoder", new StringDecoder());  
				ch.pipeline().addLast("encoder", new StringEncoder());
				ch.pipeline().addLast(new HelloWorldClientHandler());
                
			}
			
		});
		
		ChannelFuture future = bootstrap.connect("127.0.0.0", 9000).sync();
        future.channel().writeAndFlush("Hello Netty Server ,I am a common client");  
        future.channel().closeFuture().sync();
        
        group.shutdownGracefully();
	}

	class HelloWorldClientHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelActive(ChannelHandlerContext ctx) {
			System.out.println("HelloWorldClientHandler Active");
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) {
			System.out.println("HelloWorldClientHandler read Message:" + msg);
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
			cause.printStackTrace();
			ctx.close();
		}

	}

}
