package com.kotall.learn.io.netty;

import com.kotall.learn.MsgInfoPb;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/9/3 11:47
 * @since 1.0.0
 */
@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    NettyTcpServer nettyTcpServer;
    @Autowired
    NettyTcpClient nettyTcpClient;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args).start();
    }

    @Override
    public void run(String... args) throws Exception {
        ChannelFuture start = nettyTcpServer.start();

        //启动客户端，发送数据
        nettyTcpClient.connect();
        /*for (int i = 0; i < 10; i++) {
            nettyTcpClient.sendMsg("hello world" + i);
        }*/

        MsgInfoPb.MsgInfo.Builder builder = MsgInfoPb.MsgInfo.newBuilder();
        builder.setMsgType(1);
        builder.setDbName("client");
        builder.setFileNameTime("20190901");
        builder.setTimeStamp(1200000011);
        nettyTcpClient.sendMsg(builder.build());
        start.channel().closeFuture().syncUninterruptibly();
    }

}
