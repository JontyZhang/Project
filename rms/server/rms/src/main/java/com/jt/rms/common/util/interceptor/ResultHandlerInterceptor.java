package com.jt.rms.common.util.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 
 * @author :zjt
 * @time :2018年2月4日
 */
public class ResultHandlerInterceptor extends HandlerInterceptorAdapter{
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String path = request.getServletPath();
		if(!path.startsWith("/swagger")) {
			if(modelAndView == null) {
				return;
			}
			Map<String, Object> model = modelAndView.getModel();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", true);
			result.put("code", 999999);
			result.put("time", new Date().getTime());
			result.put("data", getData(model, (HandlerMethod)handler));
			
			modelAndView.setView(new MappingJackson2JsonView());
			modelAndView.getModel().clear();
			modelAndView.addAllObjects(result);
		}
	}
	
	private Object getData(Map<String, Object> model, HandlerMethod handler) {
		Map<String, Object> result = new HashMap<String, Object>();
		String tmpKey = null;
		Object tmpValue = null;
		for (Map.Entry<String, Object> entry : model.entrySet()) {
			tmpKey = entry.getKey();
			tmpValue = entry.getValue();
			if (tmpKey.startsWith("org.springframework")) {
				continue;
			} else if (handler.getReturnType().getMethod().getReturnType().getName().contains("Map")) {
				result.put(tmpKey, tmpValue);
			} else {
				return tmpValue;
			}
		}
		return result;
	}
}
