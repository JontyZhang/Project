package com.jt.rms.role.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.rms.common.util.Untils;
import com.jt.rms.role.dao.RoleMapper;
import com.jt.rms.role.entity.Role;
import com.jt.rms.role.service.IRoleService;
import com.jt.rms.user.entity.User;

/**
 * 
 * @author :zjt
 * @time :2018年3月14日
 */
@Service("IRoleService")
public class RoleServiceImpl implements IRoleService {
	@Resource
	RoleMapper roleMapper;
	@Override
	public Role queryRoleById(String roleId) {
		Role role=null;
		if(roleId!=null&&!roleId.equals("")){
			role=roleMapper.queryRoleById(roleId);
		}
		return role;
	}

	@Override
	public void addRole(Role role) {
		if(role!=null){
			role.setRoleId(Untils.getUUID("ID_"));
			roleMapper.insertRole(role);
		}
		else{
			System.out.println("对象为空");
		}
		
	}

	@Override
	public PageInfo<Role> queryRoleBatch(Map<String, String> params) {
		PageHelper.startPage(Integer.parseInt(params.get("pageNum")),Integer.parseInt(params.get("pageSize")));
		List<Role> resultList=roleMapper.queryRoleBatch(params);
		PageInfo<Role> pageInfo=new PageInfo<Role>(resultList);
		return pageInfo;
	}

	@Override
	public void deleteRole(List<Role> params) {
		if(null!=params&&params.size()>0){
			roleMapper.deleteRoleBatch(params);
		}
				
		
	}

	@Override
	public void updateRole(Role params) {
		if(params!=null&&params.getRoleId()!=null&&!params.getRoleId().equals("")){
			roleMapper.updateRole(params);
		}
	}
}
