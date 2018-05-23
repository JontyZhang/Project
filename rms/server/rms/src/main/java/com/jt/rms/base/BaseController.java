package com.jt.rms.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.rms.common.cache.RedisUtil;
import com.jt.rms.common.constant.Code;
import com.jt.rms.common.constant.UserEmun;
import com.jt.rms.common.util.constant.Constants;
import com.jt.rms.common.util.exception.ServiceException;
import com.jt.rms.common.util.utils.CookieUtils;
import com.jt.rms.common.util.utils.GetIp;
import com.jt.rms.common.util.utils.RequestUtils;
import com.jt.rms.common.util.utils.SessionUtils;
import com.jt.rms.role.entity.Role;
import com.jt.rms.user.dao.UserMapper;
import com.jt.rms.user.entity.User;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author :zjt
 * @time :2018年3月21日
 */
@Controller
@RequestMapping("/baseController")
@Scope("prototype")
public class BaseController {
	@Resource
	UserMapper userMapper;
	private static SessionUtils sessionutils;
	/**
	 * 从缓存中取出用户信息
	 */
	public Map<String, Object> getUserBySess(HttpServletRequest request) {
        User user = sessionutils.getLoginUmsUser(request.getSession());
        List<String> roles = getRolesToUser(user);
        Map<String ,Object> m = new HashMap<>();
        m.put(Constants.IP_KEY, GetIp.getRealIP(request));//用户ID
        String userName = "";
        m.put(Constants.USER_DODE_KEY, user.getUserId());//用户名称
        if(user.getUserName() != null){
            userName = user.getUserName();
        }
        m.put(Constants.USER_NAME_KEY, userName);//登录IP
        m.put(Constants.ROLE_LIST_KEY, roles);//角色list
        return m;
    }
	/**
	 * 从缓存中取出用户Id
	 * @param params
	 * @return
	 */
    public Map<String,String> getUserId(HttpServletRequest request){
    	CookieUtils cookieUtiles = new CookieUtils();
    	Map<String,String> resultMap= new HashMap<>();
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		RedisUtil redisUtil = (RedisUtil)context.getBean("redisUtil");		
		if(redisUtil.exists("userId")){
			resultMap.put("userId",(String) redisUtil.get("userId"));			
		}else{
			throw new ServiceException(Code.NULL_PARAMS.getErrorCode(),Code.NULL_DATAS.getErrorMessage());
		}
		System.out.println(cookieUtiles.getCookieValue(request, "token"));
		//判断token是否存在，否则请重新登陆
		if(cookieUtiles.getCookieValue(request, "token")==null||!cookieUtiles.getCookieValue(request,"token").equals((String) redisUtil.get("token"))){
			throw new ServiceException(Code.ERROR_TOKEN.getErrorCode(),Code.ERROR_TOKEN.getErrorMessage());
		}
    	return resultMap;
    }
    /**根据redis中缓存的用户id
     * 从数据库中取出用户信息
     * @param params
     * @return
     */
    public User getUserByBess(HttpServletRequest request){
    	String userId = "";
    	CookieUtils cookieUtiles = new CookieUtils();
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		RedisUtil redisUtil = (RedisUtil)context.getBean("redisUtil");
		if(redisUtil.exists("userId")){
			userId = (String)redisUtil.get("userId");
		}else{
			throw new ServiceException(Code.NULL_USER.getErrorCode(),Code.NULL_USER.getErrorMessage());
		}
		//判断token是否存在，否则请重新登陆
		if(cookieUtiles.getCookieValue(request, "token")==null||!cookieUtiles.getCookieValue(request,"token").equals((String) redisUtil.get("token"))){
			throw new ServiceException(Code.ERROR_TOKEN.getErrorCode(),Code.ERROR_TOKEN.getErrorMessage());
		}
    	User user =userMapper.queryUserById(userId);
    	return user;
    }
    
    public List<String> getRolesToUser(User params){
    	if(params==null){
    		throw new ServiceException(Code.USER_LOGIN.getErrorCode(),Code.USER_LOGIN.getErrorMessage());
    	}
    	List<Role> roleList = params.getRoleList();
    	List<String> roles = new ArrayList<String>();
    	if (CollectionUtils.isNotEmpty(roleList)) {
			for (Role urd : roleList) {
				roles.add(urd.getRoleId());
			}
		}
    	return roles;
    }
    /**
     * 获取ip
     *
     * @author: 
     */
    @RequestMapping(value = "getIp", method = RequestMethod.POST)
    @ApiOperation(value = "获取ip", notes = "获取ip", httpMethod = "POST", response = List.class)
    public Map<String, String> getIp(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ip", RequestUtils.getRealIP(request));
        return map;
    }
    
}
