package com.jt.rms.rent.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.jt.rms.base.BaseController;
import com.jt.rms.rent.entity.RentShowVo;
import com.jt.rms.rent.entity.Rents;
import com.jt.rms.rent.service.RentService;
import com.jt.rms.user.entity.User;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author :zjt
 * @time :2018年3月29日
 */
@Controller
@RequestMapping("Rent")
public class RentController extends BaseController{
	@Resource
	RentService rentService;
	/**
	 * 批量查询租金列表
	 */
	@RequestMapping(value="queryRentBatch", method = RequestMethod.POST)
	@ApiOperation(value = "批量查询租金", notes = "批量查询租金",httpMethod = "POST", response = List.class)
	PageInfo<RentShowVo> queryRentsBatch(@RequestBody Map<String,String> params,HttpServletRequest request) {
		//提取登陆用户信息
		User user = getUserByBess(request);
		params.put("userId", user.getUserId());
		PageInfo<RentShowVo> resultList = rentService.queryRentBatch(params);
		return resultList;
	}
	/**
	 * 根据租金ID查询租金详情
	 */
	@RequestMapping(value="queryRentById", method = RequestMethod.POST)
	@ApiOperation(value = "查询租金详情", notes = "查询租金详情",httpMethod = "POST", response = Rents.class)
	RentShowVo queryRentById(@RequestBody Rents params){
		RentShowVo result = null;
		result = rentService.queryRentById(params.getRentId());
		return result;
	}
	/**
	 * 新增租金
	 */
	void addRents (Rents params){
		
	}
	/**
	 * 批量删除租金
	 */
	@RequestMapping(value="deleteRents", method = RequestMethod.POST)
	@ApiOperation(value = "批量删除租金", notes = "批量删除租金",httpMethod = "POST", response = void.class)
	void deleteRents(@RequestBody List<Rents> params){
		rentService.deleteRents(params);
	}
	/**
	 * 更新租金信息
	 */
	@RequestMapping(value="updateRents", method = RequestMethod.POST)
	@ApiOperation(value = "更新租金信息", notes = "更新租金信息",httpMethod = "POST", response = void.class)
	void updateRents(@RequestBody Rents params){
		rentService.updateRents(params);
	}
}
