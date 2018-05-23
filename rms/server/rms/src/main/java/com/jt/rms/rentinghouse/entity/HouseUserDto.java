package com.jt.rms.rentinghouse.entity;
/**
 * 
 * @author :zjt
 * @time :2018年3月22日
 */
public class HouseUserDto {
	private String id;
	private String userId;
	private String rengtingHouseId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRengtingHouseId() {
		return rengtingHouseId;
	}
	public void setRengtingHouseId(String rengtingHouseId) {
		this.rengtingHouseId = rengtingHouseId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
