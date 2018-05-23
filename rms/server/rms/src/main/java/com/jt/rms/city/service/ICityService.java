package com.jt.rms.city.service;

import java.util.List;

import com.jt.rms.city.entity.City;
import com.jt.rms.city.entity.TreeDto;

/**
 * 
 * @author :zjt
 * @time :2018年3月11日
 */
public interface ICityService {
	/**
	 * 根据Pid查询地区信息
	 */
	List<City> queryCityByPid(City params);
    /**
     * 递归查找所有
     * 
     * @return
     */
	public List<City> findAllCity();
	/*
	 * 
	 * 树
	 */
	public List<TreeDto> getTree();
	/**
	 * 根据ORG_ID查出城市信息
	 */
	public City queryCityByOrgId(City params);
	/**
	 * 去除广东节点
	 */
	public List<TreeDto> getTreeForFront();
}
