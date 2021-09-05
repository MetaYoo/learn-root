package com.kotall.learn;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@ComponentScan(basePackages = {"com.kotall.learn"})
@Configuration
public class MyApplication {

    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        return dispatcherServlet;
    }

    public static void main(String[] args) {
        // 1. 启动spring容器
        AnnotationConfigWebApplicationContext configWebApplicationContext = new AnnotationConfigWebApplicationContext();
        configWebApplicationContext.register(MyApplication.class);
        configWebApplicationContext.refresh();
        System.out.println("实例化Spring容器...");

        // 2. 启动tomcat
        // WebServerFactory
        startTomcat(configWebApplicationContext);

    }

    private static void startTomcat(WebApplicationContext webApplicationContext) {
        // tomcat -> server -> engine -> host -> context -> servlet
        //                  -> connector
        Tomcat tomcat = new Tomcat();

        Service service = tomcat.getService();

        Connector connector = new Connector();
        connector.setPort(8080);
        service.addConnector(connector);

        Engine engine = new StandardEngine();
        engine.setDefaultHost("localhost");

        Host host = new StandardHost();
        host.setName("localhost");

        Context context = new StandardContext();
        context.setPath("/demo");
        context.addLifecycleListener(new Tomcat.FixContextListener());
        host.addChild(context);

        engine.addChild(host);
        service.setContainer(engine);



        DispatcherServlet dispatcher = webApplicationContext.getBean(DispatcherServlet.class);
        tomcat.addServlet("/demo", "dispatcher", dispatcher);
        context.addServletMappingDecoded("/*", "dispatcher");


        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
