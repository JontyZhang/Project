package com.jt.rms.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.user.entity.User;
import com.jt.rms.user.entity.UserRoleEntity;

/**
 * 
 * @author :zjt
 * @time :2018年1月18日
 */
public interface UserMapper {
	/**
	 * 用户查询
	 */
	User queryUserById(@Param("userId") String userId);
	/**
	 * 根据用户名字和密码查询用户是否存在
	 */
	User queryUser(User user);
	/**
	 * 用户身份证号码必传
	 * 根据用户名和用户身份证号码检查用户是否存在
	 */
	Integer checkUserExit(User user);
	/**
	 * 查询用户信息
	 */
	 User queryUserNew (User user);
	/**
	 * 添加用户
	 */
	int insertUser(User user);
	/**
	 * 分页批量查询用户
	 */
	List<User> queryUserBatch(Map<String,String> params);
	/**
	 * 删除用户
	 */
	int deleteUser(List<User> params);
	/**
	 * 更新用户信息
	 */
	void updateUser(User params);
	/**
	 * 根据角色ID查询角色用户连接信息
	 */
	List<UserRoleEntity> queryUserRoleList(User params);
	/**
	 * 根据用户删除用户角色
	 */
	void deleteUserRole(User params);
	/**
	 * 新增用户角色连接
	 */
	void addUserRole(UserRoleEntity params);
	/**
	 * 根据房子ID查询用户信息
	 */
	User selectUserFromHouseId(Map<String,String> params);
}
