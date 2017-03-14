package com.iyihua.framework.mvc.sample.proxy;

import java.lang.reflect.Proxy;

public class DynamicProxyUse {

    public static void main(String[] args) {
	// jdk动态代理，重构前
	Hello hello = new HelloImpl();
	DynamicProxy dynamicProxy = new DynamicProxy(hello);
	Hello helloProxy = (Hello) Proxy.newProxyInstance(
		hello.getClass().getClassLoader(), 
		hello.getClass().getInterfaces(),
		dynamicProxy);
	helloProxy.say();

	// jdk动态代理，重构后
	DynamicProxy dynamicProxy2 = new DynamicProxy(new HelloImpl());
	Hello helloProx2 = dynamicProxy2.getProxy();
	helloProx2.say();
	
    }
}
