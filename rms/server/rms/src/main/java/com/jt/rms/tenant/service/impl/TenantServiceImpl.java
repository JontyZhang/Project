package com.jt.rms.tenant.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.rms.common.constant.Code;
import com.jt.rms.common.util.Untils;
import com.jt.rms.common.util.exception.ServiceException;
import com.jt.rms.common.util.isnotnull.StringUtils;
import com.jt.rms.tenant.dao.TenantMapper;
import com.jt.rms.tenant.entity.TenantEntity;
import com.jt.rms.tenant.service.TenantService;
import com.jt.rms.user.entity.User;

/**
 * 
 * @author :zjt
 * @time :2018年3月24日
 */
@Service()
public class TenantServiceImpl implements TenantService {
	@Resource
	TenantMapper tenantMapper;
	/**
	 * 分页查询客户信息
	 * 列表展示
	 */
	@Override
	public PageInfo<TenantEntity> queryTenantBatch(Map<String, String> params) {
		PageHelper.startPage(Integer.parseInt(params.get("pageNum")),Integer.parseInt(params.get("pageSize")));
		List<TenantEntity> resultList = tenantMapper.queryTenantBatch(params);
		PageInfo<TenantEntity> result = new PageInfo<TenantEntity>(resultList);
		return result;
	}
	/**
	 * 批量删除客户信息
	 */
	@Override
	public void deleteTenantEntity(List<TenantEntity> params) {
		for (TenantEntity tenantEntity : params) {
			if(StringUtils.isEmpty(tenantEntity.getTenantId())){
				throw new ServiceException(Code.EMPTY_TEANNT_ID.getErrorCode(),Code.EMPTY_TEANNT_ID.getErrorMessage());
			}
		}
		//执行删除
		tenantMapper.deleteTenant(params);
	}
	/**
	 * 根据客户UUID查询客户信息
	 */
	@Override
	public TenantEntity queryTenant(String params) {
		if(StringUtils.isEmpty(params)){
			throw new  ServiceException(Code.EMPTY_TEANNT_ID.getErrorCode(),Code.EMPTY_TEANNT_ID.getErrorMessage());
		}
		TenantEntity result = new TenantEntity();
		System.out.println(params);
		result = tenantMapper.queryTenantByUUID(params);
		return result;
	}
	/**
	 * 新增客户
	 */
	@Override
	public void addTenant(TenantEntity params,User user) {
		params.setLandlordId(user.getUserId());
		params.setTenantId(Untils.getUUID("TENANT_ID_"));;
		//执行插入
		tenantMapper.addTenant(params);
	}
	@Override
	public void updateTenant(TenantEntity params) {
		if(params.getTenantId()!=null){
			tenantMapper.updateTenant(params);
		}	
	}

}
