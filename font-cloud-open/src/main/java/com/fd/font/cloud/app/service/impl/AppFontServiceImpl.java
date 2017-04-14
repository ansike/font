package com.fd.font.cloud.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fd.font.cloud.app.service.AppFontService;
import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.base.FontTypeEnum;
import com.fd.font.cloud.base.ReturnCode;
import com.fd.font.cloud.developer.dao.AppFontExMapper;
import com.fd.font.cloud.developer.entity.Developer;
import com.fd.font.cloud.developer.service.DeveloperService;
import com.fd.font.cloud.developer.vo.AppFontResponse;
import com.fd.font.cloud.developer.vo.OperateRequest;
import com.fd.font.cloud.developer.vo.UnauthzFont;
import com.fd.font.cloud.font.dao.FontMapper;
import com.fd.font.cloud.font.entity.Font;
import com.fd.font.cloud.font.entity.FontExample;
import com.fd.font.cloud.util.FontCloudDateUtil;
import com.fd.font.cloud.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("appFontService")
public class AppFontServiceImpl implements AppFontService {
	@Autowired
	private DeveloperService developerService;
	@Autowired
	private AppFontMapper appFontMapper;
	
	@Autowired
	private AppMapper appMapper;
	
	@Autowired
	private FontMapper fontMapper;
	
	@Autowired
	private AppFontExMapper appFontExMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int operate(OperateRequest request) {
		int count = 0;
		List<AppFont> list = request.getFonts();

		//FIXME mybatis 批量生成语句效率更高
		for(AppFont appFont : list){
			if(appFont.getAppFontId()!=null){
				appFont.setIsDeleted((byte)1);
				count = count + appFontMapper.updateByPrimaryKeySelective(appFont);
			}else{
				Font font = fontMapper.selectByPrimaryKey(appFont.getFontId());
				appFont.setAppId(request.getAppId());
				appFont.setAuthzDate(new Date());
				appFont.setUnauthzDate(new Date());
				appFont.setFontVersionId(font.getCurrFontVersionId());
				count = count + appFontMapper.insertSelective(appFont);
			}
		}
		return count;
	}
	@Override
	public List<AppFont> findAppsByFontId(Integer fontId,Integer appId) {
		AppFontExample example = new AppFontExample();
		AppFontExample.Criteria criteria=example.createCriteria();
		if(appId!=null){
			criteria.andAppIdEqualTo(appId);
		}
		criteria.andStatusEqualTo((byte)1);
		criteria.andFontIdEqualTo(fontId);
		criteria.andIsDeletedEqualTo((byte)0);
		List<AppFont> list = appFontMapper.selectByExample(example);
		return list;
	}
	@Override
	public PageInfo<UnauthzFont> fontList(Map<String,Object> params, Integer pageNum) {
		if(pageNum==null){
			pageNum=1;
		}
		Integer pageSize=15;
		PageHelper.startPage(pageNum, pageSize);
		List<UnauthzFont> list = appFontExMapper.fontList(params);
	    return new PageInfo<UnauthzFont>(list);
		
	}
	/**
	 * 查询用户所有授权的字体
	 */
	@Override
	public List<AppFont> findAppFontByDeveloper(Integer developerId,Integer appid,Integer fontId) {
		AppExample appExample=new AppExample();
		appExample.createCriteria().andDeveloperIdEqualTo(developerId).andIsDeletedEqualTo((byte)0);
		List<App> apps=appMapper.selectByExample(appExample);
		List<Integer> appids=new ArrayList<Integer>();
		for(App a:apps){
			appids.add(a.getAppId());
		}
		AppFontExample afs=new AppFontExample();
		AppFontExample.Criteria criteria=afs.createCriteria();
		criteria.andAppIdIn(appids).andIsDeletedEqualTo((byte)0).andStatusEqualTo((byte)1);
		if(appid!=null){
			criteria.andAppIdEqualTo(appid);
		}
		if(fontId!=null){
			criteria.andFontIdEqualTo(fontId);
		}
		return appFontMapper.selectByExample(afs);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void authOrUnFont(Integer developerId, List<Integer> appIds, List<Integer> fontIds, Integer status,String type) throws ControllerException {
		//验证用户应用是否为用户应用
		AppExample appExample=new AppExample();
		appExample.createCriteria().andDeveloperIdEqualTo(developerId).andAppIdIn(appIds).andIsDeletedEqualTo((byte)0);
		List<App> appList=appMapper.selectByExample(appExample);
		//ID数量必须和查询结果匹配
		if(appList.size()>0&&appList.size()==appIds.size()){
			//为每个应用添加字体
			for(App app:appList){
				//查询字体是否存在
				FontExample fontExample=new FontExample();
				fontExample.createCriteria().andFontIdIn(fontIds).andIsDeletedEqualTo((byte)0);
				List<Font> fontList=fontMapper.selectByExample(fontExample);
				//ID数量必须和查询结果匹配
				if(fontList.size()>0 && fontList.size()==fontIds.size()){
					//判断状态是否正确
					AppFontExample appFontExample=new AppFontExample();
					appFontExample.createCriteria().andAppIdEqualTo(app.getAppId()).andFontIdIn(fontIds).andIsDeletedEqualTo((byte)0);
					List<AppFont> appFontList=appFontMapper.selectByExample(appFontExample);
					if(status==1){
						//判断已经授权的和将要授权的字体数量是否大于20
						AppFontExample appFontExampleHaved=new AppFontExample();
						appFontExampleHaved.createCriteria().andAppIdEqualTo(app.getAppId())
							.andIsDeletedEqualTo((byte)0).andStatusEqualTo(status.byteValue());
						List<AppFont> appFontListHaved=appFontMapper.selectByExample(appFontExampleHaved);
						int havedNum=appFontListHaved.size()+fontIds.size();
						if(havedNum>20){
							throw new ControllerException(ReturnCode.APP_NUM_LIMIT);
						}
						//更新已存在的数据
						List<Integer> havedIds=new ArrayList<Integer>();
						for(AppFont af:appFontList){
							af.setStatus(status.byteValue());
							af.setUpdateBy(0);
							af.setUpdateTime(new Date());
							af.setAuthzDate(new Date());
							af.setUnauthzDate(FontCloudDateUtil.getUnauthzDateFormNow());
							appFontMapper.updateByPrimaryKeySelective(af);
							havedIds.add(af.getFontId());
						}
						//授权,插入新数据
						for(Integer fontId:fontIds){
							if(havedIds.contains(fontId)){
								continue;
							}
							AppFont record=new AppFont();
							record.setAppId(app.getAppId());
							record.setFontId(fontId);
							record.setAuthzDate(new Date());
							record.setCreateBy(0);
							record.setCreateTime(new Date());
							record.setFontVersionId(fontList.get(0).getCurrFontVersionId());
							//计算授权过期时间
							record.setUnauthzDate(FontCloudDateUtil.getUnauthzDateFormNow());
							record.setStatus(status.byteValue());
							record.setUpdateBy(0);
							record.setUpdateTime(new Date());
							appFontMapper.insertSelective(record);
						}
					}else if(status==0 && appFontList.size()>0){
						//取消授权
						AppFont record=new AppFont();
						record.setStatus(FontTypeEnum.FONT_NOT_AUTH.getType());
						appFontMapper.updateByExampleSelective(record, appFontExample);
					}else{
						throw new ControllerException(ReturnCode.FAILE,"授权状态错误");
					}
				}else{
					throw new ControllerException(ReturnCode.FAILE,"字体不存在");
				}
				
			}
		}else{
			throw new ControllerException(ReturnCode.FAILE,"应用数量不匹配");
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void authFontToApps(Integer developerid,Integer fontid, List<Integer> appids) throws ControllerException {
		//取消字体在应用中的所有授权
		if(appids.size()==0){
			AppExample unauthAppExample=new AppExample();
			unauthAppExample.createCriteria().andDeveloperIdEqualTo(developerid).andIsDeletedEqualTo(FontTypeEnum.NOT_DELETED.getType());
			List<App> unauthAppList=appMapper.selectByExample(unauthAppExample);
			for(App app:unauthAppList){
				appids.add(app.getAppId());
			}
			AppFont appFont=new AppFont();
			appFont.setStatus(FontTypeEnum.FONT_NOT_AUTH.getType());
			AppFontExample appFontExample=new AppFontExample();
			appFontExample.createCriteria().andFontIdEqualTo(fontid).andAppIdIn(appids).andStatusEqualTo(FontTypeEnum.FONT_AUTH.getType());
			appFontMapper.updateByExampleSelective(appFont, appFontExample);
			return;
		}
		//查询应用是否存在
		AppExample checkAppExample=new AppExample();
		checkAppExample.createCriteria().andDeveloperIdEqualTo(developerid).andAppIdIn(appids).andIsDeletedEqualTo(FontTypeEnum.NOT_DELETED.getType());
		List<App> checkAppList=appMapper.selectByExample(checkAppExample);
		if(checkAppList.size()!=appids.size())throw new ControllerException(ReturnCode.APP_NOT_EXIST);
		//查询字体是否存在
		FontExample fontExample=new FontExample();
		fontExample.createCriteria().andFontIdEqualTo(fontid).andIsDeletedEqualTo((byte)0);
		List<Font> fontList=fontMapper.selectByExample(fontExample);
		if(fontList.size()!=1)throw new ControllerException(ReturnCode.FONT_NOT_EXIST);
		Font font=fontList.get(0);
		
		AppExample appExample=new AppExample();
		appExample.createCriteria().andDeveloperIdEqualTo(developerid).andIsDeletedEqualTo(FontTypeEnum.NOT_DELETED.getType());
		List<App> appList=appMapper.selectByExample(appExample);
		for(App app:appList){
			AppFontExample appFontExample=new AppFontExample();
			appFontExample.createCriteria().andAppIdEqualTo(app.getAppId()).andFontIdEqualTo(fontid)
			.andStatusEqualTo(FontTypeEnum.FONT_AUTH.getType()).andIsDeletedEqualTo(FontTypeEnum.NOT_DELETED.getType());
			//查询已授权字体
			List<AppFont> appFonts=appFontMapper.selectByExample(appFontExample);
			if(appids.contains(app.getAppId())){
				appids.remove(app.getAppId());
				//授权字体到应用
				if(appFonts.size()>0){
					//已经授权的
				}else{
					//查询授权信息
					AppFontExample havedAppFontExample=new AppFontExample();
					havedAppFontExample.createCriteria().andAppIdEqualTo(app.getAppId()).andFontIdEqualTo(fontid).andIsDeletedEqualTo(FontTypeEnum.NOT_DELETED.getType());
					//有授权信息，但是没有被授权的数据
					List<AppFont> havedAppFonts=appFontMapper.selectByExample(havedAppFontExample);
					if(havedAppFonts.size()==1){
						//修改状态
						AppFont havedAppFont=havedAppFonts.get(0);
						havedAppFont.setStatus(FontTypeEnum.FONT_AUTH.getType());
						havedAppFont.setUpdateTime(new Date());
						appFontMapper.updateByPrimaryKeySelective(havedAppFont);
					}else if(havedAppFonts.size()==0){
						//插入数据
						AppFont appFont=new AppFont();
						appFont.setAppId(app.getAppId());
						appFont.setFontId(fontid);
						appFont.setStatus(FontTypeEnum.FONT_AUTH.getType());
						appFont.setFontVersionId(font.getCurrFontVersionId());
						appFont.setAuthzDate(new Date());
						appFont.setUnauthzDate(FontCloudDateUtil.getUnauthzDateFormNow());
						appFont.setUpdateBy(0);
						appFontMapper.insertSelective(appFont);
					}
				}
			}else{
				//取消授权应用
				if(appFonts.size()>0){
					//取消授权
					AppFont appFont=appFonts.get(0);
					appFont.setStatus(FontTypeEnum.FONT_NOT_AUTH.getType());
					appFontMapper.updateByPrimaryKeySelective(appFont);
				}else{
					//没有授权，不用操作
				}
			}
		}
		if(appids.size()>0){
			throw new ControllerException(ReturnCode.FAILE,"应用数量不匹配");
		}
	}
	@Override
	public List<AppFontResponse> findAppFonts(Integer developerId,Integer appid) {
		App app=new App();
		app.setDeveloperId(developerId);
		app.setAppId(appid);
		return appFontExMapper.selectAppFontByAppId(app);
	}
	@Override
	public List<Map<String,String>> findUnauthing(Integer day) {
		return appFontExMapper.selectUnauthing(day);
	}
	/**
	 * 查询未授权字体，和临时授权字体
	 */
	@Override
	public PageInfo<UnauthzFont> unauthzFonts(Integer pageNum,Integer developerId, Integer appId, String authzFonts) {
		if(pageNum==null)pageNum=1;
		PageHelper.startPage(pageNum,15);
		//已经授权的字体，带回去
		List<String> authzFontsList=new ArrayList<>();
		Map<String,Object> params=new HashMap<>();
		if(authzFonts!=null&&authzFonts.length()>0){
			for(String fontId:authzFonts.split(",")){
				authzFontsList.add(fontId);
			}
			params.put("fonts", authzFontsList);
		}
		//查询未授权字体
		params.put("appId", appId);
		params.put("developerId", developerId);
		return new PageInfo<UnauthzFont>(appFontExMapper.selectUnauthzFonts(params));
	}
	@Override
	public Map<String, Object> findAppNums(Integer developerId) {
		List<Map<String,Object>> list=appFontExMapper.selectAppNums(developerId);
		Map<String,Object> apps=new HashMap<>();
		for(Map<String,Object> m:list){
			apps.put(m.get("appId").toString(), m.get("count"));
		}
		return apps;
	}
	@Override
	public App checkAppUser(String mobile, String appKey, String secretMD5) throws ControllerException {
		//用户查询应用
		Developer user=developerService.findByMobile(mobile);
		if(user==null){
			//用户不存在
			throw new ControllerException(ReturnCode.MOBILE_NOT_EXIST);
		}
		AppExample appExample=new AppExample();
		appExample.createCriteria().andDeveloperIdEqualTo(user.getDeveloperId()).andIsDeletedEqualTo((byte)0).andAppKeyEqualTo(appKey);
		List<App> apps=appMapper.selectByExample(appExample);
		if(apps==null||apps.size()==0){
			//应用不存在
			throw new ControllerException(ReturnCode.APP_NOT_EXIST);
		}
		String appSecret=apps.get(0).getAppSecret();
		if(secretMD5.equals(MD5Util.string2MD5(mobile+appKey+appSecret))){
			//验证成功
			return apps.get(0);
		}
		return null;
	}
	@Override
	public PageInfo<AppFontResponse> findAppFontsByPage(Integer pageNum,Integer pageSize,App app) {
		PageHelper.startPage(pageNum,pageSize);
		return new PageInfo<AppFontResponse>(appFontExMapper.selectAppFontByPage(app));
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<AppFont> reAuth(Integer developerId, Integer appId, List<Integer> fontids) throws ControllerException {
		//验证用户
		AppExample appExample=new AppExample();
		appExample.createCriteria().andAppIdEqualTo(appId).andDeveloperIdEqualTo(developerId).andIsDeletedEqualTo((byte)0);
		List<App> apps=appMapper.selectByExample(appExample);
		if(apps.isEmpty())throw new ControllerException(ReturnCode.APP_NOT_EXIST);
		App app=apps.get(0);
		AppFontExample appFontExample=new AppFontExample();
		appFontExample.createCriteria().andAppIdEqualTo(app.getAppId()).andFontIdIn(fontids).andIsDeletedEqualTo((byte)0).andStatusEqualTo((byte)1);
		List<AppFont> appFonts=appFontMapper.selectByExample(appFontExample);
		if(appFonts.size()!=fontids.size()){
			throw new ControllerException(ReturnCode.FONT_NOT_EXIST);
		}
		for(AppFont appFont:appFonts){
			//更新授权时间
			if(FontCloudDateUtil.canReauth(appFont.getUnauthzDate())){
				//可以重新授权(15天内)
				appFont.setUnauthzDate(FontCloudDateUtil.getUnauthzDateFormDate(appFont.getUnauthzDate()));
			}else if(System.currentTimeMillis()-appFont.getUnauthzDate().getTime()>0){
				//过期
				appFont.setAuthzDate(new Date());
				appFont.setUnauthzDate(FontCloudDateUtil.getUnauthzDateFormNow());
			}else{
				//没过期
			}
		}
		appFontMapper.updateBatchByPrimaryKeySelective(appFonts);
		return appFonts;
	}

}
