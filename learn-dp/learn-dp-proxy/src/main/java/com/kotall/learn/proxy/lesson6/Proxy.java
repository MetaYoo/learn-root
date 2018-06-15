package com.kotall.learn.proxy.lesson6;

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

    static String binPath = "D:/tmp/";
    static String packagePath = "com/kotall/learn/proxy/";


    public static Object newProxyInstance(Class intrface, InvocationHandler invocationHandler) throws Exception {

        String methodStr = "";
        Method[] methods = intrface.getMethods();
        for (Method method : methods) {
            String str = "    @Override\n" +
                     "    public void " + method.getName() + "() {\n" +
                     "        try {\n" +
                     "            Method m = " + intrface.getName() +".class.getMethod(\"" + method.getName() + "\");\n" +
                     "            handler.invoke(this, m);\n" +
                     "        } catch (Exception e) {\n" +
                     "            e.printStackTrace();\n" +
                     "        }\n" +
                     "    }\n";
            methodStr += str;
        }

        String clzStr = "package com.kotall.learn.proxy;\n" +
                "\n" +
                "import java.lang.reflect.*;\n" +
                "\n" +
                "public class $Proxy0 implements "+ intrface.getName() +" {\n" +
                "\n" +
                "    private com.kotall.learn.proxy.lesson6.InvocationHandler handler;\n" +
                "\n" +
                "    public $Proxy0(com.kotall.learn.proxy.lesson6.InvocationHandler handler) {\n" +
                "        this.handler = handler;\n" +
                "    }\n" +
                "\n" +

                methodStr +

//                "    @Override\n" +
//                "    public void order() {\n" +
//                "        System.out.println(\"== startTime\");\n" +
//                "        long startTime = System.currentTimeMillis();\n" +
//                "        target.order();\n" +
//                "        long endTime = System.currentTimeMillis();\n" +
//                "        System.out.println(\"== endTime, cost: \" + (endTime - startTime));\n" +
//                "    }\n" +

                "}\n";

        /**
         * 1. 生成java源文件
         */
        File file = new File(binPath, packagePath + "$Proxy0.java");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        FileWriter fw = new FileWriter(file);
        fw.write(clzStr);
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
        Class clz = classLoader.loadClass("com.kotall.learn.proxy.$Proxy0");

        /**
         * 4. 删掉源文件
         */
        if (file.exists()) {
//        	file.delete();
        }
        
        Constructor constructor = clz.getConstructor(InvocationHandler.class);
        return constructor.newInstance(invocationHandler);
    }

}
