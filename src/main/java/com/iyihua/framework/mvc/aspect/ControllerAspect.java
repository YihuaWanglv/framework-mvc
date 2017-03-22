package com.iyihua.framework.mvc.aspect;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iyihua.framework.mvc.annotation.Aspect;
import com.iyihua.framework.mvc.annotation.Controller;
import com.iyihua.framework.mvc.proxy.AspectProxy;

/**
 * 拦截Controller所有方法
 * 
 * @author iyihua
 *
 */
@Aspect(values = Controller.class)
public class ControllerAspect extends AspectProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
	LOGGER.debug("---------begin---------");
	LOGGER.debug(String.format("class: %s", cls.getName()));
	LOGGER.debug(String.format("class: %s", method.getName()));
	begin = System.currentTimeMillis();
    }

    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
	LOGGER.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
	LOGGER.debug("----- ----end------ ---");
    }

}
