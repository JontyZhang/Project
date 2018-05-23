package com.jt.rms.common.util.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 定义统一的异常处理过滤器
 * @author :zjt
 * @time :2018年3月21日
 */
public class CommonExceptionHandler extends ExceptionHandlerExceptionResolver{
private static final Log logger = LogFactory.getLog(CommonExceptionHandler.class);
	
	public ModelAndView doResolveHandlerMethodException(
			HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethodØ, Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder msg = new StringBuilder();
		map.put("success",  false);
		
		Throwable te = ex;
		if(te instanceof UndeclaredThrowableException) {
			te = ((UndeclaredThrowableException) te).getUndeclaredThrowable();
		}
		if(te instanceof InvocationTargetException) {
			te = ((InvocationTargetException) te).getTargetException();
		}
		if (te instanceof SQLException) {
			SQLException e = (SQLException)te;
			map.put("code", e.getErrorCode());
			msg.append(e.getMessage());
		} else if(te instanceof ServiceException){
			ServiceException e = (ServiceException)te;
			map.put("code", e.getCode());
			msg.append(te.getMessage());
		} else if(te instanceof SocketException) {
			msg.append("您的操作太快了~");
		} else {
			StringWriter writer = new StringWriter();
			PrintWriter print = new PrintWriter(writer);
			te.printStackTrace(print);
			
			map.put("log", writer.toString());
			
			logger.error("其它异常", te);
			map.put("code", 500);
			msg.append("服务器繁忙，请稍后再试！");
		}
		map.put("msg", msg.toString());
		
		return new ModelAndView(new MappingJackson2JsonView(), map);
	}
}
