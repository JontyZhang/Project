package com.jt.rms.user.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.user.entity.User;
import com.jt.rms.user.entity.UserRoleEntity;

/**
 * 
 * @author :zjt
 * @time :2018年1月18日
 */
public interface IUserService {
	/**
	 * 查询用户信息，根据用户ID
	 */
	User queryUserById(String userId);
	/**
	 *登陆校验
	 * 通过用户名字和密码查询用户信息
	 */
	User queryUser(User params);	
	/**
	 * 添加用户信息
	 */
	void addUser(User user);
	/**
	 * 分页查询用户信息
	 */
	PageInfo<User> queryUserBatch(Map<String,String> params);
	/**
	 * 删除用户
	 */
	void deleteUser(List<User> params);
	
	/**
	 * 更新用户信息
	 * 
	 */
	void updateUser(User params);
	/**
	 * 用户添加角色
	 */
	void addUserRole(UserRoleEntity params);
}
