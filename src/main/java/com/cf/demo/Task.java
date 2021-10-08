package com.cf.demo;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: chenf
 * @Date: 2021/10/8 16:54
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class Task{
	@SuppressWarnings("BusyWait")
	public static void main(String[] args) {
		String path = "D:\\workspace\\classloader\\src\\main\\java\\com\\cf\\demo\\TestDemo.class";
		String javaPath = "D:\\workspace\\classloader\\src\\main\\java\\com\\cf\\demo\\TestDemo.java";
		DefaultClassLoader defaultClassLoader = new DefaultClassLoader();
		Thread t1 = new Thread(() -> {
			while (true) {
				try {
					Object obj = defaultClassLoader.getInstance(javaPath, path);
					Class<?> clazz = (Class<?>) defaultClassLoader.getClazz();
					clazz.getMethod("test").invoke(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		t1.start();

		//监听文件是否被修改
		Thread t2 = new Thread(() -> {
			long lastModified = new File(javaPath).lastModified();
			while (true) {
				try {
					Thread.sleep(1000);
					long now = new File(javaPath).lastModified();
					if (now != lastModified) {
						lastModified = now;
						defaultClassLoader.clear();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		});
		t2.start();
	}
}
