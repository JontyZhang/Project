package com.jt.rms.MsgManager.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.rms.MsgManager.dao.MsgManagerMapper;
import com.jt.rms.MsgManager.entity.MsgManagerEntity;
import com.jt.rms.MsgManager.service.IMsgManagerService;
import com.jt.rms.common.util.Untils;
import com.jt.rms.rentinghouse.entity.RentingHouse;

/**
 * 
 * @author :zjt
 * @time :2018年5月11日
 */
@Service("MsgManagementService")
public class MsgManagementServiceImpl implements IMsgManagerService {
	@Resource
	MsgManagerMapper msgManagerMapper;
	@Override
	public void addMsg(MsgManagerEntity params) {
		params.setId(Untils.getUUID("Msg_"));
		msgManagerMapper.addMsg(params);
	}

	@Override
	public PageInfo<MsgManagerEntity> queryMsgBatch(Map<String, String> params) {
		PageHelper.startPage(Integer.parseInt(params.get("pageNum")),Integer.parseInt(params.get("pageSize")));
		List<MsgManagerEntity> resultList = msgManagerMapper.queryMsgBatch(params);
		PageInfo<MsgManagerEntity> result = new PageInfo<MsgManagerEntity>(resultList);
		return result;
	}

	@Override
	public MsgManagerEntity queryMsgDetail(MsgManagerEntity params) {
		MsgManagerEntity result = null;
		result= msgManagerMapper.queryMsgDetail(params);
		return result;
	}

	@Override
	public void deleteMsgBatch(List<MsgManagerEntity> params) {
		msgManagerMapper.deleteMsgBatch(params);
	}

}
