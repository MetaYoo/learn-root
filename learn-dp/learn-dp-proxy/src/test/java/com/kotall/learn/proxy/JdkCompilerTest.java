package com.kotall.learn.proxy;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.junit.Test;

public class JdkCompilerTest {

	static String binPath = "D:/tmp/";
	static String packagePath = "com/kotall/learn/proxy/";

	static String clz = "package com.kotall.learn.proxy;\n" +
			"\n" +
			"public class Test {\n" +
			"    public static void main(String[] args) {\n" +
			"        System.out.println(\"hello world!\");\n" +
			"    }\n" +
			"}\n";


	@Test
	public void testCompiler() throws Exception {
		/**
		 * 1. 生成java源文件
		 */

		File file = new File(binPath, packagePath + "Test.java");
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
		
		Iterable<? extends JavaFileObject> units = sfm.getJavaFileObjectsFromFiles(Arrays.asList(new File(binPath, packagePath + "Test.java")));
		
		compiler.getTask(null, sfm, null, null, null, units).call();

		/**
		 * 3. 加载 class文件
		 */

		URL url = new URL("file:/" + binPath );
		URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
		Class clazz = classLoader.loadClass("com.kotall.learn.proxy.Test");

		System.out.println(clazz.getName());

	}
}
