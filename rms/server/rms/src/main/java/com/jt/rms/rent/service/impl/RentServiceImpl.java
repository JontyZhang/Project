package com.jt.rms.rent.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.rms.common.constant.Code;
import com.jt.rms.common.util.exception.ServiceException;
import com.jt.rms.rent.dao.RentMapper;
import com.jt.rms.rent.entity.RentShowVo;
import com.jt.rms.rent.entity.Rents;
import com.jt.rms.rent.service.RentService;

/**
 * 
 * @author :zjt
 * @time :2018年3月30日
 */
@Service("RentService")
public class RentServiceImpl implements RentService {
	@Resource
	RentMapper rentMapper;
	@Override
	public PageInfo<RentShowVo> queryRentBatch(Map<String, String> params) {
		PageHelper.startPage(Integer.parseInt(params.get("pageNum")),Integer.parseInt(params.get("pageSize")));
		List<RentShowVo> resultList =  rentMapper.queryRentBatch(params);
		PageInfo<RentShowVo> result = new PageInfo<RentShowVo>(resultList);
		return result;
	}

	@Override
	public RentShowVo queryRentById(String params) {
		if(params.equals("")){
			throw new ServiceException(Code.EMPTY_HOUSE_RENT_ID.getErrorCode(), Code.EMPTY_HOUSE_RENT_ID.getErrorMessage());
		}
		RentShowVo result = new RentShowVo();
		result = rentMapper.queryRentById(params);
		return result;
	}

	@Override
	public void updateRents(Rents params) {
		rentMapper.updateRents(params);
		
	}

	@Override
	public void deleteRents(List<Rents> params) {
		rentMapper.deleteRentBatch(params);
	}

}
