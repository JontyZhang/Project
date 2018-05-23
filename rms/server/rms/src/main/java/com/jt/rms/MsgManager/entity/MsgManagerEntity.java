package com.jt.rms.MsgManager.entity;
/**
 * 
 * @author :zjt
 * @time :2018年5月8日
 */
public class MsgManagerEntity {
	private String id;
	private String touristNum;
	private String msg;
	private String msgState;
	private String userId;
	private String createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTouristNum() {
		return touristNum;
	}
	public void setTouristNum(String touristNum) {
		this.touristNum = touristNum;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgState() {
		return msgState;
	}
	public void setMsgState(String msgState) {
		this.msgState = msgState;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
