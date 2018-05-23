package com.jt.rms.tenant.service;
/**
 * 
 * @author :zjt
 * @time :2018年3月24日
 */

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jt.rms.tenant.entity.TenantEntity;
import com.jt.rms.user.entity.User;

public interface TenantService {
	/**
	 * 新增客户
	 */
	void addTenant(TenantEntity params,User user); 
	/**
	 * 分页查询
	 * 批量查询客户信息
	 */
	PageInfo<TenantEntity> queryTenantBatch(Map<String,String> params);
	/**
	 * 批量删除客户信息
	 */
	void deleteTenantEntity (List<TenantEntity> params);
	/**
	 * 根据客户UUID查询客户详情
	 */
	TenantEntity queryTenant(String params);
	/**
	 * 更新客户信息
	 */
	void updateTenant (TenantEntity params);
}
