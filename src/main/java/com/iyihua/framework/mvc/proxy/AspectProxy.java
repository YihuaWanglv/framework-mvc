package com.iyihua.framework.mvc.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 切面代理
 * 
 * @author iyihua
 *
 */
public abstract class AspectProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
	Object result = null;
	Class<?> cls = proxyChain.getTargetClass();
	Method method = proxyChain.getTargetMethod();
	Object[] params = proxyChain.getMethodParams();

	begin();
	try {
	    if (intercpet(cls, method, params)) {
		before(cls, method, params);
		result = proxyChain.doProxyChain();
		after(cls, method, params, result);
	    } else {
		result = proxyChain.doProxyChain();
	    }
	} catch (Exception e) {
	    LOGGER.error("proxy failed", e);
	    error(cls, method, params, e);
	    throw e;
	} finally {
	    end();
	}

	return result;
    }

    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
	// TODO Auto-generated method stub
	
    }

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
	// TODO Auto-generated method stub
	
    }

    public void error(Class<?> cls, Method method, Object[] params, Exception e) {
	// TODO Auto-generated method stub
	
    }

    public boolean intercpet(Class<?> cls, Method method, Object[] params) throws Throwable {
	return true;
    }

    public void begin() {
	// TODO Auto-generated method stub

    }

    public void end() {
	// TODO Auto-generated method stub

    }

}
