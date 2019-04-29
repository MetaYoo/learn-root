package com.kotall.learn.shiro.ssl;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import java.io.File;

/**
 * Hello world!
 */
@SpringBootApplication
public class HttpsOauthApp {

    @Value("${https.port:443}")
    private Integer httpsPort;

    @Value("${https.ssl.keyStoreType:PKCS12}")
    private String keyStoreType;

    @Value("${https.ssl.key-store:/opt/dsp/dsp-feedback/keystore.p12}")
    private String keyStore;

    @Value("${https.ssl.key-store-password:123456}")
    private String keyStorePassword;

    @Value("${https.ssl.key-password:123456}")
    private String keyPassword;

    @Value("${https.ssl.keyAlias:tomcat}")
    private String keyAlias;

    public static void main(String[] args) {
        SpringApplication.run(HttpsOauthApp.class, args).start();
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        // 添加 https
        tomcat.addAdditionalTomcatConnectors(createSslConnector());
        return tomcat;
    }

    /**
     * 配置 https
     *
     * @return
     */
    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            File keystore = new File(keyStore);
            connector.setScheme("https");
            connector.setSecure(true);
            connector.setPort(httpsPort);
            protocol.setSSLEnabled(true);
            protocol.setKeystoreFile(keystore.getAbsolutePath());
            protocol.setKeystorePass(keyStorePassword);
            protocol.setKeystoreType(keyStoreType);
            protocol.setKeyAlias(keyAlias);
            return connector;
        } catch (Exception ex) {
            throw new IllegalStateException("can't access keystore: [" + "keystore"
                    + "]", ex);
        }
    }

}
