package com.jt.rms.tenant.dao;
/**
 * 
 * @author :zjt
 * @time :2018年3月23日
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.rms.tenant.entity.TenantEntity;

public interface TenantMapper {
	/**
	 * 根据客户UUID查询客户信息
	 */
	 TenantEntity queryTenantByUUID(@Param("id")String id);
	/**
	 * 批量查询客户信息
	 * 分页
	 */
	 List<TenantEntity> queryTenantBatch(Map<String,String> params);
	 /**
	  * 新增客户
	  */
	 void addTenant(TenantEntity params);
	 /**
	  * 删除客户
	  */
	 void deleteTenant(List<TenantEntity> params);
	 /**
	  * 更新客户信息
	  */
	 void updateTenant(TenantEntity params);
}
