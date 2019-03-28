package com.kotall.learn.utils;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/28 8:59
 * @since 1.0.0
 */
@Data
public class BaseDTO {

    @NotNull(message = "base为null")
    @NotBlank(message = "base为空串")
    private String base;
}
