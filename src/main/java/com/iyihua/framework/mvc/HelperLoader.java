package com.iyihua.framework.mvc;

import com.iyihua.framework.mvc.helper.BeanHelper;
import com.iyihua.framework.mvc.helper.ClassHelper;
import com.iyihua.framework.mvc.helper.ControllerHelper;
import com.iyihua.framework.mvc.helper.IocHelper;
import com.iyihua.framework.mvc.util.ClassUtil;

/**
 * 集中加载相应的helper类
 * @author iyihua
 *
 */
public final class HelperLoader {

    public static void init() {
	Class<?>[] classList = {
		ClassHelper.class,
		BeanHelper.class,
		IocHelper.class,
		ControllerHelper.class
	};
	for (Class<?> cls : classList) {
	    ClassUtil.loadClass(cls.getName());
	}
    }
}
