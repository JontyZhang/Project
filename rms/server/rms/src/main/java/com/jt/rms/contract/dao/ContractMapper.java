package com.jt.rms.contract.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.PageInfo;
import com.jt.rms.contract.entity.ContractEntity;

/**
 * 
 * @author :zjt
 * @time :2018年3月23日
 */
public interface ContractMapper {
	/**
	 * 新增合同
	 */
	void addContract (ContractEntity params);
	/**
	 * 分页查询合同订单
	 * @param params
	 * @return
	 */
	List<ContractEntity> queryContractBatch(Map<String,String> params);
	/**
	 * 查询合同订单详情
	 */
	ContractEntity queryContract (ContractEntity params);
	/**
	 * 根据房子ID查询房子详情
	 */
	ContractEntity queryContractByRentingHosueId(String params);
	/**
	 * 根据房子ID查询已签订合同数量
	 */
	int countContractByRentingHouse(String params);
	/**
	 * 批量删除合同
	 */
	void deleteContractBatch(List<ContractEntity> params);
	
	/**
	 * 更改合同信息
	 */
	void updateContract(ContractEntity params);
}
