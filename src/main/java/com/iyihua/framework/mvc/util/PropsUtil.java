package com.iyihua.framework.mvc.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性文件工具类
 * 
 * @author iyihua
 *
 */
public class PropsUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

	/**
	 * 加载属性文件
	 * 
	 * @param fileNames
	 * @return
	 */
	public static Properties loadProps(String fileNames) {
		Properties props = null;
		InputStream is = null;

		is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileNames);
		try {
			if (null == is) {
				throw new FileNotFoundException(fileNames + " file is not found");

			}
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("load properties file failure", e);
		} finally {
			if (is != null) {

				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.error("close input stream failure", e);
				}
			}
		}
		return props;
	}

	/**
	 * 获取字符型属性(默认值为空字符串)
	 * 
	 * @param props
	 * @param key
	 * @return
	 */
	public static String getString(Properties props, String key) {
		return getString(props, key, "");
	}

	/**
	 * 获取字符型属性(可指定默认值)
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Properties props, String key, String defaultValue) {
		String value = defaultValue;
		if (props.containsKey(key)) {
			value = props.getProperty(key);
		}
		return value;
	}

	/**
	 * 获取数值型属性(默认值为0)
	 * 
	 * @param props
	 * @param key
	 * @return
	 */
	public static int getInt(Properties props, String key) {
		return getInt(props, key, 0);
	}

	/**
	 * 获取数值型属性(可指定默认值)
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(Properties props, String key, int defaultValue) {
		int value = defaultValue;
		if (props.containsKey(key)) {
			value = CastUtil.castInt(props.getProperty(key));
		}
		return value;
	}

	/**
	 * 获取布尔型属性(默认值为false)
	 * 
	 * @param props
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Properties props, String key) {
		return getBoolean(props, key, false);
	}

	/**
	 * 获取布尔型属性(可指定默认值)
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(Properties props, String key, Boolean defaultValue) {
		boolean value = defaultValue;
		if (props.containsKey(key)) {
			value = CastUtil.castBoolean(props.getProperty(key));
		}
		return value;
	}
}
