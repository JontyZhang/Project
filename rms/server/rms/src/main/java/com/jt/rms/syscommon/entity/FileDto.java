package com.jt.rms.syscommon.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author :zjt
 * @time :2018年3月15日
 */
public class FileDto implements Serializable{

	private static final long serialVersionUID = -4934968048205630354L;
	/**
	 * 文件名字
	 */
	private String fileName;
	/**
	 * FILES 文件
	 *
	 */
	/**
	 * 文件后缀名
	 */
	private String extName;
	/**
	 * 文件流
	 */
	private byte[] files;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFiles() {
		return files;
	}
	public void setFiles(byte[] files) {
		this.files = files;
	}
	public String getExtName() {
		return extName;
	}
	public void setExtName(String extName) {
		this.extName = extName;
	}	
}
