package com.jt.rms.common.util.constant;

/**
 * 
 * @author Administrator
 * @since 2018年3月22日
 * @version 系统常量类
 */
public class Constants {
	
	//开关 1： 关闭   0：打开
	public final static String IS_OPEN = "0";

	//开关 1： 关闭   0：打开
	public final static String IS_COLSE = "1";

	//
	public final static String  CACHE_ABLE = "0";

	//
	public final static String CACHE_PUT = "1";
	
	
	public final static int ZERO=0;//数字类型
	
	
	public final static String PARENT_ID="0";
	
	public final static int PAGE_3=3;
	
	public final static int PAGE_5=5;
	
	public final static int PAGE_10=10;

	public final static int PAGE_1=1;
	public final static int PAGE_SIZE=20;
	
	/*删除标记*/
	public final static int DELETE_FLAG=ZERO;//删除
	public final static int DELETE_FLAG_N=1;//不删除

	public final static String SUCESS_CODE="999999";//成功
	public final static String FAILE_CODE="000000";//失败

	public final static String filedEnglish="filedEnglish"; // 字段英文名称
	public final static String filedChinese="filedChinese"; // 字段中文名称
	public final static String filedValue="filedValue"; // 字段值

	//父节点key
	public final static String PRRENT_ID_KEY="parentId";

	//update key
	public final static String UPDATE_BY_KEY="updateBy";
	//list key
	public final static String listKey = "list";
	//total key
	public final static String totalKey = "total";
	//totalCount key
	public final static String totalCountKey = "totalCount";
	//resultList Key
	public final static String resultListKey = "resultList";
	//isRed key
	public final static String isRedKey = "isRed";

	//orderFiled Key
	public final static String orderFiledKey = "orderFiled";
	//emptyFiled Key
	public final static String emptyFiledKey = "emptyFiled";
	//repeatFiled Key
	public final static String repeatFiledKey = "repeatFiled";
	//模块长度
	public final static Integer MODULE_NAME_LENGTH=40;
	
	//模块说明长度
	public final static Integer MODULE_REMARK_LENGTH=400;
	
	public final static String MODULE_NAME_SYS="0";
	public final static String MODULE_TYPE_REGION="1";
	public final static String MODULE_TYPE_PAGE="2";
	public final static String MODULE_TYPE_TAG="5";
	
	
	public final static Integer MODULE_FILED_NAME_LENGTH=20;
	
	//页面占比总和
	public final static Integer MODULE_FILED_PROP_TOTAL=90;

	//状态，1：有效
	public final static String STATE_ING="1";
	//状态，2：停用
	public final static String STATE_STOP="2";
	//状态，3：删除
	public final static String STATE_DEL="3";



	//SOA token过期时循环次数
	public final static Integer SOA_TOKEN_CALL = 2;
	//SOA token过期错误代码
	public final static String SOA_TOKEN_CODE = "312002";


	//userCode key
	public final static String USER_DODE_KEY = "userId";
	//userName key
	public final static String USER_NAME_KEY = "userName";
	//selfCode key
	public final static String selfCode_KEY = "selfCode";
	//organCode key
	public final static String organCode_KEY = "organCode";
	//organName key
	public final static String organName_KEY = "organName";
	//ip key
	public final static String IP_KEY = "ip";
	//
	public final static String ROLE_LIST_KEY = "roleList";
	//耗时 key
	public final static String totalTimeKey = "totalTime";

	//资源监测默认pageStart
	public final static Integer CHECK_PAGE_START = 1;

	//资源监测默认pageSize
	public final static Integer CHECK_PAGE_SIZE = 5;
	
}
