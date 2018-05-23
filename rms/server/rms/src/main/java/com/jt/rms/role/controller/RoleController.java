package com.jt.rms.role.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.jt.rms.role.entity.Role;
import com.jt.rms.role.service.IRoleService;
import com.jt.rms.user.controller.UserController;
import com.jt.rms.user.entity.User;
import com.jt.rms.user.service.IUserService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author :zjt
 * @time :2018年3月13日
 */
@Controller
@RequestMapping("role")
public class RoleController {
	private static final Log logger = LogFactory.getLog(RoleController.class);
	@Resource
	IRoleService roleService;
	/**
	 * 通过角色ID查询用户信息
	 */
	@RequestMapping(value = "queryRoleById", method = RequestMethod.POST)
	@ApiOperation(value = "角色查询", notes = "角色查询",httpMethod = "POST", response = Role.class)
	public Role queryRole(@RequestBody Map<String, String> params) {
		if(params==null){
			logger.error("参数为空");
		}
		System.out.println(params.get("userId"));
		return roleService.queryRoleById(params.get("roleId"));
	}	
	
	@RequestMapping(value="addRole",method=RequestMethod.POST)
	@ApiOperation(value="添加角色",httpMethod="POST",notes="添加角色", response = Void.class)
	private void addRole(@Param(value = "Role") @RequestBody Role params) {
		roleService.addRole(params);
	}
	/**
	 * 批量查询角色，分页
	 */
	@RequestMapping(value="queryRoleBatch",method=RequestMethod.POST)
	@ApiOperation(value="分页查询角色",httpMethod="POST",notes="分页查询角色", response = PageInfo.class)
	private PageInfo<Role>queryUserBatch(@Param(value = "Role") @RequestBody  Map<String, String> params) {
		PageInfo<Role> result =new PageInfo<Role>();
		result=roleService.queryRoleBatch(params);
		return result;
	}
	/**
	 * 删除角色
	 */
	@RequestMapping(value="deleteRole",method=RequestMethod.POST)
	@ApiOperation(value="删除角色",httpMethod="POST",notes="删除角色", response = Void.class)
	private void deleteRole(@Param(value = "userId") @RequestBody List<Role> params) {
		if(params!=null&&params.size()>0){
			roleService.deleteRole(params);
		}		
	}
	/**
	 * 更新角色信息
	 */
	@RequestMapping(value="updateRole",method=RequestMethod.POST)
	@ApiOperation(value="更新角色",httpMethod="POST",notes="更新角色", response = Void.class)
	private void updateUser(@Param(value = "Role") @RequestBody Role params) {
		if(params!=null){
			roleService.updateRole(params);
		}		
	}
}
