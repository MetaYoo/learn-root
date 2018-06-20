package com.kotall.learn.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {

	public static void main(String[] args) throws Exception {
		
		new NettyServer().start();
	}
	
	void start() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.localAddress("127.0.0.1", 9000)
				.childHandler(new ChannelInitializer() {
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast("decoder", new StringDecoder());
						ch.pipeline().addLast("encoder", new StringEncoder());
						ch.pipeline().addLast(new HelloWorldChannelHandler());
					}
				});
	
		 // 绑定端口，开始接收进来的连接  
        ChannelFuture future = bootstrap.bind(9000).sync();    
          
        System.out.println("Server start listen at " + 9000 );  
        future.channel().closeFuture().sync(); 
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
	}
	
	class HelloWorldChannelHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			System.out.println("Server channelRead...");
			System.out.println(ctx.channel().remoteAddress() + "->Server: " + msg.toString());
			ctx.write("Server write: " + msg);
			ctx.flush();
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			ctx.close();
		}
		
	}
}
