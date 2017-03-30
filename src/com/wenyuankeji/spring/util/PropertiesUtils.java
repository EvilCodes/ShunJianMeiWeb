package com.wenyuankeji.spring.util;


import java.util.ResourceBundle;

/**
 * @ClassName PropertiesUtils 
 * @Description 读取properties工具类
 * @author lnn
 * @mail lnnlsn@163.com
 * @date 2015-8-28 上午10:38:31
 */
public class PropertiesUtils {
	
	private static ResourceBundle commonProperties = ResourceBundle.getBundle("config");
	/**
	 * 取得Common的properties中key对应的值
	 * @param key key
	 * @return String key对应的值
	 */
	public static String getCommonString(String key) {
		String value = commonProperties.getString(key);
		value = (value!=null)?value.trim():"";
		return value;
	}
	
}
