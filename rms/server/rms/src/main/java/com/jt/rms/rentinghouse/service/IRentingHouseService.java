package com.jt.rms.rentinghouse.service;


import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.jt.rms.app.entity.MsgDetails;
import com.jt.rms.app.entity.SearchConditionDto;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.rentinghouse.entity.ResultVo;
import com.jt.rms.user.entity.User;

/**
 * 
 * @author :zjt
 * @time :2018年3月5日
 */

public interface IRentingHouseService {
	/**
	 * 添加房源
	 */
	ResultVo addRentingHouse(RentingHouse rentingHouse,User user);
	/**
	 * 批量查询房源
	 */
	PageInfo<RentingHouse> queryRentingHouseBatch(Map<String,String> params);
	/**
	 * 批量查询房源,前台
	 */
	PageInfo<RentingHouse> queryRentingHouseBatchFront(Map<String,String> params);
	/**
	 * 删除房源
	 */
	void deleteRentingHouse(List<RentingHouse> params,User user);
	
	/**
	 * 更新房源信息
	 * 
	 */
	void updateRentingHouse(RentingHouse rentingHouse,User user);
	/**
	 * 查询房源具体信息
	 */
	RentingHouse queryRentingHouse(RentingHouse rentingHouse);
	/**
	 * 查询房源相关信息
	 */
	MsgDetails queryRentingHouseFront(Map<String,String> params);
}
