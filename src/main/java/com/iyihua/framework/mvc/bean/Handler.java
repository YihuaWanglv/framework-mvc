package com.iyihua.framework.mvc.bean;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 * @author iyihua
 *
 */
public class Handler {

    private Class<?> controllerClass;
    private Method actionMethod;
    public Handler(Class<?> controllerClass, Method actionMethod) {
	super();
	this.controllerClass = controllerClass;
	this.actionMethod = actionMethod;
    }
    
    public Class<?> getControllerClass() {
	return controllerClass;
    }
    
    public Method getActionMethod() {
	return actionMethod;
    }
    
}
