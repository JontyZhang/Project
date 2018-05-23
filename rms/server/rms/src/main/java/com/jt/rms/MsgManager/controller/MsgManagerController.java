package com.jt.rms.MsgManager.controller;

import java.util.List;
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
import com.jt.rms.base.BaseController;
import com.jt.rms.user.entity.User;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author :zjt
 * @time :2018年5月8日
 */
@Controller
@RequestMapping("MsgManager")
public class MsgManagerController extends BaseController{
	@Resource
	IMsgManagerService iMsgManagerService;
	/**
	 * 批量查询留言
	 */
	@RequestMapping(value = "queryMsgBatch", method = RequestMethod.POST)
	@ApiOperation(value = "批量查询留言", notes = "批量查询留言",httpMethod = "POST", response = PageInfo.class)
	public PageInfo<MsgManagerEntity> queryMsgBatch (@RequestBody Map<String,String> params,HttpServletRequest request){
		//获取用户基本信息
		User user = getUserByBess(request);
		params.put("userId", user.getUserId());
		return iMsgManagerService.queryMsgBatch(params);
	}
	/**
	 * 查看留言详情
	 */
	@RequestMapping(value="queryMsgDetail",method = RequestMethod.POST)
	@ApiOperation(value = "查看留言详情" ,notes ="查看留言详情" ,httpMethod="POST" ,response = MsgManagerEntity.class)
	public MsgManagerEntity queryMsgDetail (@RequestBody MsgManagerEntity params){
		
		return iMsgManagerService.queryMsgDetail(params);
	}
	/**
	 * 批量删除留言信息
	 */
	@RequestMapping(value = "deleteMsgBatch" ,method = RequestMethod.POST)
	@ApiOperation(value = "批量删除留言" ,notes ="批量删除留言" ,httpMethod="POST" ,response = void.class)
	public void deleteMsgBatch (@RequestBody List<MsgManagerEntity> params){
		iMsgManagerService.deleteMsgBatch(params);
	}
}
