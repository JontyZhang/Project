package com.jt.rms.common.constant;
/**
 * 
 * @author :zjt
 * @time :2018年3月11日
 */
public enum Code implements ExceptionConstant{
    /**
     * 参数为空
     * */
    EMPTY_PARAME("A11002","参数为空"),
    /**
     * 参数错误 
     * */
    ERROR_PARAME("A11003","参数错误"),
    /**
     * 用户操作问题
     */
    USER_KEY("USER", "存在Session中的用户KEY"),
	RATION_KEY("RATION","存在Session中的用户权限KEY"),
	USER_LOGIN("808099","您没有登录或者登录失效，请登录！"),
	/**
	 * 操作fastdfs的问题
	 */
	FAIl_TO_INSERT("999960","上传图片失败"),
    //A12为字典表
	/**数据库存在重复typeCode*/
	REPEAT_PARAME("A11000","数据库存在重复typeCode和Code"),
	EMPTY_TYPECODE("A11001","typeCode为空"),
	EPMTY_CODE("A11002","CODE为空"),
	//A11为用户表
	EMPTY_USER("A11000","用户不存在或者密码错误"),
	EMPTY_USERNAME("A11001","用户名为空"),
	EMPTY_USERPWD("A11002","用户密码为空"),
	EMPTY_PHONE_NUM("A11003","用户密码为空"),
	ERROR_DELETE_USER_H("A11004","该用户名下用户房子，请先删除房子"),
	EMPTY_USER_CID("A11005","用户身份证信息为空，请补充信息"),
	//A10房子表
	EMPTY_RENTINGHOUSE("A10000","房子信息为空或者参数错误"),
	EMPTY_RENTINGHOUSEID("A10001","房子ID为空"),
	EMPTY_RENTINGHOUSEADDRESS("A10002","房子详细地址为空"),
	EMPTY_RENTINGHOUSECOMMUNITY("A10003","房子社区为空"),	
	//A09合同表
	EMPTY_CONTRACT("A09000","合同表信息为空或者参数错误"),
	EMPTY_CONTRACT_L_NAME("A09001","房东姓名为空"),
	EMPTY_CONTRACT_L_CID("A09002","房东身份证为空"),
	ERROR_CONTRACT_L_CID("A09003","房东身份证错误"),
	EMPTY_CONTRACT_T_NAME("A09004","客户名字为空"),
	EMPTY_CONTRACT_T_CID("A10905","客户身份证为空"),	
	ERROR_CONTRACT_T_CID("A10906","客户身份证错误"),	
	EMPTY_CONTRACT_RENT("A09007","租金为空"),
	EMPTY_CONTRACT_RENT_FROM_DATE("A09008","租期起为空"),
	EMPTY_CONTRACT_RENT_TO_DATE("A09009","租期至为空"),
	EMPTY_CONTRACT_ID("A09010","合同ID为空"),
	ERROR_CONTRACT_STATE("A09011","生效合同无法修改"),
	ERROR_DELETE_CONTRACT_STATE("A09012","生效合同无法删除"),
	//A08押金表
	EMPTY_DEPOSIT("A08000","押金信息为空或者参数错误"),
	EMPTY_DEP_LANLORD("A08001","押金表收款方为空"),
	EMPTY_DEP_TENANT("A08002","押金单付款方为空"),
	EMPTY_DEP_DEPOSIT("A08002","押金单金额为空"),
	ERROR_DELETE_DEPOSIT_STATE("A08011","生效押金单无法删除"),
	//A07客户表
	EMPTY_TEANNT("A07000","客户信息为空或者参数错误"),
	EMPTY_TEANNT_ID("A07001","客户ID为空"),
	//A06定金详情表
	EMPTY_HOUSE_RENT("A06000","房租不能为空"),
	EMPTY_HOUSE_RENT_ID("A06001","租金ID不能为空"),
	//A05为前台用
	NULL_PARAMS("A05000","参数为空"),
	NULL_DATAS("A05001","内容为空"),
	//A04用户信息判断
	NULL_USER("A04000","用户信息失效或者过期，请重新登陆"),
	ERROR_TOKEN("A04001","Token已过期或者有误，请重新登陆")
	;
	private String errorCode;
	private String errorMessage;

	private Code(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
	 public String getErrorCode() {
	        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
   }
}
