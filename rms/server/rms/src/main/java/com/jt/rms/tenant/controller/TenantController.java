package com.jt.rms.tenant.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.jt.rms.base.BaseController;
import com.jt.rms.rentinghouse.controller.RentingHouseController;
import com.jt.rms.tenant.entity.TenantEntity;
import com.jt.rms.tenant.service.TenantService;
import com.jt.rms.user.entity.User;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author :zjt
 * @time :2018年3月25日
 */
@Controller
@RequestMapping("tenant")
public class TenantController extends BaseController{
	private static final Log logger = LogFactory.getLog(TenantController.class);
	@Resource
	TenantService tenantService;
	/**
	 * 批量查询客户
	 * @param params
	 * @return
	 */
	@RequestMapping(value="queryTenantBatch" ,method=RequestMethod.POST)
	@ApiOperation(value = "批量查询客户", notes = "批量查询客户",httpMethod = "POST", response = PageInfo.class)
	public PageInfo<TenantEntity> queryTenantBatch (@RequestBody Map<String,String> params,HttpServletRequest request){
		//获取用户信息
		User user = getUserByBess(request);
		params.put("landlordId", user.getUserId());
		PageInfo<TenantEntity> resultList = new PageInfo<TenantEntity>();
		resultList=tenantService.queryTenantBatch(params);
		return resultList;
	}
	/**
	 * 新增客户
	 */
	@RequestMapping(value="	" ,method=RequestMethod.POST)
	@ApiOperation(value = "新增客户",notes="新增客户",httpMethod="POST" ,response=void.class)
	public void addTenant (@RequestBody TenantEntity params,HttpServletRequest request){
		User user = getUserByBess(request);
		tenantService.addTenant(params, user);
	}
	/**
	 * 删除客户信息
	 */
	@RequestMapping(value="deleteTenant",method=RequestMethod.POST)
	@ApiOperation(value="删除客户信息",notes="删除客户信息",httpMethod="POST",response=void.class)
	public void deleteTenant(@RequestBody List<TenantEntity> params){
		tenantService.deleteTenantEntity(params);
	}
	/**
	 * 查询客户详情
	 */
	@RequestMapping(value="queryTenant",method=RequestMethod.POST)
	@ApiOperation(value="查询客户详情",notes="查询客户详情",httpMethod="POST",response=TenantEntity.class)
	public TenantEntity queryTenant(@RequestBody TenantEntity params){
		TenantEntity result = new TenantEntity();
		result=tenantService.queryTenant(params.getTenantId());
		return result;
	}
	/**
	 * 更新客户信息
	 */
	@RequestMapping(value="updateTenant" ,method=RequestMethod.POST)
	@ApiOperation(value="更新客户信息",notes="更新客户信息",httpMethod="POST",response=void.class)
	public void updateTenant(@RequestBody TenantEntity params){
		tenantService.updateTenant(params);
	}
}
