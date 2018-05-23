package com.jt.rms.pression.service;

import java.util.List;
import java.util.Map;

import com.jt.rms.pression.entity.Premission;

/**
 * 
 * @author :zjt
 * @time :2018年5月14日
 */
public interface IPressionService {
	/**
	 * 新增权限
	 */
	void addPremission (Premission params);
	/**
	 * 查看权限列表
	 */
	void queryPremissionBatch (Map<String,String> params);
	/**
	 * 修改权限
	 */
	void updatePremission (Premission params);
	/**
	 * 删除权限
	 */
	void deletePremission(List<String> params);
}
