package com.iyihua.framework.mvc.sample.proxy;

public class HelloProxy implements Hello {
    
    private Hello hello;
    
    public HelloProxy() {
	hello = new HelloImpl();
    }

    @Override
    public void say() {
	before();
	hello.say();
	after();
    }

    private void after() {
	System.out.println("after");
    }

    private void before() {
	System.out.println("before");
    }

}
