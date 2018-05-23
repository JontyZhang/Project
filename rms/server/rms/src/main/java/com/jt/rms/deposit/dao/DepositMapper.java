package com.jt.rms.deposit.dao;

import java.util.List;
import java.util.Map;

import com.jt.rms.deposit.entity.DepositEntity;

/**
 * 
 * @author :zjt
 * @time :2018年3月20日
 */
public interface DepositMapper {
	/**
	 * 新增押金单
	 */
	void addDeposit (DepositEntity params);
	/**
	 * 批量查询押金表
	 */
	List<DepositEntity> queryDepositBatch(Map<String,String> params);
	/**
	 * 批量删除押金表
	 */
	void deleteDepositBatch(List<DepositEntity> params);
}
