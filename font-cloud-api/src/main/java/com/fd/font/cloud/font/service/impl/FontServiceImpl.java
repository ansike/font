package com.fd.font.cloud.font.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.font.cloud.common.constant.ErrorEnum;
import com.fd.font.cloud.common.exception.BusinessException;
import com.fd.font.cloud.common.vo.ErrorCode;
import com.fd.font.cloud.developer.dao.AppFontMapper;
import com.fd.font.cloud.developer.dao.AppMapper;
import com.fd.font.cloud.developer.dao.FontDownloadLogMapper;
import com.fd.font.cloud.developer.entity.App;
import com.fd.font.cloud.developer.entity.AppExample;
import com.fd.font.cloud.developer.entity.AppFont;
import com.fd.font.cloud.developer.entity.AppFontExample;
import com.fd.font.cloud.developer.entity.FontDownloadLog;
import com.fd.font.cloud.font.dao.FontExMapper;
import com.fd.font.cloud.font.dao.FontMapper;
import com.fd.font.cloud.font.dto.FontDetailDto;
import com.fd.font.cloud.font.dto.FontDto;
import com.fd.font.cloud.font.entity.Font;
import com.fd.font.cloud.font.entity.FontExample;
import com.fd.font.cloud.font.service.FontService;

@Service(value="fontService")
public class FontServiceImpl implements FontService{
	
	@Autowired
	private FontMapper fontMapper;
	
	@Autowired
	private FontExMapper fontExMapper;
	
	@Autowired
	private AppMapper appMapper;
	
	@Autowired
	private AppFontMapper appFontMapper;
	
	@Autowired
	private FontDownloadLogMapper fontDownloadLogMapper;

	@Override
	public List<FontDetailDto> findFontByCode(String code) {
//		FontExample fontExample = new FontExample();
//		fontExample.createCriteria().andCodeEqualTo(code);
//		fontMapper.selectByExample(fontExample);
		return fontExMapper.selectByFontCode(code);
	}

	@Override
	public List<FontDto> findAll() {
		List<FontDto> list = fontExMapper.findAll();
		return list;
	}

	@Override
	public AppFont getAppFontByAppkeyAndFontCode(String appkey, String code) {
		FontExample fontExample = new FontExample();
		fontExample.createCriteria().andCodeEqualTo(code);
		List<Font> list = fontMapper.selectByExample(fontExample);
		
		if(list == null || list.size()==0){
			throw new BusinessException(new ErrorCode(ErrorEnum.FONT_NOT_EXIST));
		}
		
		Font font = list.get(0);
		Integer fontId = font.getFontId();
		Integer currFontVersionId = font.getCurrFontVersionId();
		
		AppExample appExample = new AppExample();
		appExample.createCriteria().andAppKeyEqualTo(appkey);
		appMapper.selectByExample(appExample);
		List<App> applist = appMapper.selectByExample(appExample);
		App app = applist.get(0);
		Integer appId = app.getAppId();
		
		AppFontExample appFontExample = new  AppFontExample();
		appFontExample.createCriteria().andFontIdEqualTo(fontId).andFontVersionIdEqualTo(currFontVersionId).andAppIdEqualTo(appId);
		List<AppFont> appFontList =  appFontMapper.selectByExample(appFontExample );
		
		if(appFontList != null && appFontList.size()>0){
			return appFontList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public int addFontDownloadLog(FontDownloadLog fontDownloadLog) {
		FontExample fontExample = new FontExample();
		fontExample.createCriteria().andCodeEqualTo(fontDownloadLog.getFontCode());
		List<Font> list = fontMapper.selectByExample(fontExample);
		Font font = list.get(0);
		
		fontDownloadLog.setFontId(font.getFontId());
		fontDownloadLog.setFontVersionId(font.getCurrFontVersionId());
		
		AppExample appExample = new AppExample();
		appExample.createCriteria().andAppKeyEqualTo(fontDownloadLog.getAppKey());
		List<App> appList = appMapper.selectByExample(appExample) ;
		App app = appList.get(0);
		
		fontDownloadLog.setAppId(app.getAppId());
		
		return fontDownloadLogMapper.insertSelective(fontDownloadLog);
	}

	
}
