package com.jt.rms.rentinghouse.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileUpload;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.rms.app.entity.MsgDetails;
import com.jt.rms.app.entity.SearchConditionDto;
import com.jt.rms.city.dao.CityMapper;
import com.jt.rms.city.entity.City;
import com.jt.rms.common.constant.Code;
import com.jt.rms.common.util.exception.ServiceException;
import com.jt.rms.common.util.Untils;
import com.jt.rms.common.util.file.upload.FileUploadUtils;
import com.jt.rms.common.util.isnotnull.StringUtils;
import com.jt.rms.rentinghouse.dao.RentingHouseMapper;
import com.jt.rms.rentinghouse.entity.HouseUserDto;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.rentinghouse.entity.ResultVo;
import com.jt.rms.rentinghouse.service.IRentingHouseService;
import com.jt.rms.syscommon.dao.DictionaryMapper;
import com.jt.rms.syscommon.entity.DictionaryEntity;
import com.jt.rms.syscommon.entity.FileDto;
import com.jt.rms.tenant.entity.TenantEntity;
import com.jt.rms.user.dao.UserMapper;
import com.jt.rms.user.entity.User;

/**
 * 
 * @author :zjt
 * @time :2018年3月5日
 */
@Service("IRentingHouseService")
public class RentingHouseImpl implements IRentingHouseService {
	@Resource
	RentingHouseMapper rentingHouseMapper;
	@Resource
	CityMapper cityMapper;
	@Resource
	DictionaryMapper dictionaryMapper;
	@Resource
	UserMapper userMapper;
	//图片上传公共方法
	private FileUploadUtils fileUploadUtils;
	/**
	 * 新增房源
	 */
	@Override
	public ResultVo addRentingHouse(RentingHouse rentingHouse,User user) {
		if(StringUtils.isEmpty(user)){
			throw new ServiceException(Code.EMPTY_RENTINGHOUSE.getErrorCode(), Code.EMPTY_RENTINGHOUSE.getErrorMessage());
		}else{
			//检查该用户信息是否完整，尤其身份证是否健全
			checkUser(user);
			//校验房屋信息
			checkMsg(rentingHouse, "add");
		}
		fileUploadUtils=new FileUploadUtils();
		ResultVo result=null;
		String path="";
		if(rentingHouse!=null){
			rentingHouse.setRentingHouseId(Untils.getUUID("RENTING_HOUSE_"));
		}
		//处理图片
		//取出图片数据，图片以流形式上传到fastdf服务器，图片路径存到数据库
		List<FileDto> fileList=rentingHouse.getFileDtoList();
		//遍历存放
		for (FileDto fileDto : fileList) {
			//判断地址是否存在，已判断是否插入成功	
			String tempPath;
			try {
				tempPath = fileUploadUtils.insertPhotoToSer(fileDto);
				if(tempPath==null||tempPath.equals("")){
					result.setMessage("插入失败");
					throw new ServiceException(Code.FAIl_TO_INSERT.getErrorCode(),Code.FAIl_TO_INSERT.getErrorMessage());
				}else{
					path=tempPath;
				}
			} catch (IOException | MyException e) {
				e.printStackTrace();
			}			
		}
		rentingHouse.setImages(path);
		rentingHouseMapper.addRentingHouse(rentingHouse);
		//建立用户-房子信息连接
		HouseUserDto houseUserDto=new HouseUserDto();
		houseUserDto.setId(Untils.getUUID("ID_"));
		houseUserDto.setUserId(user.getUserId());
		houseUserDto.setRengtingHouseId(rentingHouse.getRentingHouseId());
		rentingHouseMapper.addRentingHouseUserConn(houseUserDto);
		return result;
	}
	/**
	 *批量查询房源
	 */
	@Override
	public PageInfo<RentingHouse> queryRentingHouseBatch(Map<String, String> params) {
		PageHelper.startPage(Integer.parseInt(params.get("pageNum")),Integer.parseInt(params.get("pageSize")));
		List<RentingHouse> resultList=rentingHouseMapper.queryRentingHousesBatch(params);
		PageInfo<RentingHouse> pageInfo=new PageInfo<RentingHouse>(resultList);
		return pageInfo;
	}
	/**
	 * 批量查询房源
	 * 首页专用
	 */
	@Override
	public PageInfo<RentingHouse> queryRentingHouseBatchFront (Map<String, String> params){
		PageHelper.startPage(Integer.parseInt(params.get("pageNum")),Integer.parseInt(params.get("pageSize")));
		SearchConditionDto tempSearch= new SearchConditionDto();
		if(StringUtils.isNotEmpty(params.get("rent"))){
			String tempString = params.get("rent");
			String [] a=tempString.split("-");
			String t1= a[0];
			String t2 = a[1];
			if(Integer.parseInt(t1)==10000){
				tempSearch.setRent(100);
				tempSearch.setRent1(Integer.parseInt(t1));
				tempSearch.setRent2(10000000);
			}else{
				tempSearch.setRent(100);
				tempSearch.setRent1(Integer.parseInt(t1));
				tempSearch.setRent2(Integer.parseInt(t2));
			}			
		}
		if(StringUtils.isNotEmpty(params.get("provienceId"))){
			tempSearch.setProvienceId(params.get("provienceId"));
		}
		if(StringUtils.isNotEmpty(params.get("countryId"))){
			tempSearch.setCountryId(params.get("countryId"));
		}
		if(StringUtils.isNotEmpty(params.get("cityId"))){
			tempSearch.setCityId(params.get("cityId"));
		}	
		if(StringUtils.isNotEmpty(params.get("houseStyle"))){
			tempSearch.setHouseStyle(params.get("houseStyle"));
		}
		if(StringUtils.isNotEmpty(params.get("rentingStyle"))){
			tempSearch.setRentingStyle(params.get("rentingStyle"));
		}	
		List<RentingHouse> resultList=rentingHouseMapper.queryRentingHousesBatchFrontNew(tempSearch);
		PageInfo<RentingHouse> pageInfo=new PageInfo<RentingHouse>(resultList);
		return pageInfo;
	}
	/**
	 * 删除房源
	 */
	@Override
	public void deleteRentingHouse(List<RentingHouse> params,User user) {
		if(null!=params&&params.size()>0){
			rentingHouseMapper.deleteRentingHousesBatch(params);
		}
		//删除房子图片
//		for (RentingHouse rentingHouse : params) {
//			if(rentingHouse.getImages()!=null&&!rentingHouse.getImages().equals("")){
//				try {
//					fileUploadUtils.deletePhotoFromSer(rentingHouse.getImages());
//				} catch (IOException | MyException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		//删除房子房东连接
		List<String > tempParmas=new ArrayList<String>();
		for (RentingHouse rentingHouse : params) {
			tempParmas.add(rentingHouse.getRentingHouseId());
		}
		rentingHouseMapper.deleteRentingHouseUserConn(tempParmas);
		
	}
	
	/**
	 * 更新房子信息
	 */
	@Override
	public void updateRentingHouse(RentingHouse rentingHouse,User user) {
		if(rentingHouse!=null&&rentingHouse.getRentingHouseId()!=null&&!rentingHouse.getRentingHouseId().equals("")){
			rentingHouseMapper.updateRentingHouse(rentingHouse);
		}
		
	}
	/**
	 * 根据房子UUID查询房子具体信息
	 */
	@Override
	public RentingHouse queryRentingHouse(RentingHouse rentingHouse) {
		RentingHouse result=null;
		City city=new City();
		if(rentingHouse.getRentingHouseId()!=null&&!rentingHouse.getRentingHouseId().equals("")){
			result=rentingHouseMapper.queryRentingHouse(rentingHouse);
		}
		//翻译房子省市县
		if(result.getProvienceId()!=null&&!result.getProvienceId().equals("")){
			city.setOrgId(result.getProvienceId());
			result.setProvienceId((cityMapper.queryCityByOrgId(city)).getOrgName());
		}
		if(result.getCityId()!=null&&!result.getCityId().equals("")){
			city.setOrgId(result.getCityId());
			result.setCityId((cityMapper.queryCityByOrgId(city)).getOrgName());
		}
		if(result.getCountryId()!=null&&!result.getCountryId().equals("")){
			city.setOrgId(result.getCountryId());
			result.setCountryId((cityMapper.queryCityByOrgId(city)).getOrgName());
		}
		//翻译房子类型
//		if(StringUtils.isNotEmpty(result.getHouseStyle())){
//			DictionaryEntity tempParams=new DictionaryEntity();
//			tempParams.setTypeCode("houseStyle");
//			tempParams.setCode(result.getHouseStyle());
//			result.setHouseStyle(dictionaryMapper.queryDictionary(tempParams).getValue());
//		}
//		//翻译出租类型
//		if(StringUtils.isNotEmpty(result.getRentingStyle())){
//			DictionaryEntity tempParams1=new DictionaryEntity();
//			tempParams1.setTypeCode("rentingStyle");
//			tempParams1.setCode(result.getRentingStyle());
//			result.setRentingStyle(dictionaryMapper.queryDictionary(tempParams1).getValue());
//		}
		return result;
	}

	@Override
	public MsgDetails queryRentingHouseFront(Map<String,String> params) {
		MsgDetails result = null;
		if(params.equals("")){
			System.out.println(Code.NULL_PARAMS.getErrorMessage());
		}else{
			result = rentingHouseMapper.queryHouseMsgDetailFront(params);
		}
		if(result  == null){
			System.out.println(Code.NULL_DATAS.getErrorMessage());
		}
		//翻译
		if(result.getHouseStyle()!=null){
			result.setHouseStyle(translateHouseStyle(result));
		}		
		//添加用户信息
		if(result.getHouseStyle()!=null){
		result.setUser(userMapper.selectUserFromHouseId(params));
		//处理用户信息
//		if(result.getTenantList()!=null){
//			encryptMsg(result.getTenantList());
//		}		
	}			
		return result;
	}
	private static void checkUser(User user){
		if(StringUtils.isEmpty(user.getUserCID())){
			throw new ServiceException(Code.EMPTY_USER_CID.getErrorCode(), Code.EMPTY_USER_CID.getErrorMessage());
		}
	}
	private static void checkMsg(RentingHouse params,String signel){
		if(signel.equals("update")){
			if(StringUtils.isEmpty(params.getRentingHouseId())){
				throw new ServiceException(Code.EMPTY_RENTINGHOUSEID.getErrorCode(), Code.EMPTY_RENTINGHOUSEID.getErrorMessage());
			}
		}
		if(StringUtils.isEmpty(params.getAddress())){
			throw new ServiceException(Code.EMPTY_RENTINGHOUSEADDRESS.getErrorCode(), Code.EMPTY_RENTINGHOUSEADDRESS.getErrorMessage());
		}
		if(StringUtils.isEmpty(params.getCommunity())){
			throw new ServiceException(Code.EMPTY_RENTINGHOUSECOMMUNITY.getErrorCode(), Code.EMPTY_RENTINGHOUSECOMMUNITY.getErrorMessage());
		}
	}
	/*
	 * 翻译房型
	 */
	private static String translateHouseStyle(MsgDetails params){
		String result ="";
		if(params.getHouseStyle().equals("1")){
			result ="一房"+params.getSupportingFacilities();
		}
		if(params.getHouseStyle().equals("1")){
			result="二房"+params.getSupportingFacilities();
		}
		if(params.getHouseStyle().equals("1")){
			result = "三房"+params.getSupportingFacilities();
		}
		if(params.getHouseStyle().equals("1")){
			result = "四房"+params.getSupportingFacilities();
		}
		return result;
	}
	/*
	 * 处理舍友信息，隐蔽
	 */
	private static List<TenantEntity> encryptMsg(List<TenantEntity> list){
		Iterator it = list.iterator();
		for(int i =0;i<list.size();i++){
			TenantEntity user = list.get(i);
			if(!user.getTenantName().equals("")){
				if(user.getTenantSex()!=null&&user.getTenantSex().equals("1")){
					user.setTenantName(user.getTenantName().substring(0, 1)+"先生");
				}else{
					user.setTenantName(user.getTenantName().substring(0, 1)+"女士");
				}
				if(user.getTenantPhoneNum()!=null){
					user.setTenantPhoneNum(user.getTenantPhoneNum().substring(0, 3)+"********");
				}			
				list.set(i, user);
			}			
		}
		return list;
	}
}
