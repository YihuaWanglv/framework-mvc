package com.iyihua.framework.mvc.proxy;

public interface Proxy {

    /**
     * 执行链式代理
     * 
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
