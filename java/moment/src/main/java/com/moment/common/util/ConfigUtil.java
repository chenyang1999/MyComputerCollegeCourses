package com.moment.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigUtil {
	private static Properties pro;
	static{
		//InputStream input=ConfigUtil.class
				//.getResourceAsStream("/conf/config.properties");
		
		InputStream input=ConfigUtil.class.getClassLoader()
				.getResourceAsStream("config.properties");
		pro=new Properties();
		try {
			pro.load(new InputStreamReader(input,"UTF-8"));
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 注释
		}
	}
	public static String getValue(String key){
		return pro.getProperty(key);
	}
}
