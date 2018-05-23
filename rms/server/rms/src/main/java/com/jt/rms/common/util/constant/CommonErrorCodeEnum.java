package com.jt.rms.common.util.constant;

import com.jt.rms.common.util.Constant;

/**
 * Created by bing on 2017/7/15.
 */
public enum CommonErrorCodeEnum implements Constant {
    SUCCESS("999999", "成功"),
    FAIL("100001", "失败"),
    INPUT_ERROR("100002", "输入参数有误"),
    INPUT_EMPTY("100003", "输入参数不能为空")
    ;
    private String code;
    private String value;

    CommonErrorCodeEnum(String code, String value) {
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
