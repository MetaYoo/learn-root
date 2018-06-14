package com.kotall.learn.proxy.lesson3;

import com.kotall.learn.proxy.order.OrderService;
import com.kotall.learn.proxy.order.OrderServiceImpl;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

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
            "}\n";

    public static Object newProxyInstance(Class intface) throws Exception {
        /**
         * 1. 生成java源文件
         */
        File file = new File(binPath, packagePath + "TimeOrderProxy.java");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        FileWriter fw = new FileWriter(file);
        fw.write(clz);
        fw.flush();
        fw.close();

        /**
         * 2. 编译java源文件
         */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sfm = compiler.getStandardFileManager(null, null, null);

        Iterable<? extends JavaFileObject> units = sfm.getJavaFileObjectsFromFiles(Arrays.asList(new File(binPath, packagePath + "TimeOrderProxy.java")));

        compiler.getTask(null, sfm, null, null, null, units).call();

        /**
         * 3. 加载 class文件
         */

        URL url = new URL("file:/" + binPath );
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
        Class clz = classLoader.loadClass("com.kotall.learn.proxy.TimeOrderProxy");


        OrderService target = new OrderServiceImpl();
        Constructor constructor = clz.getConstructor(intface);
        return constructor.newInstance(target);
    }

}
