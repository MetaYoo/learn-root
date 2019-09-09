package com.kotall.learn.io.netty;

import com.kotall.learn.MsgInfoPb;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/9/3 11:18
 * @since 1.0.0
 */
@Component
@ChannelHandler.Sharable
public class ServerChannelHandler extends SimpleChannelInboundHandler<Object> {
    /**
     * 拿到传过来的msg数据，开始处理
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Netty tcp server receive msg : " + msg);
        MsgInfoPb.MsgInfo.Builder builder = MsgInfoPb.MsgInfo.newBuilder();
        builder.setMsgType(2);
        builder.setDbName("server");
        builder.setFileNameTime("20190902");
        builder.setTimeStamp(1200000012);
        ctx.channel().writeAndFlush(builder.build()).syncUninterruptibly();
    }
}
