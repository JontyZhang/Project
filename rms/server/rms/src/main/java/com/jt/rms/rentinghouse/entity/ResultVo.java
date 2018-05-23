package com.jt.rms.rentinghouse.entity;

import java.io.Serializable;

/**
 * 
 * @author :zjt
 * @time :2018年3月13日
 */
public class ResultVo implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 上传图片返回值，成功：0	失败：1	
	 */
	private String error;
	/**
	 * 回显图片使用的url
	 */
	private String url;
	/**
	 * 错误时的错误消息
	 */
	private String message;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
