package com.jt.rms.common.util.utils;

import javax.servlet.http.HttpSession;

import com.jt.rms.common.constant.UserEmun;
import com.jt.rms.common.util.exception.ServiceException;
import com.jt.rms.user.entity.User;

/**
 * 
 * @author :zjt
 * @time :2018年3月21日
 */
public class SessionUtils {
	public static User getLoginUmsUser(HttpSession session)  {
		User user = null;
		try {
			Object object = session.getAttribute(UserEmun.USER_KEY.getCode());
			String id = session.getId();
			if(object == null) {
				throw new ServiceException(UserEmun.SESSION_EXPIRES.getCode(), UserEmun.SESSION_EXPIRES.getDesc());
			}
//			umsUser = new LoginUser();
//			JSONObject objJson = JSONObject.parseObject(object.toString());
//			umsUser.setUserId(objJson.get(CasConstants.USER_ID).toString());
//			umsUser.setAccount(objJson.get(CasConstants.ACCOUNT).toString());
//			umsUser.setUserName(objJson.get(CasConstants.USER_NAME).toString());
//			umsUser.setSelfCode(objJson.get(CasConstants.SELF_CODE).toString());
//			umsUser.setRoleList(JSONObject.parseArray(objJson.get(CasConstants.ROLE_LIST).toString(), UcRoleDto.class));
		} catch (Exception e) {
			throw new ServiceException(UserEmun.SESSION_EXPIRES.getCode(), UserEmun.SESSION_EXPIRES.getDesc());
		}
		return user;
	}
}
