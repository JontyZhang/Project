package com.jt.rms.rent.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.rms.rent.entity.RentShowVo;
import com.jt.rms.rent.entity.Rents;

/**
 * 
 * @author :zjt
 * @time :2018年3月29日
 */
public interface RentMapper {
	//新增租金
	void addRent (Rents params);
	//批量删除租金详情
	void deleteRentBatch (List<Rents> params) ;
	//查询租金详情列表
	List<RentShowVo> queryRentBatch(Map<String,String> params);
	//查询租金详情
	RentShowVo queryRentById (@Param("rentId") String rentId);
	//更新租金单详情
	void updateRents (Rents params);
}
