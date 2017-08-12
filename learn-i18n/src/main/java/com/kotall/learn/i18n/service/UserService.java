package com.kotall.learn.i18n.service;

import com.kotall.learn.i18n.util.I18NUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/8/12 0012 上午 11:09
 * @version: 1.0.0
 */
@Service("userService")
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public String formatLog1(String param) {
        logger.info(I18NUtil.getMessage("param.length.valid", param));
        return I18NUtil.getMessage("param.length.valid", param);
    }

    public String formatLog2(String param) {
        logger.info(I18NUtil.getMessage("param.format.valid", param, "yyyy-MM-dd"));
        return I18NUtil.getMessage("param.format.valid", param, "yyyy-MM-dd");
    }

    public String formatLog3(String param) {
        logger.info(I18NUtil.getMessage("request.param.valid", param));
        return I18NUtil.getMessage("request.param.valid", param, "hello");
    }

}
