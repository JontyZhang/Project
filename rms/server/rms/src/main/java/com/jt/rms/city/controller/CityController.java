package com.jt.rms.city.controller;
/**
 * 
 * @author :zjt
 * @time :2018年3月11日
 */

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.rms.city.entity.City;
import com.jt.rms.city.entity.TreeDto;
import com.jt.rms.city.service.ICityService;

import io.swagger.annotations.ApiOperation;
@Controller
@RequestMapping("cityManager")
public class CityController {
	
	@Resource
	ICityService cityService;
	/**
	 * 根据父Id查询地区
	 */
	@RequestMapping(value = "queryCityByPid",method = RequestMethod.POST)
	@ApiOperation(value = "根据Pid查询地区信息", notes = "根据Pid查询地区信息",httpMethod = "POST", response = List.class)
	public List<City> queryCityByPid(@RequestBody City params){
		return cityService.queryCityByPid(params);
	}
	/**
	 * 递归查询所有
	 */
//	@RequestMapping(value = "queryAllCity",method = RequestMethod.POST)
//	@ApiOperation(value = "递归查询所有城市信息", notes = "递归查询所有城市信息",httpMethod = "POST", response = List.class)
//	public List<City> queryAllCity(){
//		List<City> resultList=cityService.findAllCity();
//		return resultList;
//	}
	/**
	 * 递归查询数据2
	 */
	@RequestMapping(value = "queryAllCity",method = RequestMethod.POST)
	@ApiOperation(value = "递归查询所有城市信息", notes = "递归查询所有城市信息",httpMethod = "POST", response = List.class)
	public List<TreeDto> queryAllCity(){
		List<TreeDto> resultList=cityService.getTree();
		return resultList;
	}
	/**
	 * 根据编码查询出城市名字
	 */
	@RequestMapping(value="queryOrgNameByOrgId",method =RequestMethod.POST)
	@ApiOperation(value = "递归查询所有城市信息", notes = "递归查询所有城市信息",httpMethod = "POST", response = City.class)
	public City queryOrgNameByOrgId(@RequestBody City params){
		
		return cityService.queryCityByOrgId(params);
	}
	/**
	 * 递归查出数据3
	 */
	@RequestMapping(value = "queryAllCityForFront",method = RequestMethod.POST)
	@ApiOperation(value = "递归查询所有城市信息", notes = "递归查询所有城市信息",httpMethod = "POST", response = List.class)
	public List<TreeDto> queryAllCityForFront(){
		List<TreeDto> resultList=cityService.getTreeForFront();
		
		return resultList;
	}
}
