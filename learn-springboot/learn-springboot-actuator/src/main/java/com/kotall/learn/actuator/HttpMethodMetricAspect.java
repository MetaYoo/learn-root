package com.kotall.learn.actuator;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/1/3 14:37
 * @since 1.0.0
 */
@Aspect
@Component
public class HttpMethodMetricAspect {

    @Autowired
    private MeterRegistry meterRegistry;

    @Pointcut("@annotation(com.kotall.learn.actuator.MethodMetric)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Method targetMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 这里是为了拿到实现类的注解
        Method currentMethod = ClassUtils.getUserClass(joinPoint.getTarget().getClass())
                .getDeclaredMethod(targetMethod.getName(), targetMethod.getParameterTypes());
        if (currentMethod.isAnnotationPresent(MethodMetric.class)) {
            MethodMetric methodMetric = currentMethod.getAnnotation(MethodMetric.class);
            return processMetric(joinPoint, currentMethod, methodMetric);
        } else {
            return joinPoint.proceed();
        }
    }

    private Object processMetric(ProceedingJoinPoint joinPoint, Method currentMethod,
                                 MethodMetric methodMetric) throws Throwable {
        String name = methodMetric.name();
        if (!StringUtils.hasText(name)) {
            name = currentMethod.getName();
        }
        String desc = methodMetric.description();
        if (!StringUtils.hasText(desc)) {
            desc = name;
        }
        String[] tags = methodMetric.tags();
        if (tags.length == 0) {
            tags = new String[2];
            tags[0] = name;
            tags[1] = name;
        }
        Timer timer = Timer.builder(name).tags(tags)
                .description(desc)
                .register(meterRegistry);
        return timer.record(() -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new IllegalStateException(throwable);
            }
        });
    }
}
