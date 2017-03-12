package com.iyihua.framework.mvc.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 封装请求信息
 * 
 * @author iyihua
 *
 */
public class Request {

    private String requestMethod;

    private String requestPath;

    public Request(String requestMethod, String requestPath) {
	super();
	this.requestMethod = requestMethod;
	this.requestPath = requestPath;
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj);
    }

    public String getRequestMethod() {
	return requestMethod;
    }

    public String getRequestPath() {
	return requestPath;
    }

}
