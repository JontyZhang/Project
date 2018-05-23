package com.jt.rms.rent.entity;

import java.io.Serializable;

/**
 * 
 * @author :zjt
 * @time :2018年3月20日
 */
public class Rents implements Serializable{

	private static final long serialVersionUID = 1L;
	//主键ID
	private String rentId;
	//房租
	private double houseRent;
	//水费
	private double waterFee;
	//电费
	private double electricFee;
	//网费
	private double netFee;
	//燃气费
	private double gasFee;
	//物业管理费
	private double propertyFee;
	//房东
	private String landlordId;
	//租客ID
	private String tenantId;
	//状态
	private String rentsState;
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
	public String getRentId() {
		return rentId;
	}
	public void setRentId(String rentId) {
		this.rentId = rentId;
	}

	public double getHouseRent() {
		return houseRent;
	}
	public void setHouseRent(double houseRent) {
		this.houseRent = houseRent;
	}
	public double getWaterFee() {
		return waterFee;
	}
	public void setWaterFee(double waterFee) {
		this.waterFee = waterFee;
	}
	public double getElectricFee() {
		return electricFee;
	}
	public void setElectricFee(double electricFee) {
		this.electricFee = electricFee;
	}
	public double getNetFee() {
		return netFee;
	}
	public void setNetFee(double netFee) {
		this.netFee = netFee;
	}
	public double getGasFee() {
		return gasFee;
	}
	public void setGasFee(double gasFee) {
		this.gasFee = gasFee;
	}
	public double getPropertyFee() {
		return propertyFee;
	}
	public void setPropertyFee(double propertyFee) {
		this.propertyFee = propertyFee;
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
	public String getRentsState() {
		return rentsState;
	}
	public void setRentsState(String rentsState) {
		this.rentsState = rentsState;
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
