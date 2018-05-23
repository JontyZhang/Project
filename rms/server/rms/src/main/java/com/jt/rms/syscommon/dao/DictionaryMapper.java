package com.jt.rms.syscommon.dao;

import java.util.List;
import java.util.Map;

import com.jt.rms.syscommon.entity.DictionaryEntity;

/**
 * 
 * @author :zjt
 * @time :2018年3月19日
 */
public interface DictionaryMapper {
	/**
	 * 创建字典内容
	 * @param params
	 */
	void addDictionaryContent(DictionaryEntity params);
	/**
	 * 批量查询字典表内容
	 * @param params
	 * @return
	 */
	List<DictionaryEntity> queryDictionaryBatch(Map<String,String> params);
	/**
	 * 删除字典表内容
	 * @param params
	 */
	void deleteDictionary (List<DictionaryEntity> params);
	/**
	 * 查询字典code是否存在
	 */
	Integer selectDictionaryByTypeCode(DictionaryEntity params);
	/**
	 * 根据字典typeCode和Code查询字典内容
	 */
	DictionaryEntity queryDictionary(DictionaryEntity params);
}
