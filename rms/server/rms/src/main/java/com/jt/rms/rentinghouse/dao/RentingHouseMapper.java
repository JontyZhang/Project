package com.jt.rms.rentinghouse.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.rms.app.entity.MsgDetails;
import com.jt.rms.app.entity.SearchConditionDto;
import com.jt.rms.rentinghouse.entity.HouseUserDto;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.user.entity.User;

/**
 * 
 * @author :zjt
 * @time :2018年3月5日
 */
public interface RentingHouseMapper {
	/**
	 * 新增租屋
	 */
	void addRentingHouse(RentingHouse rentingHouse);
	/**
	 * 分页批量查询租屋信息
	 */
	List<RentingHouse> queryRentingHousesBatch(Map<String,String> params);
	/**
	 * 分页查询租屋信息
	 * 首页专用
	 */
	List<RentingHouse> queryRentingHousesBatchFront(Map<String,String> params);	
	/**
	 * 分页查询租屋信息
	 * 首页专用
	 */
	List<RentingHouse> queryRentingHousesBatchFrontNew(SearchConditionDto params);
	/**
	 * 查询租屋具体信息
	 * 首页专用
	 */
	MsgDetails queryHouseMsgDetailFront(Map<String,String> params);
	/**
	 * 删除租屋信息
	 */
	int deleteRentingHousesBatch(List<RentingHouse> params);
	/**
	 * 修改租屋信息
	 */
	void updateRentingHouse(RentingHouse rentingHouse);
	/**
	 * 查询租屋具体数据
	 */
	RentingHouse queryRentingHouse(RentingHouse rentingHouse);
	/**
	 * 建立房子-房东信息连接
	 */
	void addRentingHouseUserConn(HouseUserDto params);
	/**
	 * 删除房子-房东连接
	 */
	void deleteRentingHouseUserConn(List<String> params);
	/**
	 * 查询是否存在用户房子连接
	 */
	Integer queryCountRentingHouseUserConn(User user);
}
