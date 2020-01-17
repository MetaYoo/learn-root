package com.kotall.learn;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        System.out.println(isValid("{[]}"));
    }


    // ()[]{}
    public boolean isValid(String s) {
        int index = -1;
        char[] target = new char[s.length()];
        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (index < 0) {
                target[++index] = source[i];
            } else {
                String pair = (target[index] + "" + source[i]);
                if ("()".equals(pair) || "{}".equals(pair) || "[]".equals(pair)) {
                    index--;
                } else {
                    index++;
                    target[index] = source[i];
                }
            }
        }
        if (index < 0) {
            return true;
        }
        return false;
    }
}
