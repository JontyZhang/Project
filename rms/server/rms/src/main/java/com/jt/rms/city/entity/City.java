package com.jt.rms.city.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author :zjt
 * @time :2018年3月11日
 */
public class City implements Serializable{

	private static final long serialVersionUID = 1L;
	private String orgId;
	private String orgName;
	private String orgPid;
	private String cityId;
	private List<City> childrenList;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgPid() {
		return orgPid;
	}
	public void setOrgPid(String orgPid) {
		this.orgPid = orgPid;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public List<City> getchildrenList() {
		return childrenList;
	}
	public void setchildrenList(List<City> childrenList) {
		this.childrenList = childrenList;
	}
	
}
