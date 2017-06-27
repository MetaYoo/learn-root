package com.kotall.learn.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author aracwong
 * @version 1.0.0
 * @email
 * @datetime 2017/6/26 0026 下午 8:47
 */
public class DemoService {
    private static final Logger logger = LoggerFactory.getLogger(DemoService.class);

    public String demo() {
        logger.info("---demo---");
        return "this is demo!";
    }
}
