package com.kotall.learn.str;

import java.util.HashMap;
import java.util.Map;

/**
 * （字符串哈希表）找到字符串中不重复出现字符的最长子串
 * @author zpwang
 * @version 1.0.0
 */
public class StrTest {
    public static void main(String[] args) {
        String str = "abcfadef";
//        String str = "abcabc";

        StrTest strTest = new StrTest();
        String max = strTest.maxSubStr(str);
        System.out.println(max);

    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), max = 0;
        int[] index = new int[128];
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            max = Math.max(max, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return max;
    }

    // abcfadef
    // abcabc
    public String maxSubStr(String str) {
        Map<Character, Integer> map = new HashMap<>(str.length());
        int max = 0;
        int lastIdx = 0;
        for (int i=0, j=0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                j = Math.max(map.get(str.charAt(i)),j);
            }
            if ((i-j+1) > max) {
                lastIdx = i;
                max = i -j + 1;
            }
            map.put(str.charAt(i), i+1);
        }
        return str.substring(lastIdx - max + 1, lastIdx + 1);
    }
}
