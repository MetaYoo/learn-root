package com.kotall.learn.tomcat;

import org.apache.catalina.startup.Tomcat;

public class TomcatApp {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setHostname("localhost");
        tomcat.setPort(8080);
        tomcat.setBaseDir(".");

        tomcat.start();
        tomcat.getServer().await();
    }
}
