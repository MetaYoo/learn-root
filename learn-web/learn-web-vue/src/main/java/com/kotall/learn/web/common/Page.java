package com.kotall.learn.web.common;

import lombok.Data;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/5/24 20:01
 * @since 1.0.0
 */
@Data
public class Page {
    private int code;
    private int count;
    private int limit;
    private Object data;
}
