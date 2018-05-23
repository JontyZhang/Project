package com.jt.rms.common.constant;
public enum UserEmun {
	USER_KEY("USER", "存在Session中的用户KEY"),
	RATION_KEY("RATION","存在Session中的用户权限KEY"),
	USER_NOT_EXIST("110000","用户不存在"),
	USER_ERROR_PWD("110001","用户密码错误"),
	PRIVILAGE_NOT_ALLOW("123061","权限不足，不允许访问"),
	SESSION_EXPIRES("120000", "用户会话超时"),
	ADMIN("admin", "超级管理员"),
	ADMIN_ROLE_ID("156c2dd89955420c8bee4f326788a8d3", "超级管理员角色id"),
	TICKET_KEY("TICKET", "存在shiro的Session中的单点登录票据"),
	SYSTEM_ID("SYSTEMID", "系统id"),
	IP("IP", "服务器IP"),
	SESSION_ID("SESSIONID", "会话ID"),
	USER_MODULE_LIST("","USER_MODULE_LIST"),
	USER_LOGIN_PREMI("808010","您没有任何权限，请找管理员分配！"),
	SSO_FLAG("flag","单点登录的判断标示key"),
	TICKET_ENTRYPT_FAILED("124001", "TICKET加密失败"),
	TICKET_DETRYPT_FAILED("124002", "TICKET解密失败"),
	OPERA_TIME("OPERA_TIME", "用户最新操作时间"),
	ONLINE_COUNT("onlineCount", "在线人数"),
	;

	private String code;
	private String desc;
	
	UserEmun(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return this.code;
	}

	public String getDesc() {
		return this.desc;
	}
}