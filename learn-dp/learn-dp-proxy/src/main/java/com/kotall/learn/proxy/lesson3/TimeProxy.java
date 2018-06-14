package com.kotall.learn.proxy.lesson3;

import java.io.File;
import java.io.FileWriter;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class TimeProxy {

    static String binPath = "D:/tmp/";
    static String packagePath = "com/kotall/learn/proxy/";

    static String clz = "package com.kotall.learn.proxy;\n" +
            "\n" +
            "import com.kotall.learn.proxy.order.OrderService;\n" +
            "\n" +
            "public class TimeOrderProxy implements OrderService {\n" +
            "\n" +
            "    private OrderService target;\n" +
            "\n" +
            "    public TimeOrderProxy(OrderService target) {\n" +
            "        this.target = target;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void order() {\n" +
            "        System.out.println(\"== startTime\");\n" +
            "        long startTime = System.currentTimeMillis();\n" +
            "        target.order();\n" +
            "        long endTime = System.currentTimeMillis();\n" +
            "        System.out.println(\"== endTime, cost: \" + (endTime - startTime));\n" +
            "    }\n" +
            "}";

    public static Class newProxyInstance(Class clazz) throws Exception {
        File file = new File(binPath, packagePath + "TimeOrderProxy.java");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        FileWriter fw = new FileWriter(file);
        fw.write(clz);
        return null;
    }

}
