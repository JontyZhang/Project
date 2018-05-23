package com.jt.rms.city.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author :zjt
 * @time :2018年3月13日
 */
public class TreeDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String value;
	private String label;
	private List<TreeDto> children;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<TreeDto> getChildren() {
		return children;
	}
	public void setChildren(List<TreeDto> children) {
		this.children = children;
	}
	
}
