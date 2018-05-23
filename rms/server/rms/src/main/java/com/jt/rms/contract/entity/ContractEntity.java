package com.jt.rms.contract.entity;

import java.io.Serializable;
import java.util.Date;

import com.jt.rms.deposit.entity.DepositEntity;
import com.jt.rms.rent.entity.Rents;

/**
 * 
 * @author :zjt
 * @time :2018年3月20日
 */
public class ContractEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	//主键
	private String contractId;
	//合同编号
	private String contractNo;
	//合同类型
	private String contractStyle;
	//房东
	private String landlordName;
	//房东电话
	private String landlordTel;
	//房东身份证号码
	private String landlordCID;
	//租户
	private String tenantName;
	//房东电话
	private String tenantTel;
	//租户身份证号码
	private String tenantCID;
	//租金
	private double rent;
	//租金详细表ID
	private String rentId;
	//押金ID
	private String depositId;
	//租期开始日期
	private Date rentFromDate;
	//租期至日期
	private Date rentToDate;
	//租期时间长短
	private String rentDate;
	//支付方式
	private String payStyle;
	//支付日期
	private String payDate;
	//合同状态
	private String contractState;
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
	//房子ID
	private String rentingHouseId;
	//押金单
	private DepositEntity deposit;
	//租金详细单
	private Rents rents;
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getContractStyle() {
		return contractStyle;
	}
	public void setContractStyle(String contractStyle) {
		this.contractStyle = contractStyle;
	}
	public String getLandlordName() {
		return landlordName;
	}
	public void setLandlordName(String landlordName) {
		this.landlordName = landlordName;
	}
	public String getLandlordTel() {
		return landlordTel;
	}
	public void setLandlordTel(String landlordTel) {
		this.landlordTel = landlordTel;
	}
	public String getLandlordCID() {
		return landlordCID;
	}
	public void setLandlordCID(String landlordCID) {
		this.landlordCID = landlordCID;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public String getTenantTel() {
		return tenantTel;
	}
	public void setTenantTel(String tenantTel) {
		this.tenantTel = tenantTel;
	}
	public String getTenantCID() {
		return tenantCID;
	}

	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public void setTenantCID(String tenantCID) {
		this.tenantCID = tenantCID;
	}
	public void setRent(Integer rent) {
		this.rent = rent;
	}
	public String getRentId() {
		return rentId;
	}
	public void setRentId(String rentId) {
		this.rentId = rentId;
	}
	public String getDepositId() {
		return depositId;
	}
	public void setDepositId(String depositId) {
		this.depositId = depositId;
	}
	public Date getRentFromDate() {
		return rentFromDate;
	}
	public void setRentFromDate(Date rentFromDate) {
		this.rentFromDate = rentFromDate;
	}
	public Date getRentToDate() {
		return rentToDate;
	}
	public void setRentToDate(Date rentToDate) {
		this.rentToDate = rentToDate;
	}
	public String getRentDate() {
		return rentDate;
	}
	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}
	public String getPayStyle() {
		return payStyle;
	}
	public void setPayStyle(String payStyle) {
		this.payStyle = payStyle;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getContractState() {
		return contractState;
	}
	public void setContractState(String contractState) {
		this.contractState = contractState;
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
	public String getRentingHouseId() {
		return rentingHouseId;
	}
	public void setRentingHouseId(String rentingHouseId) {
		this.rentingHouseId = rentingHouseId;
	}
	public DepositEntity getDeposit() {
		return deposit;
	}
	public void setDeposit(DepositEntity deposit) {
		this.deposit = deposit;
	}
	public Rents getRents() {
		return rents;
	}
	public void setRents(Rents rents) {
		this.rents = rents;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
