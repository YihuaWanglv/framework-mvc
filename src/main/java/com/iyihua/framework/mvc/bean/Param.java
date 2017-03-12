package com.iyihua.framework.mvc.bean;

import java.util.Map;

import com.iyihua.framework.mvc.util.CastUtil;

/**
 * 封装的请求参数对象
 * 
 * @author iyihua
 *
 */
public class Param {

    private final Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
	this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMapMap() {
	return paramMap;
    }

    public String getString(String name) {
	return CastUtil.castString(get(name));
    }

    public double getDouble(String name) {
	return CastUtil.castDouble(get(name));
    }

    public long getLong(String name) {
	return CastUtil.castLong(get(name));
    }

    public int getInt(String name) {
	return CastUtil.castInt(get(name));
    }

    private Object get(String name) {
	return paramMap.get(name);
    }
}
