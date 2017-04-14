package com.fd.font.cloud.developer.controller;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fd.font.cloud.common.constant.ErrorEnum;
import com.fd.font.cloud.common.exception.BusinessException;
import com.fd.font.cloud.common.vo.ErrorCode;
import com.fd.font.cloud.common.vo.Errors;
import com.fd.font.cloud.developer.business.AccessTokenBusiness;
import com.fd.font.cloud.developer.vo.AccessTokenRequest;
import com.fd.font.cloud.developer.vo.AccessTokenResponse;

@Controller
@RequestMapping
public class AccessTokenController {
	
	private static final Logger _log = LoggerFactory.getLogger(AccessTokenController.class);
	
	@Autowired
	private AccessTokenBusiness accessTokenBusiness;

	@RequestMapping(value = "/v1/accessToken", method = RequestMethod.POST)
	@ResponseBody
	public Object accessToken(@RequestBody AccessTokenRequest request){
		String appKey = request.getAppkey();
		String time = request.getTime();
		String mac = request.getMac();
		String partnerUserId = request.getPartnerUserId();
		Errors errors = new  Errors();
		
		if(StringUtils.isEmpty(appKey)){
			errors.add(new ErrorCode(ErrorEnum.APPKEY_PARAM_NOT_EXIST));
		}
		
		if(StringUtils.isEmpty(mac)){
			errors.add(new ErrorCode(ErrorEnum.MAC_PARAM_NOT_EXIST));
		}
		
		if(StringUtils.isEmpty(time)){
			errors.add(new ErrorCode(ErrorEnum.TIME_PARAM_NOT_EXIST));
		}
		
		if(StringUtils.isEmpty(partnerUserId)){
			errors.add(new ErrorCode(ErrorEnum.PARTNERUSERID_PARAM_NOT_EXIST));
		}
		
		if(errors.getErrors().size()>0){
			return errors;
		}
		
		long now = new DateTime().getMillis();
		long commit = Long.valueOf(time);
		long interval = Math.abs(now-commit);
		
		if(interval>15*60*1000){//超过60s
			errors.add(new ErrorCode(ErrorEnum.TIME_PARAM_LIMIT_INNER_15_MINIUTES));
		}
		
		if(errors.getErrors().size()>0){
			return errors;
		}
		
		AccessTokenResponse response =null;
		try{
			response = accessTokenBusiness.createAccessToken(request);
		}catch(BusinessException be){
			_log.error(be.getMessage(), be);
			errors.add(be.getErrorCode());
			return errors;
		}catch(Exception e){
			_log.error(e.getMessage(), e);
			errors.add(new ErrorCode(ErrorEnum.SYSTEM_ERROR));
			return errors;
		}
		return response;
	}
	
	public static void main(String[] args){
		long now = new DateTime().getMillis();
		System.out.println(now);
		long commit = Long.valueOf(now+59*1000);
		long interval = Math.abs(now-commit);
		
		if(interval>60*1000){//超过60s
			System.out.println(">60s");
		}else{
			System.out.println("<=60s");
		}
	}
}
