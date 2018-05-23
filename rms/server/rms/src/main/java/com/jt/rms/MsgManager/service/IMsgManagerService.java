package com.jt.rms.MsgManager.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.jt.rms.MsgManager.entity.MsgManagerEntity;

/**
 * 
 * @author :zjt
 * @time :2018年5月11日
 */
public interface IMsgManagerService {
	/**
	 * 新增留言
	 */
	void addMsg (MsgManagerEntity params);
	/**
	 * 批量查询
	 */
	PageInfo<MsgManagerEntity> queryMsgBatch (Map<String,String> params);
	/**
	 * 查看留言详情
	 */
	MsgManagerEntity queryMsgDetail (MsgManagerEntity params);
	/**
	 * 批量删除留言详情
	 */
	void deleteMsgBatch (List<MsgManagerEntity> params);
}
