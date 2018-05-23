package com.jt.rms.contract.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jt.rms.contract.entity.ContractEntity;
import com.jt.rms.user.entity.User;

/**
 * 
 * @author :zjt
 * @time :2018年3月23日
 */
public interface ContractService {
	/**
	 * 新增合同
	 */
	void addContract (ContractEntity params,User user);
	/**
	 * 批量查询合同
	 */
	PageInfo<ContractEntity> queryContractBatch(Map<String,String> params) ;
	/**
	 * 根据合同UUID查询合同内容
	 */
	ContractEntity queryContract(ContractEntity params);
	/**
	 * 根据房子UUID查询合同详情
	 */
	ContractEntity queryContractByRentingHouseId(String params);
	/**
	 * 批量删除合同
	 */
	void deleteContractBatch(List<ContractEntity> params);
	/**
	 * 更新合同内容
	 */
	void updateContract(ContractEntity params,User user);
	/**
	 * 生成word文档
	 */
	void creatContractToWord(ContractEntity params,User user);
}
