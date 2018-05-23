package com.jt.rms.city.dao;

import java.util.List;

import com.jt.rms.city.entity.City;
import com.jt.rms.city.entity.TreeDto;

/**
 * 
 * @author :zjt
 * @time :2018年3月11日
 */
public interface CityMapper {
	/**
	 * 根据pid查询
	 * @param params
	 * @return
	 */
	List<City> queryCityByPid(City params);
	/**
	 * 查询整个城市信息，树
	 */
	List<City> findAllCity();
	
	List<City> findCityByPid(String orgId);
	
	List<TreeDto> getTreeCity();
	
	List<TreeDto> getTreeCityByPid(String orgId);
	/**
	 * 根据ORG_ID查询城市信息
	 */
	City queryCityByOrgId(City params);
}
