package com.jt.rms.user.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.jt.rms.base.BaseController;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.user.entity.User;
import com.jt.rms.user.entity.UserRoleEntity;
import com.jt.rms.user.service.IUserService;

import io.swagger.annotations.ApiOperation;
/**
 * 
 * @author :zjt
 * @time :2018年1月18日
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController{
	private static final Log logger = LogFactory.getLog(UserController.class);
	@Resource
	IUserService userService;
	@RequestMapping(value = "queryUser", method = RequestMethod.POST)
	@ApiOperation(value = "用户查询", notes = "用户查询",httpMethod = "POST", response = User.class)
	public User queryUser(@RequestBody Map<String, String> params,HttpServletRequest request) {
		if(params==null){
			logger.error("参数为空");
		}
		System.out.println(params.get("userId"));
		return userService.queryUserById(params.get("userId"));
	}	
	@RequestMapping(value = "queryUserByBass", method = RequestMethod.POST)
	@ApiOperation(value = "用户查询", notes = "用户查询",httpMethod = "POST", response = User.class)
	public User queryUserByBass(HttpServletRequest request) {
		User user = getUserByBess(request);
		return user;
	}		
	@RequestMapping(value="addUser",method=RequestMethod.POST)
	@ApiOperation(value="添加用户",httpMethod="POST",notes="添加用户", response = Void.class)
	private void addUser(@Param(value = "user") @RequestBody User user) {
		userService.addUser(user);
	}
	/**
	 * 批量查询用户，分页
	 */
	@RequestMapping(value="queryUserBatch",method=RequestMethod.POST)
	@ApiOperation(value="分页查询用户",httpMethod="POST",notes="分页查询用户", response = PageInfo.class)
	private PageInfo<User>queryUserBatch(@Param(value = "user") @RequestBody  Map<String, String> params) {
		PageInfo<User> result =new PageInfo<User>();
		result=userService.queryUserBatch(params);
		return result;
	}
	/**
	 * 删除用户
	 */
	@RequestMapping(value="deleteUser",method=RequestMethod.POST)
	@ApiOperation(value="删除用户",httpMethod="POST",notes="删除用户", response = Void.class)
	private void deleteUser(@Param(value = "userId") @RequestBody List<User> params) {
		if(params!=null){
			userService.deleteUser(params);
		}		
	}
	/**
	 * 更新用户信息
	 */
	@RequestMapping(value="updateUser",method=RequestMethod.POST)
	@ApiOperation(value="更新用户",httpMethod="POST",notes="更新用户", response = Void.class)
	private void updateUser(@Param(value = "User") @RequestBody User user) {
		if(user!=null){
			userService.updateUser(user);
		}		
	}
	/**
	 * 用户添加角色
	 */
	@RequestMapping(value="addUserRole",method=RequestMethod.POST)
	@ApiOperation(value="用户添加角色",httpMethod="POST",notes="用户添加角色", response = Void.class)
	private void addUserRole(@Param(value = "User") @RequestBody UserRoleEntity params) {
		if(params!=null){
			userService.addUserRole(params);
		}		
	}
}
