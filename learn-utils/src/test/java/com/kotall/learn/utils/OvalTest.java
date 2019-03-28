package com.kotall.learn.utils;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.junit.Test;

import java.util.List;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/28 8:46
 * @since 1.0.0
 */
public class OvalTest {

    @Test
    public void testOval() {
        LogDTO logDTO = new LogDTO(123, "123");
        Validator validator = new Validator();
        List<ConstraintViolation> results =  validator.validate(logDTO);
        for (ConstraintViolation result : results) {
            System.out.println(result);
        }
    }
}
