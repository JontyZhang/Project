package com.jt.rms.rent.service;
/**
 * 
 * @author :zjt
 * @time :2018年3月30日
 */

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jt.rms.rent.entity.RentShowVo;
import com.jt.rms.rent.entity.Rents;

public interface RentService {
	/**
	 * 分页查询租金信息
	 */
	PageInfo<RentShowVo> queryRentBatch(Map<String,String> params);
	/**
	 * 通过租金id查询租金基本信息
	 */
	RentShowVo queryRentById (String params);
	/**
	 * 更改租金单状态
	 */
	void updateRents(Rents params);
	/**
	 * 批量删除租金单信息
	 */
	void deleteRents (List<Rents> params);
}
