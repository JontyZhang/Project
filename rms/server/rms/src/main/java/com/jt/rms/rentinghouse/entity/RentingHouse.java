package com.jt.rms.rentinghouse.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jt.rms.syscommon.entity.FileDto;

public class RentingHouse implements Serializable{
	private static final long serialVersionUID = 1L;
	//房屋id
	private String rentingHouseId;
	//房屋面积 不允许为空
	private String area;
	//房屋地址 不允许为空
	private String address;	
	//省市县
	private String provienceId;
	private String cityId;
	private String countryId;
	//房屋小区
	private String community;
	//房屋类型
	private String houseStyle;
	//房屋朝向
	private String houseFace;
	//出租类型
	private String rentingStyle;
	//配套设施
	private String supportingFacilities;
	//租金
	private double rent;
	//出租标题
	private String title;
	//附带照片
	private String images;
	/**
	 * 文件信息
	 */
	private List<FileDto> FileDtoList;
	//出租者
	private String landlord;
	//电话号码 不允许为空
	private String phoneNumber;
	//创建时间
	private String createTime;
	//更新时间
	private String updateTime;
	//租赁状况
	private String rantingState;
	//备注
	private String remark;
	public String getRentingHouseId() {
		return rentingHouseId;
	}
	public void setRentingHouseId(String rentingHouseId) {
		this.rentingHouseId = rentingHouseId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getHouseStyle() {
		return houseStyle;
	}
	public void setHouseStyle(String houseStyle) {
		this.houseStyle = houseStyle;
	}
	public String getHouseFace() {
		return houseFace;
	}
	public void setHouseFace(String houseFace) {
		this.houseFace = houseFace;
	}
	public String getRentingStyle() {
		return rentingStyle;
	}
	public void setRentingStyle(String rentingStyle) {
		this.rentingStyle = rentingStyle;
	}
	public String getSupportingFacilities() {
		return supportingFacilities;
	}
	public void setSupportingFacilities(String supportingFacilities) {
		this.supportingFacilities = supportingFacilities;
	}

	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getLandlord() {
		return landlord;
	}
	public void setLandlord(String landlord) {
		this.landlord = landlord;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getRantingState() {
		return rantingState;
	}
	public void setRantingState(String rantingState) {
		this.rantingState = rantingState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getProvienceId() {
		return provienceId;
	}
	public void setProvienceId(String provienceId) {
		this.provienceId = provienceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public List<FileDto> getFileDtoList() {
		return FileDtoList;
	}
	public void setFileDtoList(List<FileDto> fileDtoList) {
		FileDtoList = fileDtoList;
	}	


}
