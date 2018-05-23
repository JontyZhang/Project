package com.jt.rms.app.entity;
/**
 * 
 * @author :zjt
 * @time :2018年3月22日
 */
public class SearchConditionDto {
	//省ID
	private String provienceId;
	//市ID
	private String countryId;
	//区ID
	private String cityId;
	//房子类型
	private String houseStyle;
	//出租类型
	private String rentingStyle;
	//租金
	private Integer rent;
	/**
	 * 租金区域 2为大，1为小
	 */
	private Integer rent2;
	private Integer rent1;
	public String getProvienceId() {
		return provienceId;
	}
	public void setProvienceId(String provienceId) {
		this.provienceId = provienceId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getHouseStyle() {
		return houseStyle;
	}
	public void setHouseStyle(String houseStyle) {
		this.houseStyle = houseStyle;
	}
	public String getRentingStyle() {
		return rentingStyle;
	}
	public void setRentingStyle(String rentingStyle) {
		this.rentingStyle = rentingStyle;
	}
	public Integer getRent() {
		return rent;
	}
	public void setRent(Integer rent) {
		this.rent = rent;
	}
	public Integer getRent2() {
		return rent2;
	}
	public void setRent2(Integer rent2) {
		this.rent2 = rent2;
	}
	public Integer getRent1() {
		return rent1;
	}
	public void setRent1(Integer rent1) {
		this.rent1 = rent1;
	}
	
}