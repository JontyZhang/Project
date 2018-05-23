package com.jt.rms.syscommon.entity;

import java.io.Serializable;

/**
 * 
 * @author :zjt
 * @time :2018年3月19日
 */
public class DictionaryEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private String dictionaryId;
	private String typeCode;
	private String code;
	private String value;
	private String remark;
	public String getDictionaryId() {
		return dictionaryId;
	}
	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
