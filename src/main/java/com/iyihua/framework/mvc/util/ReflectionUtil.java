package com.iyihua.framework.mvc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类
 * 
 * @author iyihua
 *
 */
public final class ReflectionUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

	/**
	 * 创建实例
	 * 
	 * @param cls
	 * @return
	 */
	public static Object newInstance(Class<?> cls) {
		Object instance;
		try {
			instance = cls.newInstance();
		} catch (Exception e) {
			LOGGER.error("new instance failed.", e);
			throw new RuntimeException(e);
		}

		return instance;
	}

	/**
	 * 调用方法
	 * 
	 * @param obj
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object invokeMethod(Object obj, Method method, Object... args) {
		Object result;
		try {
			method.setAccessible(true);
			result = method.invoke(obj, args);
		} catch (Exception e) {
			LOGGER.error("invoke method failed.", e);
			throw new RuntimeException(e);
		}

		return result;
	}

	/**
	 * 设置字段属性值
	 * 
	 * @param obj
	 * @param field
	 * @param value
	 */
	public static void setField(Object obj, Field field, Object value) {
		field.setAccessible(true);
		try {
			field.set(obj, value);
		} catch (Exception e) {
			LOGGER.error("set field failed.", e);
			throw new RuntimeException(e);
		}
	}
}
