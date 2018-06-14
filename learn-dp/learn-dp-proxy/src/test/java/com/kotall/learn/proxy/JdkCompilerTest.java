package com.kotall.learn.proxy;

import java.io.File;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.junit.Test;

public class JdkCompilerTest {

	@Test
	public void testCompiler() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager sfm = compiler.getStandardFileManager(null, null, null);
		
		Iterable<? extends JavaFileObject> units = sfm.getJavaFileObjectsFromFiles(Arrays.asList(new File("D:/tmp/com/kotall/learn/proxy/Test.java")));
		
		compiler.getTask(null, sfm, null, null, null, units).call();
		
	}
}
