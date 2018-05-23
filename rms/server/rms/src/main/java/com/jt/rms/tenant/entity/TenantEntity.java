package com.jt.rms.tenant.entity;

import java.io.Serializable;

/**
 * 
 * @author :zjt
 * @time :2018年3月23日
 */
public class TenantEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键ID
	private String tenantId;
	//客户名字
	private String tenantName;
	//客户性别
	private String tenantSex;
	//客户电话号码
	private String tenantPhoneNum;
	//客户身份证号
	private String tenantCID;
	//房东主键ID
	private String landlordId;
	//房屋主键ID
	private String rentingHouseId;
	//合同主键ID
	private String contractId;
	//备注
	private String remark;
	//创建人
	private String createBy;
	//创建时间
	private String createTime;
	//更新人
	private String updateBy;
	//更新时间
	private String updateTime;
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public String getTenantSex() {
		return tenantSex;
	}
	public void setTenantSex(String tenantSex) {
		this.tenantSex = tenantSex;
	}
	public String getTenantPhoneNum() {
		return tenantPhoneNum;
	}
	public void setTenantPhoneNum(String tenantPhoneNum) {
		this.tenantPhoneNum = tenantPhoneNum;
	}
	public String getTenantCID() {
		return tenantCID;
	}
	public void setTenantCID(String tenantCID) {
		this.tenantCID = tenantCID;
	}
	public String getLandlordId() {
		return landlordId;
	}
	public void setLandlordId(String landlordId) {
		this.landlordId = landlordId;
	}
	public String getRentingHouseId() {
		return rentingHouseId;
	}
	public void setRentingHouseId(String rentingHouseId) {
		this.rentingHouseId = rentingHouseId;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
