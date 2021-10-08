package com.cf.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: chenf
 * @Date: 2021/10/8 16:20
 */
public class FileUtils {

	public static byte[] readFileToByteArray(String classFilePath) throws Exception {
		File directory = new File(classFilePath);
		InputStream inputStream = new FileInputStream(directory);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			return outputStream.toByteArray();
		} finally {
			inputStream.close();
		}
	}
}
