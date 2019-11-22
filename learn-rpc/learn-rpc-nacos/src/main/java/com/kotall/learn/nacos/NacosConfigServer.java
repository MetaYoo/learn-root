package com.kotall.learn.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/11/22 18:33
 * @since 1.0.0
 */
@NacosPropertySource(dataId = "adx-dsp", groupId = "ADX", autoRefreshed = true)
@SpringBootApplication
public class NacosConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigServer.class, args);
    }
}
