package com.cf.demo;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: chenf
 * @Date: 2021/10/8 16:39
 */
public class DefaultClassLoader implements BaseClassLoader {

	private volatile Class<?> clazz = null;
	private Object object = null;

	/**
	 * get instance by class name
	 *
	 * @return Object
	 */
	@Override
	public Object getInstance(String javaPath, String classPath) throws IllegalAccessException, InstantiationException {
		//使用单例模式
		synchronized (Object.class) {
			if (object == null) {
				reloadClass(javaPath, classPath);
				object = clazz.newInstance();
				return object;
			} else {
				return object;
			}
		}
	}

	public void clear() {
		clazz = null;
		object = null;
	}

	public Object getClazz() {
		synchronized (Object.class) {
			return clazz;
		}
	}

	/**
	 * reload a class file
	 *
	 */
	@Override
	public void reloadClass(String javaPath, String classPath) {
		//生成class文件
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		compiler.run(null, null, null, javaPath);

		//获取自定义类加载器,指定父类加载器，null为Booststrap类加载器
		ClassFileClassLoader myClassLoad = new ClassFileClassLoader(classPath,null);
		try {
			//获取对象的class对象
			clazz = myClassLoad.loadClass("com.cf.demo.TestDemo");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
