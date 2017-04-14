package com.fd.font.cloud.base;
/**
 * 字体云项目常量配置
 * @author XiRuiQiang
 * 2017年2月15日 下午2:32:42
 */
public class FontCloudConstant {
	/**
	 * 开发者注册标记
	 */
	public final static String REGISTER_ACCOUNT="REGISTER_ACCOUNT";
	/**
	 * 开发者修改密码标记
	 */
	public final static String MODIFY_PASSWORD="MODIFY_PASSWORD";
	/**
	 * 开发者忘记密码发送短信验证码标记
	 */
	public final static String FORGOT_PASSWORD="FORGOT_PASSWORD";
	/**
	 * 开发者修改绑定手机号发送短信验证码标记
	 */
	public final static String MODIFY_MOBILE="MODIFY_MOBILE";
	/**
	 * 开发者绑定手机号发送短信验证码标记--使用QQ或微信登录时使用
	 */
	public final static String BIND_MOBILE="BIND_MOBILE";
	/**
	 * 字体授权过期发送短信验证码标记
	 */
	public final static String AUTH_EXPIRE="AUTH_EXPIRE";
	/**
	 * 手机验证码登录
	 */
	public final static String MOBILE_LOGIN="MOBILE_LOGIN";
	
	public final static String USER_SESSION_ID="USER_ID";
	
	public final static String SMS_REDIS_KEY="SMS_REDIS_KEY_";
	
	public final static String MOBILE="MOBILE";
	/**
	 * 授权即将过期的字体，发送短信状态保存在redis的key
	 */
	public final static String SEND_SMS_FONT_AUTH="SEND_SMS_FONT_AUTH_";
}
