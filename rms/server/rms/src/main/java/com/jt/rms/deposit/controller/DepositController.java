package com.jt.rms.deposit.controller;
/**
 * 
 * @author :zjt
 * @time :2018年3月20日
 */

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

import com.github.pagehelper.PageInfo;
import com.jt.rms.base.BaseController;
import com.jt.rms.deposit.entity.DepositEntity;
import com.jt.rms.deposit.service.IDepositService;
import com.jt.rms.user.entity.User;

import io.swagger.annotations.ApiOperation;
@Controller
@RequestMapping("deposit")
public class DepositController extends BaseController{
	private static final Log logger = LogFactory.getLog(DepositController.class);
	@Resource
	IDepositService iDepositService;
	/**
	 * 新增押金表
	 */
	@RequestMapping(value = "addDeposit", method = RequestMethod.POST)
	@ApiOperation(value = "添加押金表", notes = "添加押金表",httpMethod = "POST", response = void.class)
	public void addRentingHouse(@RequestBody DepositEntity params, HttpServletRequest request) {	
		User user = null;
		user = getUserByBess(request);
		params.setCreateBy(user.getUserId());
		iDepositService.addDeposit(params);
	}
	/**
	 * 分页查询押金表
	 */
	@RequestMapping(value = "queryDepositBatch", method = RequestMethod.POST)
	@ApiOperation(value = "批量查询押金表", notes = "批量查询押金表",httpMethod = "POST", response = PageInfo.class)
	public PageInfo<DepositEntity> queryRentingHousesBatch(@RequestBody Map<String, String> params, HttpServletRequest request) {
		User user = null;
		user = getUserByBess(request);
		params.put("landlordId", user.getUserId());
		PageInfo<DepositEntity> result =new PageInfo<DepositEntity>();
		result=iDepositService.queryDepositEntityBatch(params);
		return result;
	}
	/**
	 * 批量删除押金表
	 */
	@RequestMapping(value = "deleteDeposit", method = RequestMethod.POST)
	@ApiOperation(value = "删除押金表", notes = "删除押金表",httpMethod = "POST", response = void.class)
	public void deleteRentingHouse(@RequestBody List<DepositEntity> params) {		
		if(params!=null&&params.size()>0){
			iDepositService.deleteDepositBatch(params);
		}
	}	
}
