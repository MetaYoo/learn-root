package com.kotall.learn.io.netty;

import com.kotall.learn.MsgInfoPb;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/9/3 11:14
 * @since 1.0.0
 */
@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    ServerChannelHandler serverChannelHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //IdleStateHandler心跳机制,如果超时触发Handle中userEventTrigger()方法
        pipeline.addLast("idleStateHandler",
                new IdleStateHandler(15, 0, 0, TimeUnit.MINUTES));
        //字符串编解码器
        /*pipeline.addLast(
                new StringDecoder(),
                new StringEncoder()
        );*/
        //请求的解码
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        //解码封装成的目标类
        //ChannelInboundHandlerAdapter
        pipeline.addLast(new ProtobufDecoder(MsgInfoPb.MsgInfo.getDefaultInstance()));
        //响应的编码
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        //ChannelOutboundHandlerAdapter
        pipeline.addLast(new ProtobufEncoder());
        //自定义Handler
        pipeline.addLast("serverChannelHandler", serverChannelHandler);
    }
}
