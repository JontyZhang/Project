package com.jt.rms.deposit.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.jt.rms.common.constant.Code;
import com.jt.rms.common.util.exception.ServiceException;
import com.jt.rms.common.util.Untils;
import com.jt.rms.deposit.dao.DepositMapper;
import com.jt.rms.deposit.entity.DepositEntity;
import com.jt.rms.deposit.service.IDepositService;
import com.jt.rms.rentinghouse.entity.RentingHouse;

/**
 * 
 * @author :zjt
 * @time :2018年3月20日
 */
@Service("IDepositService")
public class DepositServiceImpl implements IDepositService {
	@Resource
	DepositMapper depositMapper;
	/**
	 * 新增押金表
	 */
	@Override
	public void addDeposit(DepositEntity params) {
		if(params!=null){
			params.setDepositId(Untils.getUUID("DEPOSIT_ID_"));;
		}else{
			throw new ServiceException("","");
		}
		//执行插入
		depositMapper.addDeposit(params);		
	}
	/**
	 * 分页查询押金表
	 */
	@Override
	public PageInfo<DepositEntity> queryDepositEntityBatch(Map<String, String> params) {
		PageHelper.startPage(Integer.parseInt(params.get("pageNum")),Integer.parseInt(params.get("pageSize")));
		List<DepositEntity> resultList=depositMapper.queryDepositBatch(params);
		PageInfo<DepositEntity> pageInfo=new PageInfo<DepositEntity>(resultList);
		return pageInfo;
	}
	/**
	 * 批量删除押金表
	 */
	@Override
	public void deleteDepositBatch(List<DepositEntity> params) {
		//判断押金单所附属的合同是否仍在有效，押金单是否仍在有效，否则不允许删除
		if(params!=null&&params.size()>0){
			for (DepositEntity depositEntity : params) {
				if(depositEntity.getDepositState().equals("0")){
					throw new ServiceException(Code.ERROR_DELETE_DEPOSIT_STATE.getErrorCode(), Code.ERROR_DELETE_DEPOSIT_STATE.getErrorMessage());
				}
			}
			depositMapper.deleteDepositBatch(params);
		}else{
			throw new ServiceException("","");
		}
	}
	@Override
	public void updateDeposit(DepositEntity params) {
		// TODO Auto-generated method stub
		
	}
}
