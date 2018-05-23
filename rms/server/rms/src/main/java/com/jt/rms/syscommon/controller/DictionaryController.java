package com.jt.rms.syscommon.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.syscommon.entity.DictionaryEntity;
import com.jt.rms.syscommon.service.DictionaryService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author :zjt
 * @time :2018年3月19日
 */
@Controller
@RequestMapping("DictionaryController")
public class DictionaryController {
	@Resource
	DictionaryService dictionaryService;
	/**
	 * 新增字典内容
	 * @param params
	 */
	@RequestMapping(value="addDictionaryContent" , method = RequestMethod.POST)
	@ApiOperation(value = "添加字典内容", notes = "添加字典内容",httpMethod = "POST", response = Void.class)
	void addDictionaryContent(@RequestBody DictionaryEntity params){
			dictionaryService.addDirctionaryContent(params);
	}
	/**
	 *分页查询字典列表
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "queryDictionaryBatch", method = RequestMethod.POST)
	@ApiOperation(value = "批量查询字典表", notes = "批量查询字典表",httpMethod = "POST", response = PageInfo.class)
	public PageInfo<DictionaryEntity> queryDictionaryBatch(@RequestBody Map<String, String> params) {
		PageInfo<DictionaryEntity> result =new PageInfo<DictionaryEntity>();
		result=dictionaryService.queryDictionaryBatch(params);
		return result;
	}
	/**
	 * 根据字典字典code和typeCode翻译出value
	 */
	@RequestMapping(value = "queryDictionary", method = RequestMethod.POST)
	@ApiOperation(value = "查询字典表", notes = "查询字典表",httpMethod = "POST", response = PageInfo.class)
	public DictionaryEntity queryDictionaryBatch(@RequestBody DictionaryEntity params) {
		DictionaryEntity result = new DictionaryEntity();
		result=dictionaryService.queryDictionary(params);
		return result;
	}
}
