/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.epcc.finexchange;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author wenzhi.wang
 * @version $Id: MultiThreadTest.java, v 0.1 Dec 30, 2016 9:45:58 PM phoenix Exp $
 */
public class MultiThreadProcessEngineTest implements Runnable {
    ApplicationContext applicationcontext = null;

    /**
     * @param applicationcontext
     */
    public MultiThreadProcessEngineTest(ApplicationContext applicationcontext) {
        super();
        this.applicationcontext = applicationcontext;
    }

    /** 
     * @see Runnable#run()
     */

    public void run() {
        ProcessEngineTest.testEngine(applicationcontext);
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationcontext = new ClassPathXmlApplicationContext(
            "core-service.xml");
        while (true) {
            new Thread(new MultiThreadProcessEngineTest(applicationcontext)).start();

            //            System.out.println(Thread.activeCount());
            Thread.sleep(1);

        }
    }

}
