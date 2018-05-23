package com.jt.rms.user.service.impl;
import com.jt.rms.common.util.exception.ServiceException;
import com.jt.rms.common.util.isnotnull.StringUtils;
import com.jt.rms.rentinghouse.dao.RentingHouseMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.util.CodePageUtil;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.rms.user.dao.UserMapper;
import com.jt.rms.user.entity.User;
import com.jt.rms.user.entity.UserRoleEntity;
import com.jt.rms.user.service.IUserService;
import com.jt.rms.common.constant.Code;
import com.jt.rms.common.util.Untils;
import com.jt.rms.role.dao.RoleMapper;
import com.jt.rms.role.entity.Role;
/**
 * 
 * @author :zjt
 * @time :2018年1月18日
 */
@Service("IUserService")
public class UserServiceImpl implements IUserService {
	@Resource
	UserMapper userMapper;
	@Resource 
	RoleMapper roleMapper;
	@Resource
	RentingHouseMapper rentingHouseMapper;
	/**
	 * 通过USERID查询用户
	 */
	@Override
	public User queryUserById(String userId) {
		User user=null;
		if(userId!=null&&!userId.equals("")){
			user=userMapper.queryUserById(userId);
			System.out.println(user);
		}
		return user;
	}
	/**
	 *登陆校验
	 * 通过用户名字和密码查询用户信息
	 */
	@Override
	public User queryUser (User params){
		User result =null;
		if(params.getUserName()==null||params.getUserName().equals("")){
			throw new ServiceException(Code.EMPTY_USERNAME.getErrorCode(),Code.EMPTY_USERNAME.getErrorMessage());
		}
		if(params.getUserPassword()==null||params.getUserPassword().equals("")){
			throw new ServiceException(Code.EMPTY_USERPWD.getErrorCode(),Code.EMPTY_USERPWD.getErrorMessage());
		}
		result =userMapper.queryUser(params);
		List<Role> roleList=new ArrayList<>();
		Role tempRole=null;
		//查询用户的USER_ROLE连接
		List<UserRoleEntity> tempList=userMapper.queryUserRoleList(result);
		if(!CollectionUtils.isEmpty(tempList)){
			//遍历tempList 根据ROLE_ID查询角色信息
			for(int j =0 ;j<tempList.size();j++){
				tempRole=roleMapper.queryRoleById(tempList.get(j).getRoleId());
				roleList.add(tempRole);
			}
			result.setRoleList(roleList);
		}
		return  result;
	}
	/**
	 * 添加用户
	 */
	@Override
	public void addUser(User user) {
		//对用户信息进行校验
		checkMsg(user);
		user.setUserId(Untils.getUUID("USER_"));
		userMapper.insertUser(user);
	}
	/**
	 * 分页查询用户信息
	 */
	@Override
	public PageInfo<User> queryUserBatch(Map<String, String> params) {
		PageHelper.startPage(Integer.parseInt(params.get("pageNum")),Integer.parseInt(params.get("pageSize")));
		List<User> resultList=userMapper.queryUserBatch(params);
		for(int i=0;i<resultList.size();i++){
			User tempUser=resultList.get(i);
			List<Role> roleList=new ArrayList<>();
			Role tempRole=null;
			//查询用户的USER_ROLE连接
			List<UserRoleEntity> tempList=userMapper.queryUserRoleList(tempUser);
			if(tempList.isEmpty()){
				continue;
			}
			//遍历tempList 根据ROLE_ID查询角色信息
			for(int j =0 ;j<tempList.size();j++){
				tempRole=roleMapper.queryRoleById(tempList.get(j).getRoleId());
				roleList.add(tempRole);
			}
			tempUser.setRoleList(roleList);
		}
		PageInfo<User> pageInfo=new PageInfo<User>(resultList);
		return pageInfo;
	}
	/**
	 * 删除用户
	 */
	@Override
	public void deleteUser(List<User> params) {
		//检查是否该用户是否拥有房子，若拥有则不允许删除,或者先删除房子
		for (User dd : params) {
			int count = rentingHouseMapper.queryCountRentingHouseUserConn(dd);
			if(count>0){
				throw new ServiceException(Code.ERROR_DELETE_USER_H.getErrorCode(), Code.ERROR_DELETE_USER_H.getErrorMessage());
			}
		}		
		if(StringUtils.isNotEmpty(params)){
			userMapper.deleteUser(params);
		}
		//删除用户的角色		
		for(int i=0;i<params.size();i++){
			userMapper.deleteUserRole(params.get(i));	
		}
	}
	/**
	 * 更新用户信息
	 */
	@Override
	public void updateUser(User params) {
		if(params!=null&&params.getUserId()!=null&&!params.getUserId().equals("")){
			userMapper.updateUser(params);
		}
		
	}
	/**
	 * 为用户添加角色
	 */
	@Override
	public void addUserRole(UserRoleEntity params) {
		User user=null;
		if(params!=null){
			user.setUserId(params.getUserId());

		}
		//删除原有角色		
		userMapper.deleteUserRole(user);			
		//新增角色
		params.setId(Untils.getUUID("ID_"));
		userMapper.addUserRole(params);
		
	}
	/**
	 * 对输入用户信息进行校验
	 * @param params
	 */
	private void checkMsg(User params){
		if(StringUtils.isEmpty(params.getUserName())){
			throw new ServiceException(Code.EMPTY_USERNAME.getErrorCode(), Code.EMPTY_USERNAME.getErrorMessage());
		}
		if(StringUtils.isEmpty(params.getUserPassword())){
			throw new ServiceException(Code.EMPTY_USERPWD.getErrorCode(), Code.EMPTY_USERPWD.getErrorMessage());
		}
		if(StringUtils.isEmpty(params.getPhoneNum())){
			throw new ServiceException(Code.EMPTY_PHONE_NUM.getErrorCode(), Code.EMPTY_PHONE_NUM.getErrorMessage());
		}
	}
}
