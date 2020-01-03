package com.kotall.learn.actuator;

import java.lang.annotation.*;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/1/3 14:34
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodMetric {
    String name() default "";

    String description() default "";

    String[] tags() default {};
}
