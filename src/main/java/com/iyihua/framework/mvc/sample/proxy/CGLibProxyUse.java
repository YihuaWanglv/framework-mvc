package com.iyihua.framework.mvc.sample.proxy;

public class CGLibProxyUse {

    public static void main(String[] args) {
	CGLibProxy cgLibProxy = new CGLibProxy();
	Hello hexoProxy = cgLibProxy.getProxy(HelloImpl.class);
	hexoProxy.say();
	
	// sington version
	Hello hexoProxy2 = CGLibProxySington.getInstance().getProxy(HelloImpl.class);
	hexoProxy2.say();
    }
}
