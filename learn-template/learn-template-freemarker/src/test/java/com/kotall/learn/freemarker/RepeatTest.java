package com.kotall.learn.freemarker;

import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class RepeatTest {

    public static void main(String[] args) {
        Map<String, Object> root = new HashMap<>();

        root.put("repeat", new RepeatDirective());

        String tplPath = RepeatTest.class.getClassLoader().getResource("tpl").getFile();

        FreeMarkertUtil.processTemplate(tplPath, "repeat.ftl", "UTF-8", root, new OutputStreamWriter(System.out));

    }
}
