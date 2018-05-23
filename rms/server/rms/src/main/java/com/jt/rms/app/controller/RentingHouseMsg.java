package com.jt.rms.app.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.jt.rms.MsgManager.entity.MsgManagerEntity;
import com.jt.rms.MsgManager.service.IMsgManagerService;
import com.jt.rms.app.entity.MsgDetails;
import com.jt.rms.app.entity.SearchConditionDto;
import com.jt.rms.base.BaseController;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.rentinghouse.service.IRentingHouseService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author :zjt
 * @time :2018年3月21日
 */
@Controller
@RequestMapping("RentingHouseMsgController")
public class RentingHouseMsg{
	@Resource
	private IRentingHouseService IRentingHouseService;
	@Resource
	private IMsgManagerService iMsgMangerService;
	/**
	 * 首页专用
	 * 分页查询房子信息
	 */
	@RequestMapping(value = "queryRentingHousesBatchFront", method = RequestMethod.POST)
	@ApiOperation(value = "批量查询房子", notes = "批量查询房子",httpMethod = "POST", response = PageInfo.class)
	public PageInfo<RentingHouse> queryRentingHousesBatch(@RequestBody Map<String, String> params) {
		PageInfo<RentingHouse> result =new PageInfo<RentingHouse>();
		result=IRentingHouseService.queryRentingHouseBatchFront(params);
		return result;
	}
	/**
	 * 根据房子ID查询房子详情信息，包括房东部分信息，合租舍友部分信息	 
	 **/
	@RequestMapping(value="queryMsgDetail",method=RequestMethod.POST)
	@ApiOperation(value = "查询房子具体信息", notes = "查询房子具体信息",httpMethod = "POST", response = MsgDetails.class)
	public MsgDetails  queryMsgDetail(@RequestBody Map<String,String> params){
		MsgDetails result = IRentingHouseService.queryRentingHouseFront(params);
		return result;
	}
	/**
	 * 留言
	 */
	@RequestMapping(value="setMsg",method=RequestMethod.POST)
	@ApiOperation(value="给房东留言",notes ="给房东留言",httpMethod="POST",response=void.class)
	public void setMsg(@RequestBody MsgManagerEntity params){
		params.setUserId("USER_3FCAEC8EDA7B453C805CC3996413D2FD");
		iMsgMangerService.addMsg(params);
	}
}