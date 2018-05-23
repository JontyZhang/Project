package com.jt.rms.payments.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author :zjt
 * @time :2018年5月14日
 */
public interface IPayMentsService {
	/**
	 * 按月份查出月收入
	 */
	List<String> queryRentTotalByMonth (Map<String,String> params);
	
	/**
	 * 按人查出月收入
	 */
	List<String> queryRentTotalByTanent (Map<String,String> params);
	
}
