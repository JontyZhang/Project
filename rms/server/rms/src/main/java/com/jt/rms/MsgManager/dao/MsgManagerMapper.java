package com.jt.rms.MsgManager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.rms.MsgManager.entity.MsgManagerEntity;

/**
 * 
 * @author :zjt
 * @time :2018年5月10日
 */
public interface MsgManagerMapper {
	/**
	 * 新增留言信息
	 */
	void addMsg(MsgManagerEntity params); 
	/**
	 * 分页查询留言信息
	 * @param params
	 * @return
	 */
	List<MsgManagerEntity> queryMsgBatch (Map<String,String> params);
	/**
	 * 批量删除留言详情
	 * @param params
	 */
	void deleteMsgBatch(List<MsgManagerEntity> params);
	/**
	 * 查看留言详情
	 */
	MsgManagerEntity queryMsgDetail (MsgManagerEntity params);
	
}
