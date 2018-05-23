package com.jt.rms.role.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jt.rms.role.entity.Role;
import com.jt.rms.user.entity.User;

/**
 * 
 * @author :zjt
 * @time :2018年3月14日
 */
public interface IRoleService {
	/**
	 * 查询权限信息，根据权限ID
	 */
	Role queryRoleById(String roleId);
	
	/**
	 * 添加权限信息
	 */
	void addRole(Role user);
	/**
	 * 分页查询权限信息
	 */
	PageInfo<Role> queryRoleBatch(Map<String,String> params);
	/**
	 * 删除权限
	 */
	void deleteRole(List<Role> params);
	
	/**
	 * 更新权限信息
	 * 
	 */
	void updateRole(Role params);
}
