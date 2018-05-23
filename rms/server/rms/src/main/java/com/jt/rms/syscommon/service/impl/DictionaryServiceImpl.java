package com.jt.rms.syscommon.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.rms.common.constant.Code;
import com.jt.rms.common.util.exception.ServiceException;
import com.jt.rms.common.util.isnotnull.StringUtils;
import com.jt.rms.common.util.Untils;
import com.jt.rms.syscommon.dao.DictionaryMapper;
import com.jt.rms.syscommon.entity.DictionaryEntity;
import com.jt.rms.syscommon.service.DictionaryService;

/**
 * 
 * @author :zjt
 * @time :2018年3月19日
 */
@Service("DictionaryService")
public class DictionaryServiceImpl implements DictionaryService {
	@Resource
	DictionaryMapper dictionary;
	@Override
	public void addDirctionaryContent(DictionaryEntity params) {
		//检查传入参数
		if(StringUtils.isNotEmpty(params)){
			checkMsg(params,"add");
		}else {
			throw new ServiceException(Code.EMPTY_PARAME.getErrorCode(),Code.EMPTY_PARAME.getErrorMessage());
		}
		
		//检查是否存在重复typeCode+Code
		int i= dictionary.selectDictionaryByTypeCode(params);
		if(i>0){
			throw new ServiceException(Code.REPEAT_PARAME.getErrorCode(),Code.REPEAT_PARAME.getErrorMessage());
		}
		//创建dictionaryId
		params.setDictionaryId(Untils.getUUID("DICTIONARY_ID_"));
		dictionary.addDictionaryContent(params);
	}
	@Override
	public PageInfo<DictionaryEntity> queryDictionaryBatch(Map<String, String> params) {
		PageHelper.startPage(Integer.parseInt(params.get("pageNum")),Integer.parseInt(params.get("pageSize")));
		List<DictionaryEntity> resultList=dictionary.queryDictionaryBatch(params);
		PageInfo<DictionaryEntity> pageInfo=new PageInfo<DictionaryEntity>(resultList);
		return pageInfo;
	}
	/**
	 * 批量删除字典表内容
	 */
	@Override
	public void deleteDictionaryContent(List<DictionaryEntity> params) {
		dictionary.deleteDictionary(params);
	}
	/**
	 * 根据TypeCode和Code查询字典内容
	 */
	@Override
	public DictionaryEntity queryDictionary(DictionaryEntity params) {
		checkMsg(params,"update");
		DictionaryEntity result= new DictionaryEntity();
		//查询字典表
		result=dictionary.queryDictionary(params);
		return result;
	}
	/**
	 * 校验数据
	 * @param params
	 * @param signel
	 */
	private static void checkMsg(DictionaryEntity params,String signel){
		if(StringUtils.isEmpty(params.getTypeCode())){
			throw new ServiceException(Code.EMPTY_TYPECODE.getErrorCode(), Code.EMPTY_TYPECODE.getErrorMessage());
		}
		if(StringUtils.isEmpty(params.getCode())){
			throw new ServiceException(Code.EPMTY_CODE.getErrorCode(), Code.EPMTY_CODE.getErrorMessage());
		}
		if(signel.equals("add")){
			if(params.getValue()==null&&!params.getValue().equals("")){
				throw new ServiceException(Code.EMPTY_PARAME.getErrorCode(),Code.EMPTY_PARAME.getErrorMessage());
		}
		
	}
	}
}
