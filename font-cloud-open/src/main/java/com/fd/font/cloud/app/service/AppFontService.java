package com.fd.font.cloud.app.service;

import java.util.List;
import java.util.Map;

import com.fd.font.cloud.app.entity.App;
import com.fd.font.cloud.app.entity.AppFont;
import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.developer.vo.AppFontResponse;
import com.fd.font.cloud.developer.vo.OperateRequest;
import com.fd.font.cloud.developer.vo.UnauthzFont;
import com.github.pagehelper.PageInfo;

public interface AppFontService {

	int operate(OperateRequest request);
	public List<AppFont> findAppFontByDeveloper(Integer developerId,Integer appid,Integer fontId);
	/**
	 * 查询已经授权的字体应用
	 * @author XiRuiQiang
	 * @param fontId
	 * @param appId
	 * @return
	 */
	public List<AppFont> findAppsByFontId(Integer fontId,Integer appId);
	PageInfo<UnauthzFont> fontList(Map<String,Object> params, Integer pageNum);
	public void authOrUnFont(Integer developerId,List<Integer> appIds,List<Integer> fontIds,Integer status,String type) throws ControllerException;
	void authFontToApps(Integer developerid, Integer fontid, List<Integer> appids) throws ControllerException;
	List<AppFontResponse> findAppFonts(Integer developerId, Integer paramNotNullInteger);
	List<Map<String,String>> findUnauthing(Integer day);
	Object unauthzFonts(Integer pageNum,Integer developerId, Integer appId, String authzFonts);
	Map<String, Object> findAppNums(Integer developerId);
	/**
	 * 验证应用用户
	 * @param mobile
	 * @param appKey
	 * @param secretMD5
	 * @return
	 * @throws ControllerException 
	 */
	App checkAppUser(String mobile, String appKey, String secretMD5) throws ControllerException;
	PageInfo<AppFontResponse> findAppFontsByPage(Integer pageNum,Integer pageSize,App app);
	List<AppFont> reAuth(Integer developerId, Integer appId, List<Integer> fontids) throws ControllerException;
}
