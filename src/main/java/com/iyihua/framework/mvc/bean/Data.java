package com.iyihua.framework.mvc.bean;

/**
 * 封装的数据对象
 * @author iyihua
 *
 */
public class Data {

    private Object model;
    
    public Data(Object model) {
	this.model = model;
    }
    
    public Object getModel() {
	return model;
    }
}
