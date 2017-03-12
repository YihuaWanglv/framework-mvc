package com.iyihua.framework.mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iyihua.framework.mvc.bean.Data;
import com.iyihua.framework.mvc.bean.Handler;
import com.iyihua.framework.mvc.bean.Param;
import com.iyihua.framework.mvc.bean.View;
import com.iyihua.framework.mvc.helper.BeanHelper;
import com.iyihua.framework.mvc.helper.ConfigHelper;
import com.iyihua.framework.mvc.helper.ControllerHelper;
import com.iyihua.framework.mvc.util.ArrayUtil;
import com.iyihua.framework.mvc.util.CodecUtil;
import com.iyihua.framework.mvc.util.JsonUtil;
import com.iyihua.framework.mvc.util.ReflectionUtil;
import com.iyihua.framework.mvc.util.StreamUtil;
import com.iyihua.framework.mvc.util.StringUtil;

/**
 * 请求分发器
 * 
 * @author iyihua
 *
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -3388415666149449671L;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
	HelperLoader.init();
	// 获取ServletContext对象，用于注册Servlet
	ServletContext servletContext = servletConfig.getServletContext();
	// 注册处理jsp的Servlet
	ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
	jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
	// 注册处理静态资源的默认Servlet
	ServletRegistration assetServlet = servletContext.getServletRegistration("default");
	assetServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

	Handler handler = ControllerHelper.getHandler(request.getMethod().toLowerCase(), request.getPathInfo());
	if (null != handler) {
	    // create controller class and instance
	    Class<?> controllerClass = handler.getControllerClass();
	    Object controllerBean = BeanHelper.getBean(controllerClass);
	    // create param
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    Enumeration<String> paramNames = request.getParameterNames();
	    while (paramNames.hasMoreElements()) {
		String paramName = paramNames.nextElement();
		paramMap.put(paramName, request.getParameter(paramName));
	    }
	    String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
	    if (StringUtil.isNotEmpty(body)) {
		String[] params = StringUtil.splitString(body, "&");
		if (ArrayUtil.isNotEmpty(params)) {
		    for (String param : params) {
			String[] array = StringUtil.splitString(param, "=");
			if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
			    paramMap.put(array[0], array[1]);
			}
		    }
		}
	    }
	    Param param = new Param(paramMap);
	    // invoke method
	    Method actionMethod = handler.getActionMethod();
	    Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
	    // handler of the result
	    if (result instanceof View) {
		View view = (View) result;
		String path = view.getPath();
		if (StringUtil.isNotEmpty(path)) {
		    if (path.startsWith("/")) {
			response.sendRedirect(request.getContextPath() + path);
		    } else {
			Map<String, Object> model = view.getModel();
			for (Map.Entry<String, Object> entry : model.entrySet()) {
			    request.setAttribute(entry.getKey(), entry.getValue());
			}
			request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
		    }
		}
	    } else if (result instanceof Data) {
		Data data = (Data) result;
		Object model = data.getModel();
		if (null != model) {
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    PrintWriter pw = response.getWriter();
		    pw.write(JsonUtil.toJSON(model));
		    pw.flush();
		    pw.close();
		}
	    }
	}
    }

}
