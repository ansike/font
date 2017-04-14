package com.fd.font.cloud.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.base.ReturnCode;

/**
 * Created by EricWong on 2016/8/31.
 */
public class SendSmsTool {
	private final static IClientProfile clientProfile = DefaultProfile.getProfile("cn-hangzhou", "LTAIslLeAJRyQwWD", "zXC5Ka07xTdtq24Kcr8Gwj3hgVyuVG");

	/**
	 * 注册账号
	 */
	public final static String REGISTER_ACCOUNT_TEMPLATE = "SMS_56525418";// "SMS_14825172";
	/**
	 * 修改密码
	 */
	public final static String MODIFY_PASSWORD_TEMPLATE = "SMS_56525418";// "SMS_14795257";
	/**
	 * 找回密码
	 */
	public final static String FORGOT_PASSWORD_TEMPLATE = "SMS_56525418";// "SMS_15000239";
	/**
	 * 字体授权期限
	 */
	public final static String CHECK_FONT_AUTH = "SMS_56545257";// "SMS_15670016";

	/**
	 * 发送短信
	 * 
	 * @param templateCode
	 * @param mobile
	 * @param smsRandomCode
	 */
	public static SingleSendSmsResponse sendSms(String templateCode, String mobile, String smsRandomCode) throws ControllerException {
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
			IAcsClient acsClient = new DefaultAcsClient(clientProfile);
			SingleSendSmsRequest singleSendSmsRequest = new SingleSendSmsRequest();
			singleSendSmsRequest.setActionName("SingleSendSms");
			singleSendSmsRequest.setSignName("方正手迹");
			singleSendSmsRequest.setTemplateCode(templateCode);
			singleSendSmsRequest.setRecNum(mobile);
			singleSendSmsRequest.setParamString("{\"smsCode\":\"" + smsRandomCode + "\"}");
			return acsClient.getAcsResponse(singleSendSmsRequest);
		} catch (ServerException e) {
			throw new ControllerException(ReturnCode.OFTEN_REQUEST_ACCESS_SERVER);
		} catch (ClientException e) {
			throw new ControllerException(ReturnCode.OFTEN_REQUEST_ACCESS_CLIENT);
		}
	}

	/**
	 * 获取0~9的随机短信验证码
	 * 
	 * @param length
	 *            验证码长度
	 * @return
	 */
	public static String getSmsRandomCode(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(String.valueOf((int) Math.floor(Math.random() * 9)));
		}
		return sb.toString();
	}
}
