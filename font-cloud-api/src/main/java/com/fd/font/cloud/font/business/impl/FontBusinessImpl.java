package com.fd.font.cloud.font.business.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fd.font.cloud.common.constant.Constants;
import com.fd.font.cloud.common.constant.ErrorEnum;
import com.fd.font.cloud.common.exception.BusinessException;
import com.fd.font.cloud.common.vo.ErrorCode;
import com.fd.font.cloud.developer.entity.AppFont;
import com.fd.font.cloud.developer.entity.FontDownloadLog;
import com.fd.font.cloud.developer.vo.AccessTokenRequest;
import com.fd.font.cloud.font.business.FontBusiness;
import com.fd.font.cloud.font.dto.FontDetailDto;
import com.fd.font.cloud.font.dto.FontDto;
import com.fd.font.cloud.font.service.FontService;
import com.fd.font.cloud.font.vo.FontDetailResponse;
import com.fd.font.cloud.font.vo.FontDownloadLinks;
import com.fd.font.cloud.font.vo.FontDetailLinks;
import com.fd.font.cloud.font.vo.FontResponse;
import com.fd.font.cloud.font.vo.FontTTFLinks;
import com.fd.font.cloud.font.vo.FontsResponse;

@Service(value="fontBusiness")
public class FontBusinessImpl implements FontBusiness{
	
	@Autowired
	private FontService fontService;
	
	@Value("${font.dirPrefix}")
	private String fontDirPrefix;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	
	private static final Logger _log = LoggerFactory.getLogger(FontBusinessImpl.class);
	
	private AccessTokenRequest validAccessToken(String accessToken){
		String key = Constants.PREFIX_REDIS_ACCESS_TOKEN + accessToken;
		boolean flag = stringRedisTemplate.boundValueOps(key).expire(30, TimeUnit.MINUTES);
		
		if(!flag){
			_log.error("operate key:{} fail.", key);
			throw new BusinessException(new ErrorCode(ErrorEnum.ILEGAL_ACCESS_TOKEN));
		}
		
		String jsonStr = stringRedisTemplate.boundValueOps(key).get();
		return Json.fromJson(AccessTokenRequest.class, jsonStr);
	}

	@Override
	public FontDetailResponse findFontByCode(String code, String accessToken) {
		//验证accessToken
		AccessTokenRequest accessTokenRequest =validAccessToken(accessToken);
		
		//autowire
		FontDetailResponse response = new FontDetailResponse();
		
		AppFont appFont = fontService.getAppFontByAppkeyAndFontCode(accessTokenRequest.getAppkey(), code);
		
		if(appFont!=null){
			response.setAppFontPic(appFont.getAppFontPic());
		}
		
		List<FontDetailDto> resultList = fontService.findFontByCode(code);
		
		if(resultList == null || resultList.size() ==0){
			throw new BusinessException(new ErrorCode(ErrorEnum.FONT_NOT_EXIST));
		}
		
		
		
		if(resultList!=null && resultList.size()>0){
			FontDetailDto dto = resultList.get(0);
			response.setAuthor(dto.getAuthor());
			response.setDescription(dto.getIntroduction());
			response.setId(dto.getCode());
			response.setName(dto.getName());
			response.setVersion(dto.getVersionName());
			
			FontTTFLinks ttf = new FontTTFLinks();
			//ttf.setHref(dto.getTtfDownloadUrl());
			ttf.setHref("https://api.myfont.me/download/fonts?code="+code+"&accessToken="+accessToken);
			FontDownloadLinks links = new FontDownloadLinks();
			links.setTtf(ttf);
			response.setLinks(links);
		}
		
		List<Map<String, String>> icons = new ArrayList<>();
		for(FontDetailDto dto : resultList){
			Map<String, String> icon = new HashMap<>();
			icon.put(dto.getWidth() + "x" + dto.getHeight(), dto.getPicUrl());
			icons.add(icon);
		}
		response.setIcons(icons);
		return response;
	}

	@Override
	public FontsResponse findAll(String accessToken) {
		FontsResponse response = new FontsResponse();
		List<FontResponse> list = new ArrayList<>();
		
		validAccessToken(accessToken);
		
		List<FontDto> resultList =  fontService.findAll();
		Map<String, FontResponse> map = new HashMap<>();
		
		//autowire list to FontsResponse
//		for(FontDto dto : resultList){
//			//FIXME BeanUtils转换
//			FontResponse fontResponse = new FontResponse();
//			fontResponse.setDescription(dto.getIntroduction());
//			fontResponse.setId(dto.getCode());
//			
//			FontDetailLinks links = new FontDetailLinks();
//			links.setHref("/v1/fonts/" + dto.getCode());
//			fontResponse.setLinks(links);
//			
//			fontResponse.setName(dto.getName());
//			
//			Map<String, String> icons = new HashMap<>(1);
//			icons.put(dto.getWidth() + "x" + dto.getHeight(), dto.getPicUrl());
//			fontResponse.setIcons(icons);
//			
//			list.add(fontResponse);
//		}
//		response.setFonts(list);
		
		for(FontDto dto : resultList){
			String code = dto.getCode();
			if(map.get(code)==null){
				FontResponse fontResponse = new FontResponse();
				fontResponse.setDescription(dto.getIntroduction());
				fontResponse.setId(dto.getCode());
				FontDetailLinks links = new FontDetailLinks();
				links.setHref("/v1/fonts/" + dto.getCode());
				fontResponse.setLinks(links);
				fontResponse.setName(dto.getName());
				
				List<Map<String, String>> icons = new ArrayList<>();
				Map<String, String> icon = new HashMap<>();
				icon.put(dto.getWidth() + "x" + dto.getHeight(), dto.getPicUrl());
				icons.add(icon);
				fontResponse.setIcons(icons);
				
				map.put(code, fontResponse);
			}else{
				FontResponse fontResponse = map.get(code);
				List<Map<String, String>> icons = fontResponse.getIcons();
				Map<String, String> icon = new HashMap<>();
				icon.put(dto.getWidth() + "x" + dto.getHeight(), dto.getPicUrl());
				icons.add(icon);
			}
		}
		
		response.setFonts(new ArrayList<>(map.values()));
		return response;
	}

	@Override
	public InputStream fontDownload(String code, String accessToken) {
		AccessTokenRequest request = this.validAccessToken(accessToken);
		String partnerUserId = request.getPartnerUserId();
		String appkey = request.getAppkey();
		
		List<FontDetailDto> resultList = fontService.findFontByCode(code);
		
		if(resultList == null || resultList.size() ==0){
			throw new BusinessException(new ErrorCode(ErrorEnum.FONT_NOT_EXIST));
		}
		
		FontDetailDto dto = resultList.get(0);
		String uri = dto.getTtfDownloadUrl();
		uri = uri.substring(uri.indexOf("api.myfont.me") == -1?0:uri.indexOf("api.myfont.me")+"api.myfont.me".length());
		//String prefix = "E:\\";
		_log.info("fontDirPrefix:{}",fontDirPrefix);
		_log.info("uri:{}",uri);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(fontDirPrefix+uri));
			
			//FIXME 异步处理
			FontDownloadLog fontDownloadLog = new FontDownloadLog();
			fontDownloadLog.setAppKey(appkey);
			fontDownloadLog.setFontCode(code);
			fontDownloadLog.setPartnerUserId(partnerUserId);
			//记录下载历史
			fontService.addFontDownloadLog(fontDownloadLog);
			
			return fis;
		} catch (FileNotFoundException e) {
			_log.error( e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
		
		
	}

	
	
}
