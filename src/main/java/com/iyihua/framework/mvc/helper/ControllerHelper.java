package com.iyihua.framework.mvc.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.iyihua.framework.mvc.annotation.Action;
import com.iyihua.framework.mvc.bean.Handler;
import com.iyihua.framework.mvc.bean.Request;
import com.iyihua.framework.mvc.util.ArrayUtil;
import com.iyihua.framework.mvc.util.CollectionUtil;

/**
 * 控制器助手类
 * 
 * @author iyihua
 *
 */
public final class ControllerHelper {

	/**
	 * 请求与处理器的映射关系表
	 */
	private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

	static {
		// 获取并遍历controller类，获取类中有Action注解的方法，从注解的value值中取出mapping配置，解析出请求的请求方法和请求路径。
		// 生成封装好的Request,Handler类，最后放入请求与处理器映射关系里面
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if (CollectionUtil.isNotEmpty(controllerClassSet)) {
			for (Class<?> controllerClass : controllerClassSet) {
				Method[] methods = controllerClass.getDeclaredMethods();
				if (ArrayUtil.isNotEmpty(methods)) {
					for (Method method : methods) {
						if (method.isAnnotationPresent(Action.class)) {
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							if (mapping.matches("\\w+:/\\w*")) {
								String[] array = mapping.split(":");
								if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
									Request request = new Request(array[0], array[1]);
									Handler handler = new Handler(controllerClass, method);
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 获取Handler
	 * 
	 * @param requestMethod
	 * @param requestPath
	 * @return
	 */
	public static Handler getHandler(String requestMethod, String requestPath) {
		return ACTION_MAP.get(new Request(requestMethod, requestPath));
	}
}
