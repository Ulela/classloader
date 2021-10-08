package com.cf.demo;

/**
 * @author: chenf
 * @Date: 2021/10/8 16:14
 */
public interface BaseClassLoader {

	/**
	 * get instance by class name
	 * @return Object
	 */
	Object getInstance(String javaPath, String classPath) throws IllegalAccessException, InstantiationException;


	void reloadClass(String javaPath, String classPath);
}
