package com.jt.rms.syscommon.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.syscommon.entity.DictionaryEntity;

/**
 * 
 * @author :zjt
 * @time :2018年3月19日
 */
public interface DictionaryService {
	/**
	 * 新增字典内容
	 */
	void addDirctionaryContent(DictionaryEntity params);
	/**
	 * 批量查询字典表
	 */
	 PageInfo<DictionaryEntity> queryDictionaryBatch(Map<String, String> params);
	 /**
	  * 删除字典表内容
	  */
	 void deleteDictionaryContent(List<DictionaryEntity> params);
	 /**
	  * 根据字典TypeCode和Code翻译字典内容
	  * @return String
	  */
	 DictionaryEntity queryDictionary(DictionaryEntity params);

}
