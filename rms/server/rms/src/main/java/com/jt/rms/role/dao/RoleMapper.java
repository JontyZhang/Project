package com.jt.rms.role.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.rms.role.entity.Role;

/**
 * 
 * @author :zjt
 * @time :2018年3月14日
 */
public interface RoleMapper {
	/**
	 * 角色查询
	 */
	Role queryRoleById(@Param("roleId") String roleId);
	/**
	 * 添加角色
	 */
	int insertRole(Role params);
	/**
	 * 分页批量查询角色
	 */
	List<Role> queryRoleBatch(Map<String,String> params);
	/**
	 * 删除角色
	 */
	int deleteRoleBatch(List<Role> params);
	/**
	 * 更新角色信息
	 */
	void updateRole(Role params);
}
