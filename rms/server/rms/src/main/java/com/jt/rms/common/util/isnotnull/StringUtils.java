package com.jt.rms.common.util.isnotnull;

/**
 * 字符串工具类
 * @author zjt
 * @time   2017/12/20
 */
public class StringUtils {
	
	/**
	 * 判断字符串是为非 null 和 ""
	 */
	public static boolean isNotEmpty(Object value) {
		if(null  == value) {
			return false;
		}else if("".equals(value)) {
			return false;
		}else {
			return true;
		}
	}


	/**
	 * 判断字符串是为 null 和 ""
	 */
	public static boolean isEmpty(Object value) {
		if(null  == value) {
			return true;
		}else if("".equals(value)) {
			return true;
		}else {
			return false;
		}
	}
}
