package com.kotall.learn.proxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Proxy {


    public static Object newProxyInstance(Class<?> intrface, InvocationHandler handler) throws Exception {

        String binPath = "D:/tmp/";
        String packagePath = "com/kotall/learn/proxy/";
        String methodStr = "";

        Method[] methos = intrface.getMethods();
        for (Method method : methos) {
            // 配置检查
            // TODO

            String methodName = method.getName();

//            methodStr += "    @Override\n" +
//                    "    public void "+ methodName +"() {\n" +
//                    "        System.out.println(\"日志统计开始\");\n" +
//                    "\n" +
//                    "        this.target." + methodName + "();\n" +
//                    "\n" +
//                    "        System.out.println(\"日志统计结束\");\n" +
//                    "    }\n";

            methodStr += "    @Override\n" +
                    "    public void "+ methodName +"() {\n" +
                    "        try {\n" +
                    "             Method m = " + intrface.getName() +".class.getMethod(\""+methodName+"\");\n" +
                    "             this.handler.invoke(this, m);\n" +
                    "        } catch(Exception e) {\n" +
                    "             e.printStackTrace();\n" +
                    "        }\n" +
                    "    }\n";

        }

        String clazzStr = "package com.kotall.learn.proxy;\n" +
                "\n" +
                "import java.lang.reflect.*; \n" +
                "public class $Proxy0 implements " + intrface.getName() + " {\n" +
                "\n" +
                "    private com.kotall.learn.proxy.InvocationHandler handler;\n" +
                "\n" +
                "    public $Proxy0(com.kotall.learn.proxy.InvocationHandler handler) {\n" +
                "        this.handler = handler;\n" +
                "    }\n" +
                "\n" +

                methodStr +
//                "    @Override\n" +
//                "    public void addUser() {\n" +
//                "        System.out.println(\"日志统计开始\");\n" +
//                "\n" +
//                "        this.target.addUser();\n" +
//                "\n" +
//                "        System.out.println(\"日志统计结束\");\n" +
//                "    }\n" +

                "\n" +
                "\n" +
                "}";

        /**
         * 1. 生成java源文件
         */

        File file = new File(binPath, packagePath + "$Proxy0.java");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        FileWriter fw = new FileWriter(file);
        fw.write(clazzStr);
        fw.flush();
        fw.close();

        /**
         * 2. 编译java源文件
         */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sfm = compiler.getStandardFileManager(null, null, null);

        Iterable<? extends JavaFileObject> units = sfm.getJavaFileObjectsFromFiles(Arrays.asList(new File(binPath, packagePath + "$Proxy0.java")));

        compiler.getTask(null, sfm, null, null, null, units).call();


        /**
         * 3. 加载 class文件
         */

        URL url = new URL("file:/" + binPath );
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
        Class clazz = classLoader.loadClass("com.kotall.learn.proxy.$Proxy0");

        Constructor constructor = clazz.getConstructor(InvocationHandler.class);

        return constructor.newInstance(handler);
    }
}
