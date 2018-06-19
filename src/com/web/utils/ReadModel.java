package com.web.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 读取邮件模板工具类
 * @author 吴波
 *
 */
public class ReadModel {

	public static String readModel(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ReadModel.class.getClassLoader().getResource(path).getPath())));
		String line = null;
		String str="";
		while((line=reader.readLine())!=null) {
			str+=line+"\n";
		}
		reader.close();
		return str;
	}
}
