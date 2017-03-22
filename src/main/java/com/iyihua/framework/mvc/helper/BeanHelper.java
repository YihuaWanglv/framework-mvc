package com.iyihua.framework.mvc.helper;

import java.util.HashMap;
import java.util.Map;

import com.iyihua.framework.mvc.util.ReflectionUtil;

/**
 * Bean助手类
 * 
 * @author iyihua
 *
 */
public final class BeanHelper {

    /**
     * 定义BeanMap(用户存放Bean类与Bean实例的映射关系)
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
	for (Class<?> cls : ClassHelper.getBeanClassSet()) {
	    BEAN_MAP.put(cls, ReflectionUtil.newInstance(cls));
	}
    }

    /**
     * 获取BeanMap
     * 
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
	return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     * 
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> cls) {
	if (!BEAN_MAP.containsKey(cls)) {
	    throw new RuntimeException("can not get bean by class: " + cls);
	}
	return (T) BEAN_MAP.get(cls);
    }

    /**
     * 设置bean实例
     * 
     * @param cls
     * @param obj
     */
    public static void setBean(Class<?> cls, Object obj) {
	BEAN_MAP.put(cls, obj);
    }
}
