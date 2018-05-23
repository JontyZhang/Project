package com.jt.rms.common.util.constant;


/**
 * Created by bing on 2017/7/16.
 */
public enum  ServiceIdentifyEnum implements com.jt.rms.common.util.Constant {
    UC("1", "用户中心"),
    QIYECLASS("11", "企业公开课"),
    CMS("12", "内容管理"),
    QUAN("13", "圈子")
    ;
    private String code;
    private String value;

    ServiceIdentifyEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
