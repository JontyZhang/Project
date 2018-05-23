package com.jt.rms.rentinghouse.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.jt.rms.base.BaseController;
import com.jt.rms.rentinghouse.entity.RentingHouse;
import com.jt.rms.rentinghouse.entity.ResultVo;
import com.jt.rms.rentinghouse.service.IRentingHouseService;
import com.jt.rms.user.entity.User;

import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice.Return;



@Controller
@RequestMapping("rentingHouse")
public class RentingHouseController extends BaseController{
	private static final Log logger = LogFactory.getLog(RentingHouseController.class);
	
	@Resource
	private IRentingHouseService IRentingHouseService;
	/**
	 *批量查询房子
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "queryRentingHousesBatch", method = RequestMethod.POST)
	@ApiOperation(value = "批量查询房子", notes = "批量查询房子",httpMethod = "POST", response = PageInfo.class)
	public PageInfo<RentingHouse> queryRentingHousesBatch(@RequestBody Map<String, String> params,HttpServletRequest request) {
		PageInfo<RentingHouse> result =new PageInfo<RentingHouse>();
		//设置用户
		Map<String,String> tempParams=getUserId(request);
		params.put("userId", tempParams.get("userId"));
		// Map<String, Object> rere=getUserBySess(request);
		result=IRentingHouseService.queryRentingHouseBatch(params);
		return result;
	}
	/**
	 * 新增租屋
	 * @param params
	 */
	@RequestMapping(value = "addRentingHouse", method = RequestMethod.POST)
	@ApiOperation(value = "添加租屋", notes = "添加租屋",httpMethod = "POST", response = void.class)
	public ResultVo addRentingHouse(@RequestBody RentingHouse params,HttpServletRequest request) {		
		//设置用户
		User user = getUserByBess(request);
		return IRentingHouseService.addRentingHouse(params,user);
	}
	/**
	 * 删除租屋
	 */
	@RequestMapping(value = "deleteRentingHouse", method = RequestMethod.POST)
	@ApiOperation(value = "删除租屋", notes = "删除租屋",httpMethod = "POST", response = void.class)
	public void deleteRentingHouse(@RequestBody List<RentingHouse> params,HttpServletRequest request) {		
		if(params!=null&&params.size()>0){
			//设置用户
			User user = getUserByBess(request);
			IRentingHouseService.deleteRentingHouse(params,user);
		}
	}
	/**
	 * 修改租屋信息
	 */
	@RequestMapping(value = "updateRentingHouse", method = RequestMethod.POST)
	@ApiOperation(value = "更新租屋", notes = "更新租屋",httpMethod = "POST", response = void.class)
	public void updateRentingHouse(@RequestBody RentingHouse params,HttpServletRequest request) {		
		if(params!=null){
			//设置用户
			User user = getUserByBess(request);
			IRentingHouseService.updateRentingHouse(params,user);
		}
	}
	/**
	 * 查询租屋具体信息
	 */
	@RequestMapping(value = "queryRentingHouse", method = RequestMethod.POST)
	@ApiOperation(value = "查询租屋具体信息", notes = "查询租屋具体信息",httpMethod = "POST", response = RentingHouse.class)
	public RentingHouse queryRentingHouse(@RequestBody RentingHouse params) {	
		RentingHouse result=null;
		if(params!=null){
			result=IRentingHouseService.queryRentingHouse(params);
		}
		return result;
	}
}
