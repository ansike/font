package com.fd.font.cloud.developer.business.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;

import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.RandomStringUtils;
import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fd.font.cloud.common.constant.Constants;
import com.fd.font.cloud.common.constant.ErrorEnum;
import com.fd.font.cloud.common.exception.BusinessException;
import com.fd.font.cloud.common.vo.ErrorCode;
import com.fd.font.cloud.developer.business.AccessTokenBusiness;
import com.fd.font.cloud.developer.entity.App;
import com.fd.font.cloud.developer.service.AppService;
import com.fd.font.cloud.developer.vo.AccessTokenRequest;
import com.fd.font.cloud.developer.vo.AccessTokenResponse;

@Service("accessTokenBusiness")
public class AccessTokenBusinessImpl implements AccessTokenBusiness {
	
	@Autowired
	private AppService appService;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	
	
	private static final Logger _log = LoggerFactory.getLogger(AccessTokenBusinessImpl.class);
	
	

	@Override
	public AccessTokenResponse createAccessToken(AccessTokenRequest request) {
		
		String appkey = request.getAppkey();
		String mac = request.getMac();
		String time =request.getTime();
		String partnerUserId = request.getPartnerUserId();
		
		//暴露entity
		List<App> list = appService.findByAppkey(appkey);
		
		if(list == null || list.size() != 1){
			throw new BusinessException(new ErrorCode(ErrorEnum.APP_NOT_EXIST));
		}
		
		App app = list.get(0);
		String appSecret = app.getAppSecret();
		
		String src = appkey + "|" + partnerUserId + "|" + time;
		String generatedMac = HmacUtils.hmacMd5Hex(appSecret, src);
		
		if(!generatedMac.equals(mac)){
			throw new BusinessException(new ErrorCode(ErrorEnum.MAC_NOT_MATCH));
		}
		
		String accessToken = RandomStringUtils.randomAlphabetic(32);
		String key = Constants.PREFIX_REDIS_ACCESS_TOKEN + accessToken;//spring-data-redis
		String value = Json.toJson(request);
		stringRedisTemplate.boundValueOps(key).set(value, 30, TimeUnit.MINUTES);
		
		_log.info("key:{} value:{}", key, stringRedisTemplate.boundValueOps(key).get());
		AccessTokenResponse response = new AccessTokenResponse();
		response.setAccessToken(accessToken);
		long expireAt = new java.util.Date().getTime() + 1800*1000;
		response.setExpireAt(expireAt+"");
		return response;
	}


}
