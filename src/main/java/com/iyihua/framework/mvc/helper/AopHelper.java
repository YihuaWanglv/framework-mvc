package com.iyihua.framework.mvc.helper;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iyihua.framework.mvc.annotation.Aspect;
import com.iyihua.framework.mvc.proxy.AspectProxy;
import com.iyihua.framework.mvc.proxy.Proxy;
import com.iyihua.framework.mvc.proxy.ProxyManager;

public class AopHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    static {
	try {
	    Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
	    Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
	    for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
		Class<?> targetClass = targetEntry.getKey();
		List<Proxy> proxyList = targetEntry.getValue();
		Object proxy = ProxyManager.createProxy(targetClass, proxyList);
		BeanHelper.setBean(targetClass, proxy);
	    }
	} catch (Exception e) {
	    LOGGER.error("aop failed", e);
	}
    }

    /**
     * 获取Aspect注解中设置的注解类
     * 
     * @param aspect
     * @return
     * @throws Exception
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception {
	Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
	Class<? extends Annotation> annotation = aspect.values();
	if (annotation != null && !annotation.equals(Aspect.class)) {
	    targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
	}
	return targetClassSet;
    }

    /**
     * 获取代理类(切面类)及其目标类集合之间的映射关系
     * 
     * @return
     * @throws Exception
     */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
	Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
	Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
	for (Class<?> proxyClass : proxyClassSet) {
	    if (proxyClass.isAnnotationPresent(Aspect.class)) {
		Aspect aspect = proxyClass.getAnnotation(Aspect.class);
		Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
		proxyMap.put(proxyClass, targetClassSet);
	    }
	}
	return proxyMap;
    }

    /**
     * 获取目标类与代理对象列表之间的映射关系
     * 
     * @param proxyMap
     * @return
     * @throws IllegalAccessException
     * @throws Exception
     */
    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
	Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
	for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
	    Class<?> proxyClass = proxyEntry.getKey();
	    Set<Class<?>> targetClassSet = proxyEntry.getValue();
	    for (Class<?> targetClass : targetClassSet) {
		Proxy proxy = (Proxy) proxyClass.newInstance();
		if (targetMap.containsKey(targetClass)) {
		    targetMap.get(targetClass).add(proxy);
		} else {
		    List<Proxy> proxyList = new ArrayList<Proxy>();
		    proxyList.add(proxy);
		    targetMap.put(targetClass, proxyList);
		}
	    }
	}
	return targetMap;
    }
}
