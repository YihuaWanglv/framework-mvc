package com.iyihua.framework.mvc.sample.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor {
    
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
	return  (T) Enhancer.create(cls, this);
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
