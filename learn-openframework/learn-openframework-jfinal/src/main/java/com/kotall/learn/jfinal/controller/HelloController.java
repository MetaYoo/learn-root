package com.kotall.learn.jfinal.controller;

import com.jfinal.core.Controller;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class HelloController extends Controller {

    public void index() {
        renderText("Hello JFinal world !");
    }
}
