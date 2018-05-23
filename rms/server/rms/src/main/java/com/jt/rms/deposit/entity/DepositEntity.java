package com.jt.rms.deposit.entity;

import java.io.Serializable;

/**
 * 
 * @author :zjt
 * @time :2018年3月20日
 */
public class DepositEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	//押金编号
	private String depositId;
	//押金数量
	private double deposit;
	//押金交付日期
	private String feeDate;
	//房东id
	private String landlordId;
	//租客
	private String tenantId;
	//押金状态
	private String depositState;
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
	public String getDepositId() {
		return depositId;
	}
	public void setDepositId(String depositId) {
		this.depositId = depositId;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public String getFeeDate() {
		return feeDate;
	}
	public void setFeeDate(String feeDate) {
		this.feeDate = feeDate;
	}
	public String getLandlordId() {
		return landlordId;
	}
	public void setLandlordId(String landlordId) {
		this.landlordId = landlordId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getDepositState() {
		return depositState;
	}
	public void setDepositState(String depositState) {
		this.depositState = depositState;
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
