package com.iyihua.framework.mvc.sample.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxySington implements MethodInterceptor {
    
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
	return  (T) Enhancer.create(cls, this);
    }
    
    private static CGLibProxySington instance = new CGLibProxySington();
    
    private CGLibProxySington() {
	
    }
    
    public static CGLibProxySington getInstance() {
	return instance;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
	before();
	Object result = proxy.invokeSuper(obj, args);
	after();
	return result;
    }

    private void after() {
	System.out.println("after");
    }

    private void before() {
	System.out.println("before");
    }

}
