package com.jt.rms.deposit.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jt.rms.deposit.entity.DepositEntity;

/**
 * 
 * @author :zjt
 * @time :2018年3月20日
 */
public interface IDepositService {
	/**
	 * 添加押金单
	 */
	void addDeposit (DepositEntity params);
	/**
	 * 批量查询定金单
	 * 返回分页结果
	 */
	PageInfo<DepositEntity> queryDepositEntityBatch(Map<String,String> params);
	/**
	 * 批量删除定金单
	 */
	void deleteDepositBatch(List<DepositEntity> params);
	/**
	 * 更新押金单信息
	 */
	void updateDeposit(DepositEntity params);
}
