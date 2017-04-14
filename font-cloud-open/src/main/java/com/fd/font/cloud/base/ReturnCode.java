package com.fd.font.cloud.base;

public enum ReturnCode {
    SUCCESS(0,"成功"),
    FAILE(1,"失败"),
    MOBILE_EXIST(106, "手机号已注册"),
    LOGIN_NO_SUCCESS(203,"用户未登录"),
    LOGIN_PASSWORD_ERROR(204,"帐号或密码错误"),
    MOBILE_NOT_EXIST(306,"手机号不存在"),
    SMS_CODE_DIE(101, "短信验证码失效"),
    CONFIRM_PASSWORD_ERROR(102, "两次密码输入不一致"),
    SMS_CODE_ERROR(103, "短信验证码错误"),
    RANDOM_CODE_ERROR(104, "验证码错误"),
    UPDATE_EMAIL_ERROR(105,"修改邮箱失败"),
    EMAIL_FORMAT_ERROR(106,"邮箱格式错误"),
    UPDATE_PASSWORD_ERROR(107,"修改密码失败"),
    MOBILE_FORMAT_ERROR(108,"手机号格式错误"),
    STEP_ERROR(109,"步骤错误"),
    
    APP_NAME_PARAM_NOT_EXIST(200, "应用名称不为空"),
	DEVELOPER_ID_PARAM_NOT_EXIST(201, "developerId参数不为空"),
	APP_NOT_EXIST(202, "app不存在"),
	APP_NUM_LIMIT(203, "APP创建数量超限"), 
	APP_TYPE_PARAM_NOT_EXIST(204, "appType参数不为空"), 
	APP_KEY_PARAM_NOT_EXIST(205, "appKey参数不存在"), 
	APP_ID_PARAM_NOT_EXIST(206, "appId参数不存在"),
	APP_NAME_EXIST(207,"应用名称已存在"),
	APP_NOT_UPDATE_ERROR(208,"应用没有修改次数"),
	
	FONT_NOT_EXIST(301,"字体不存在"),

    SMS_MAX_ERROR(406,"每天发送验证码次数为10次"),
    OFTEN_REQUEST_ACCESS_SERVER(407,"频繁的请求数据"),
    OFTEN_REQUEST_ACCESS_CLIENT(408,"频繁的请求数据"),
    ;

    private Integer key;
    private String value;

    private ReturnCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
