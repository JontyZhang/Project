package com.jt.rms.pression.entity;

import java.io.Serializable;

/**
 * 
 * @author :zjt
 * @time :2018年3月13日
 */
public class Premission implements Serializable{

	private static final long serialVersionUID = 1L;
	private String premissionId;
	private String premissionName;
	private String createBy;
	private String createTime;
	private String updateBy;
	private String updateTime;
	private String remark;
	public String getPremissionId() {
		return premissionId;
	}
	public void setPremissionId(String premissionId) {
		this.premissionId = premissionId;
	}
	public String getPremissionName() {
		return premissionName;
	}
	public void setPremissionName(String premissionName) {
		this.premissionName = premissionName;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
