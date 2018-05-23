package com.jt.rms.contract.controller;

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
import com.jt.rms.contract.entity.ContractEntity;
import com.jt.rms.contract.service.ContractService;
import com.jt.rms.rentinghouse.controller.RentingHouseController;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.user.entity.User;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author :zjt
 * @time :2018年3月23日
 */
@Controller
@RequestMapping("contract")
public class ContractController extends BaseController{
	private static final Log logger = LogFactory.getLog(ContractController.class);
	@Resource
	ContractService contractService;
	/**
	 * 新增合同
	 */
	@RequestMapping(value="addContract" , method = RequestMethod.POST)
	@ApiOperation(value = "添加合同", notes = "添加合同",httpMethod = "POST", response = void.class)
	public void addContract(@RequestBody ContractEntity params,HttpServletRequest request){
		//从缓存中取出用户数据
		User user = getUserByBess(request);
		contractService.addContract(params, user);
	}
	/**
	 * 批量查询合同信息
	 */
	@RequestMapping(value="queryContractBatch" , method = RequestMethod.POST)
	@ApiOperation(value = "批量查询合同", notes = "批量查询合同",httpMethod = "POST", response = List.class)
	public PageInfo<ContractEntity> queryContractBatch(@RequestBody Map<String,String> params,HttpServletRequest request){
		//从缓存中取出用户数据
		User user = getUserByBess(request);
		params.put("userId", user.getUserId());
		PageInfo<ContractEntity> resultList =new PageInfo<ContractEntity>();
		resultList = contractService.queryContractBatch(params);
		return resultList;
	}
	/**
	 * 根据房子ID查询合同详情
	 */
	@RequestMapping(value="queryContractByRentingHouseId" , method = RequestMethod.POST)
	@ApiOperation(value = "通过房子ID查询合同", notes = "通过房子ID查询合同",httpMethod = "POST", response = ContractEntity.class)
	public ContractEntity queryContractByRentingHouseId(@RequestBody String params,HttpServletRequest request){
		ContractEntity result = contractService.queryContractByRentingHouseId(params);
		return result;
	}	

	/**
	 * 根据合同ID查询合同详情
	 */
	@RequestMapping(value="queryContractByContractId" , method = RequestMethod.POST)
	@ApiOperation(value = "通过合同ID查询合同", notes = "通过合同ID查询合同",httpMethod = "POST", response = ContractEntity.class)
	public ContractEntity queryContractByContractId(@RequestBody ContractEntity params,HttpServletRequest request){
		ContractEntity result = contractService.queryContract(params);
		return result;
	}
	/**
	 * 更新合同信息
	 */
	@RequestMapping(value = "updateContract" , method = RequestMethod.POST)
	@ApiOperation(value = "更新合同信息" ,notes ="更新合同信息",httpMethod = "POST", response = void.class)
	public void updateContract (ContractEntity params,HttpServletRequest request){
		//从缓存中取出用户数据
		User user = getUserByBess(request);
		contractService.updateContract(params, user);
	}
	/**
	 * 批量删除合同信息
	 */
	@RequestMapping(value="deleteContractById" , method = RequestMethod.POST)
	@ApiOperation(value = "通过合同ID删除合同", notes = "通过合同ID删除合同",httpMethod = "POST", response = ContractEntity.class)
	public void deleteContractById(@RequestBody List<ContractEntity> params,HttpServletRequest request){
		//从缓存中取出用户数据
		User user = getUserByBess(request);
		contractService.deleteContractBatch(params);
	}	
	/**
	 * 生成合同
	 */
	@RequestMapping(value="createContactWord" , method = RequestMethod.POST)
	@ApiOperation(value="生成word合同",notes = "生成word合同" ,httpMethod = "POST" ,response = void.class)
	public void createContactWord(@RequestBody ContractEntity params,HttpServletRequest request){
		User user = getUserByBess(request);
		contractService.creatContractToWord(params, user);
	}
}
