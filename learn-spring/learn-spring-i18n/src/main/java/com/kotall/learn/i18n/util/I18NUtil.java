package com.kotall.learn.i18n.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/8/12 0012 上午 11:15
 * @version: 1.0.0
 */
public class I18NUtil {

    private static final Logger logger = LoggerFactory.getLogger(I18NUtil.class);
    private static MessageSource messageSource = (MessageSource) SpringContextUtil.getApplicationContext().getBean("messageSource");
    private static final Object[] objs = null;


    public static String getMessage(String key, Locale locale, Object... objects) {
        if (StringUtils.isEmpty(key)) {
            return StringUtils.EMPTY;
        }
        if (null == locale) {
            locale = Locale.CHINA;
        }
        // logger.info("Spring I18 ---> key：[" + key + "]，lang：[" + locale.getLanguage() + "]，params：[" + objects + "]");
        return messageSource.getMessage(key, objects, locale);
    }


    public static String getMessage(String key, Locale locale) {
        return getMessage(key, locale, objs);
    }


    public static String getMessage(String key, Object... objects) {
        return getMessage(key, null, objects);
    }

    public static String getMessage(String key) {
        return getMessage(key, null, objs);
    }
}
