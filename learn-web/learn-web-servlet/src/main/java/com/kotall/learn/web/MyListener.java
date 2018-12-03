package com.kotall.learn.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("======= myListener initialized ======");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("======= myListener destroyed ======");
    }
}
