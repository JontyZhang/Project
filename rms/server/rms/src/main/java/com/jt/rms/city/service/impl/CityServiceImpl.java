package com.jt.rms.city.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jt.rms.city.dao.CityMapper;
import com.jt.rms.city.entity.City;
import com.jt.rms.city.entity.TreeDto;
import com.jt.rms.city.service.ICityService;
import com.jt.rms.common.constant.Code;
import com.jt.rms.common.util.exception.ServiceException;

/**
 * 
 * @author :zjt
 * @time :2018年3月11日
 */
@Service("ICityService")
public class CityServiceImpl implements ICityService {
	
	@Resource
	CityMapper cityMapper;
	@Override
	public List<City> queryCityByPid(City params) {
		if(params.getOrgPid()==null||params.getOrgPid().equals("")){
			throw new ServiceException("","");
		}
		return cityMapper.queryCityByPid(params);
	}
	@Override
	public List<City> findAllCity() {
		List<City> resultList=cityMapper.findAllCity();
		return resultList;
	}
	@Override
	public List<TreeDto> getTree() {
		List<TreeDto> resultList=cityMapper.getTreeCity();
		List<TreeDto> parentList = resultList.get(0).getChildren(); 
		for (TreeDto treeDto : parentList) {
			List<TreeDto> list = treeDto.getChildren();
			if(list.size() != 0){
				getChildrenList(list);
			}else{
				treeDto.setChildren(null);
			}
		}
		return resultList;
	}
	
	public void getChildrenList(List<TreeDto> list){
		for (TreeDto treeDto : list) {
			if(treeDto.getChildren().size() == 0){
				treeDto.setChildren(null);
			}
		}
	}
	@Override
	public City queryCityByOrgId(City params) {
		City result= null;
		if(params!=null){
			result= cityMapper.queryCityByOrgId(params);
		}else{
			throw new ServiceException("","");
		}
		return result;
	}
	@Override
	public List<TreeDto> getTreeForFront() {
		List<TreeDto> resultList=cityMapper.getTreeCity();
		List<TreeDto> parentList = resultList.get(0).getChildren(); 
		for (TreeDto treeDto : parentList) {
			List<TreeDto> list = treeDto.getChildren();
			if(list.size() != 0){
				getChildrenList(list);
			}else{
				treeDto.setChildren(null);
			}
		}
		//二次处理
		List<TreeDto> result =new ArrayList<TreeDto>();
		for(int i =0 ;i<resultList.size();i++){
			result=resultList.get(i).getChildren();
		}
		return result;
	}
	
}
