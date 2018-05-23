package com.jt.rms.rent.entity;

import java.io.Serializable;

import com.jt.rms.tenant.entity.TenantEntity;

/**
 * 
 * @author :zjt
 * @time :2018年3月30日
 * 租金列表页面
 */
public class RentShowVo  implements Serializable{

	private static final long serialVersionUID = 1L;
	//主键ID
	private String rentId;
	//房租
	private String houseRent;
	//水费
	private String waterFee;
	//电费
	private String electricFee;
	//网费
	private String netFee;
	//燃气费
	private String gasFee;
	//物业管理费
	private String propertyFee;
	//房东
	private String landlordId;
	//租客ID
	private String tenantId;
	//状态
	private String rentsState;
	//备注
	private String remark;
	//租户信息
	private TenantEntity tenant;
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
	public String getHouseRent() {
		return houseRent;
	}
	public void setHouseRent(String houseRent) {
		this.houseRent = houseRent;
	}
	public String getWaterFee() {
		return waterFee;
	}
	public void setWaterFee(String waterFee) {
		this.waterFee = waterFee;
	}
	public String getElectricFee() {
		return electricFee;
	}
	public void setElectricFee(String electricFee) {
		this.electricFee = electricFee;
	}
	public String getNetFee() {
		return netFee;
	}
	public void setNetFee(String netFee) {
		this.netFee = netFee;
	}
	public String getGasFee() {
		return gasFee;
	}
	public void setGasFee(String gasFee) {
		this.gasFee = gasFee;
	}
	public String getPropertyFee() {
		return propertyFee;
	}
	public void setPropertyFee(String propertyFee) {
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
	public TenantEntity getTenant() {
		return tenant;
	}
	public void setTenant(TenantEntity tenant) {
		this.tenant = tenant;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
}
