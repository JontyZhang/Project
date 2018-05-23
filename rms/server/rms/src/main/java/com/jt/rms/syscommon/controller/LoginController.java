package com.jt.rms.syscommon.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.rms.common.cache.RedisUtil;
import com.jt.rms.common.constant.Code;
import com.jt.rms.common.util.Untils;
import com.jt.rms.common.util.exception.ServiceException;
import com.jt.rms.common.util.isnotnull.StringUtils;
import com.jt.rms.common.util.utils.CookieUtils;
import com.jt.rms.user.entity.User;
import com.jt.rms.user.service.impl.UserServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author :zjt
 * @time :2018年3月19日
 */
@Controller
@RequestMapping("loginController")
public class LoginController {
	
	@Resource
	UserServiceImpl userServiceImpl;
	/**
	 * 检验登陆信息
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ApiOperation(value = "查询个人信息", notes = "查询个人信息",httpMethod = "POST", response = User.class)
	public User loginIn(@RequestBody User params,HttpServletRequest request,HttpServletResponse response) {
		User result=null;
		//查询数据库是否存在User
		result=userServiceImpl.queryUser(params);
		if(result==null){
			throw new ServiceException(Code.EMPTY_USER.getErrorCode(),Code.EMPTY_USER.getErrorMessage());
		}else{
			//加载redis对象
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
			RedisUtil redisUtil = (RedisUtil)context.getBean("redisUtil");
			//生成toke存到redis中
			String Token = Untils.getUUID("Token_");
			//用户信息存到redis
			redisUtil.set("token", Token);
			redisUtil.set("userId", result.getUserId());
			//用户信息存到cookie中
			CookieUtils cookieUtils = new CookieUtils();
			cookieUtils.setCookie(request, response, "token", Token, 900, "utf-8");
		}
		return result;
	}
	/**
	 * 退出登陆，清空登陆信息
	 */
	@RequestMapping(value = "loginOut", method = RequestMethod.POST)
	@ApiOperation(value = "退出登陆", notes = "退出登陆",httpMethod = "POST", response = void.class)
	public String loginOut(HttpServletRequest request,HttpServletResponse response) {
		//加载redis对象
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		RedisUtil redisUtil = (RedisUtil)context.getBean("redisUtil");
		//判断redis是否存在该用户，存在则删除
		if(redisUtil.get("userId")!=null){
			redisUtil.remove("userId");
		}
		if(StringUtils.isNotEmpty(redisUtil.get("token"))){
			redisUtil.remove("token");
		}
		//删除cookie中的token
		CookieUtils cookieUtils = new CookieUtils();
		cookieUtils.deleteCookie(request, response, "token");
		return "success";
	}	
}
