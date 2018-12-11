package com.kotall.learn.jfinal;

import com.jfinal.config.*;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.kotall.learn.jfinal.controller.HelloController;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class DemoConfig extends JFinalConfig {

    public static void main(String[] args) {
        UndertowServer.start(DemoConfig.class, 80, true);
    }

    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/hello", HelloController.class);
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }

}
