package com.fd.font.cloud.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fd.font.cloud.app.dao.AppFontMapper;
import com.fd.font.cloud.app.dao.AppMapper;
import com.fd.font.cloud.app.entity.App;
import com.fd.font.cloud.app.entity.AppExample;
import com.fd.font.cloud.app.entity.AppFont;
import com.fd.font.cloud.app.entity.AppFontExample;
import com.fd.font.cloud.app.service.AppService;
import com.fd.font.cloud.base.ApplicationException;
import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.base.FontTypeEnum;
import com.fd.font.cloud.base.ReturnCode;
import com.fd.font.cloud.developer.dao.AppFontExMapper;
import com.fd.font.cloud.developer.dao.DeveloperMapper;
import com.fd.font.cloud.developer.entity.Developer;
import com.fd.font.cloud.developer.vo.AppDetailResponse;
import com.fd.font.cloud.developer.vo.AppFontResponse;
import com.fd.font.cloud.font.dao.FontExMapper;
import com.fd.font.cloud.font.entity.FontExample;
import com.fd.font.cloud.font.vo.FontDetailVo;
import com.fd.font.cloud.util.FontCloudDateUtil;
import com.fd.font.cloud.util.ImgUtils;

@Service("appService")
public class AppServiceImpl implements AppService {
	
	@Autowired
	private AppMapper appMapper;
	
	@Autowired
	private AppFontMapper appFontMapper;
	@Autowired
	private AppFontExMapper appFontExMapper;
	
	@Autowired
	private DeveloperMapper developerMapper;
	
	@Autowired
	private FontExMapper fontExMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void upsert(App app) throws ApplicationException {
		if(app.getAppId()==null){//insert
			add(app);
		}else {//update 
			modify(app);
		}
		
	}
	
	private void modify(App app) throws ApplicationException {
		Developer developer = developerMapper.selectByPrimaryKey(app.getDeveloperId());
		if(developer == null){
			throw new ApplicationException(ReturnCode.APP_NOT_EXIST);
		}
		AppExample appNameExample=new AppExample();
		appNameExample.createCriteria().andDeveloperIdEqualTo(developer.getDeveloperId())
		.andAppNameEqualTo(app.getAppName()).andIsDeletedEqualTo(FontTypeEnum.NOT_DELETED.getType())
		.andAppIdNotEqualTo(app.getAppId());
		List<App> apps=appMapper.selectByExample(appNameExample);
		if(apps.size()>0){
			//应用名称存在
			throw new ApplicationException(ReturnCode.APP_NAME_EXIST);
		}
		//查询应用字体
		App appRecord=appMapper.selectByPrimaryKey(app.getAppId());
		if(!appRecord.getAppName().equals(app.getAppName())){
			//是修改应用名
			if(appRecord.getUpdateFrequency()==null||appRecord.getUpdateFrequency()<=0){
				//修改次数用完
				throw new ApplicationException(ReturnCode.APP_NOT_UPDATE_ERROR);
			}
			appRecord.setUpdateFrequency(appRecord.getUpdateFrequency()-1);
			appRecord.setAppName(app.getAppName());
		}else{
			appRecord.setAppType(app.getAppType());
			appRecord.setIconUrl(app.getIconUrl());
		}
		List<AppFontResponse> fonts=appFontExMapper.selectAppFontByAppId(appRecord);
		List<AppFont> appFonts=new ArrayList<>();
		for(AppFontResponse font:fonts){
			//创建字体的图片
			String ttf=font.getTtfDownloadUrl();
			if(ttf.startsWith("http")){
				ttf=ttf.substring(ttf.indexOf("/download/")+10);
			}else{
				ttf=ttf.substring(ttf.indexOf("download/")+9);
			}
			ImgUtils.PictureInfo picInfo=ImgUtils.createFontPic(ttf,font.getCode()+"/", appRecord.getAppName());
			AppFont appFont=new AppFont();
			appFont.setAppFontId(font.getAppFontId());
			appFont.setAppFontPic(picInfo.getPicUrl());
			appFonts.add(appFont);
		}
		if(!appFonts.isEmpty()){
			appFontMapper.updateBatchByPrimaryKeySelective(appFonts);
		}
		appMapper.updateByPrimaryKeySelective(appRecord);
	}

	private void add(App app){
		app.setAppKey(RandomStringUtils.randomAlphabetic(32));
		app.setAppSecret(RandomStringUtils.randomAlphabetic(32));
		
		Developer developer = developerMapper.selectByPrimaryKey(app.getDeveloperId());
		if(developer == null){
			throw new ApplicationException(ReturnCode.APP_NOT_EXIST);
		}
		AppExample appNameExample=new AppExample();
		appNameExample.createCriteria().andDeveloperIdEqualTo(developer.getDeveloperId()).andAppNameEqualTo(app.getAppName()).andIsDeletedEqualTo(FontTypeEnum.NOT_DELETED.getType());
		if(appMapper.selectByExample(appNameExample).size()>0){
			throw new ApplicationException(ReturnCode.APP_NAME_EXIST);
		}
		AppExample example = new AppExample();
		example.createCriteria().andDeveloperIdEqualTo(app.getDeveloperId()).andIsDeletedEqualTo(FontTypeEnum.NOT_DELETED.getType());
		List<App> list = appMapper.selectByExample(example);
		int num = 0;
		if(list!=null){
			num = list.size();
		}
		
		if(num>developer.getMaxAppNum()){
			throw new ApplicationException(ReturnCode.APP_NUM_LIMIT);
		}
		//查询所有字体
		FontExample fontExample=new FontExample();
		fontExample.createCriteria().andStatusEqualTo((byte)0).andIsDeletedEqualTo((byte)0);
		List<FontDetailVo> fonts=fontExMapper.findFontDetailAll();
		appMapper.insertSelective(app);
		//给应用添加字体
		List<AppFont> appFonts=new ArrayList<>();
		for(FontDetailVo font:fonts){
			AppFont record=new AppFont();
			record.setAppId(app.getAppId());
			record.setFontId(font.getFontId());
			record.setAuthzDate(new Date());
			record.setCreateBy(0);
			record.setCreateTime(new Date());
			record.setFontVersionId(font.getCurrFontVersionId());
			//计算授权过期时间
			record.setUnauthzDate(FontCloudDateUtil.getUnauthzDateFormNow());
			record.setStatus((byte)1);
			record.setUpdateBy(0);
			record.setUpdateTime(new Date());
			
			//创建字体的图片
			String ttf=font.getTtfDownloadUrl();
			ttf=ttf.substring(ttf.indexOf("/download/")+10);
			ImgUtils.PictureInfo picInfo=ImgUtils.createFontPic(ttf,font.getCode()+"/", app.getAppName());
			record.setAppFontPic(picInfo.getPicUrl());
			appFonts.add(record);
		}
		appFontMapper.insertBatchSelective(appFonts);
	}

	@Override
	public List<App> findAppsByDeveloperId(Integer developerId) {
		AppExample example = new AppExample();
		example.createCriteria().andDeveloperIdEqualTo(developerId).andIsDeletedEqualTo((byte)0);
		List<App> list = appMapper.selectByExample(example);
		return list;
	}

	@Override
	public AppDetailResponse findAppDetail(Integer developerId,String appKey) {
		AppDetailResponse resp = new AppDetailResponse();
		AppExample example = new AppExample();
		example.setOrderByClause("create_time desc");
		example.createCriteria().andAppKeyEqualTo(appKey).andDeveloperIdEqualTo(developerId).andIsDeletedEqualTo((byte)0);
		List<App> resultList = appMapper.selectByExample(example);
		if(resultList == null || resultList.size() < 1){
			return resp;
		}
		App app = resultList.get(0);
		BeanUtils.copyProperties(app, resp);
		return resp;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteApp(Integer developerId, Integer appid) throws ControllerException {
		//验证用户应用
		App app=appMapper.selectByPrimaryKey(appid);
		if(app==null||app.getDeveloperId()!=developerId||app.getIsDeleted()==FontTypeEnum.DELETED.getType()){
			throw new ControllerException(ReturnCode.APP_NOT_EXIST);
		}
		//删除对应字体
		AppFont appFont=new AppFont();
		appFont.setIsDeleted(FontTypeEnum.DELETED.getType());
		AppFontExample appFontExample=new AppFontExample();
		appFontExample.createCriteria().andAppIdEqualTo(app.getAppId());
		appFontMapper.updateByExampleSelective(appFont, appFontExample);
		//删除应用
		app.setIsDeleted(FontTypeEnum.DELETED.getType());
		appMapper.updateByPrimaryKeySelective(app);
	}

	
}
