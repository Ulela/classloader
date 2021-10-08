package com.cf.demo;

/**
 * @author: chenf
 * @Date: 2021/10/8 16:17
 */
public class ClassFileClassLoader extends ClassLoader {

	private byte[] classByteArr;
	private final String classPath;

	public ClassFileClassLoader(String classPath, ClassLoader classLoader) {
		super(classLoader);
		this.classPath = classPath;
	}

	public void setClassByteArr(byte[] classByteArr) {
		this.classByteArr = classByteArr;
	}

	/**
	 * Finds the class with the specified <a href="#name">binary name</a>.
	 * This method should be overridden by class loader implementations that
	 * follow the delegation model for loading classes, and will be invoked by
	 * the {@link #loadClass <tt>loadClass</tt>} method after checking the
	 * parent class loader for the requested class.  The default implementation
	 * throws a <tt>ClassNotFoundException</tt>.
	 *
	 * @param name The <a href="#name">binary name</a> of the class
	 * @return The resulting <tt>Class</tt> object
	 * @since 1.2
	 */
	@Override
	protected Class<?> findClass(String name) {
		try {
			classByteArr = FileUtils.readFileToByteArray(classPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defineClass(name, classByteArr, 0, classByteArr.length);
	}
}

