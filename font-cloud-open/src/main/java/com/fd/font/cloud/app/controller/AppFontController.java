package com.fd.font.cloud.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fd.font.cloud.app.entity.App;
import com.fd.font.cloud.app.entity.AppFont;
import com.fd.font.cloud.app.service.AppFontService;
import com.fd.font.cloud.base.BaseController;
import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.base.ControllerResult;
import com.fd.font.cloud.base.FontCloudConstant;
import com.fd.font.cloud.base.ReturnCode;
import com.fd.font.cloud.developer.vo.AppFontResponse;
import com.fd.font.cloud.developer.vo.OperateRequest;
import com.fd.font.cloud.util.FontCloudDateUtil;
import com.fd.font.cloud.util.SendSmsTool;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@RestController
public class AppFontController extends BaseController {
	
	Logger _log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppFontService appFontService;
	/**
	 * 查询应用字体--安全验证
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("appfont/findAppFontList")
	@ResponseBody
	public ControllerResult findAppFontList(Integer pageNum,Integer pageSize){
		if(pageSize==null||pageSize>15)pageSize=15;
		if(pageNum==null||pageNum<1)pageNum=1;
		try {
			String mobile=getParamNotNull("mobile");
			String appKey=getParamNotNull("appKey");
			String secretMD5=getParamNotNull("secretMD5");
			//验证用户
			App app=appFontService.checkAppUser(mobile,appKey,secretMD5);
			if(app!=null){
				//更具app查询app已授权的字体
				PageInfo<AppFontResponse> apps=appFontService.findAppFontsByPage(pageNum,pageSize,app);
				return success(apps);
			}
		} catch (ControllerException e) {
			return e.getResult();
		}
		return faile();
	}
	/**
	 * 应用字体操作
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/appFonts" , method = RequestMethod.POST)
	public ControllerResult operate(@RequestBody OperateRequest request){
		if(StringUtils.isEmpty(request.getAppKey())){
			return faile(ReturnCode.FAILE);
		}
		try {
			getDeveloper();
			int count = appFontService.operate(request);
			_log.info("effect row num:{}",count);
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}
	}
	/**
	 * 查询所有字体对应的应用状态
	 * @author XiRuiQiang
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value="/appfont/fontList")
	public ControllerResult unauthzFonts(Integer pageNum){
		try {
			getDeveloper();
			Map<String,Object> params=new HashMap<>();
			params.put("developerId", getDeveloper().getDeveloperId());
			params.put("appId", getParamNotNullInteger("appId"));
			params.put("status", getParam("status"));//只查询授权的字体信息
			return success(appFontService.fontList(params, pageNum));
		}catch (ControllerException e) {
			return e.getResult();
		}
	}
	/**
	 * 查询未授权字体
	 * @author XiRuiQiang
	 * @return
	 */
	@RequestMapping("/appfont/findUnauthzFonts")
	public ControllerResult findUnauthzFont(Integer pageNum){
		//暂时授权的字体
		String authzFonts=getParam("authzFonts");
		try {
			Integer appId = getParamNotNullInteger("appId");
			Integer developerId=getDeveloper().getDeveloperId();
			return success(appFontService.unauthzFonts(pageNum,developerId,appId,authzFonts));
		} catch (ControllerException e) {
			return e.getResult();
		}
	}
	/**
	 * 授权字体到多个应用中---其他应用直接取消授权--如果appids为All则全部取消授权
	 * @author XiRuiQiang
	 * @return
	 */
	@RequestMapping(value="appfont/authFontToApps",method=RequestMethod.POST)
	public ControllerResult authFontToApps(){
		try {
			Integer fontid=getParamNotNullInteger("fontid");
			String appidstr=getParamNotNull("appids");
			List<Integer> appids=new ArrayList<>();
			if(appidstr.equalsIgnoreCase("cleanAll")){
				//取消此字体在所有应用中的授权--appids.size=0
			}else{
				for(String appId:appidstr.split(",")){
					if(!StringUtils.isEmpty(appId)){
						appids.add(Integer.parseInt(appId));
					}
				}
			}
			Integer developerid=getDeveloper().getDeveloperId();
			appFontService.authFontToApps(developerid,fontid,appids);
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}
	}
	/**
	 * 重新授权
	 * @return
	 */
	@RequestMapping("appfont/reAuth")
	public ControllerResult reAuth(@RequestParam("fontids")Integer[] fontids){
		try {
			Integer developerId=getDeveloper().getDeveloperId();
			Integer appId=getParamNotNullInteger("appid");
			//验证用户应用
			List<AppFont> sum=appFontService.reAuth(developerId,appId,Arrays.asList(fontids));
			return success(sum);
		} catch (ControllerException e) {
			return e.getResult();
		}
	}
	/**
	 * 授权和取消授权
	 * @author XiRuiQiang
	 * @return
	 */
	@RequestMapping(value="appfont/authOrUnFont",method=RequestMethod.POST)
	public ControllerResult authOrUnFont(){
		try {
			Integer status=getParamNotNullInteger("status");
			String appidstr=getParamNotNull("appids");
			String fontidstr=getParamNotNull("fontids");
			String type=getParam("type");//reauth重新授权
			List<Integer> fontids=new ArrayList<>();
			for(String fontId:fontidstr.split(",")){
				if(!StringUtils.isEmpty(fontId)){
					fontids.add(Integer.parseInt(fontId));
				}
			}
			List<Integer> appids=new ArrayList<>();
			for(String appId:appidstr.split(",")){
				if(!StringUtils.isEmpty(appId)){
					appids.add(Integer.parseInt(appId));
				}
			}
			appFontService.authOrUnFont(getDeveloper().getDeveloperId(), appids, fontids, status,type);
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}
	}
	/**
	 * 查询应用下的所有授权字体
	 * @author XiRuiQiang
	 * @return
	 */
	@RequestMapping(value="appfont/findAppFonts")
	public ControllerResult findAppFonts(){
		try {
			List<AppFontResponse> fonts=appFontService.findAppFonts(getDeveloper().getDeveloperId(),getParamNotNullInteger("appid"));
			return success(fonts);
		} catch (ControllerException e) {
			return e.getResult();
		}
	}
	/**
	 * 定时器发送字体即将过期短信---1条/天/手机号--提前半个月发送一次，提前一天发送一次，并在半个月时修改状态为重新授权
	 * @author XiRuiQiang
	 * @return
	 */
	@RequestMapping("appfont/sendSmsFontUnauth")
	public ControllerResult sendSmsFontUnauth(){
		try{
			Integer[] days={1,15};
			Map<String,String> result=new HashMap<>();
			for(Integer day:days){
				String redisKey=FontCloudConstant.SEND_SMS_FONT_AUTH+"_"+day+"_"+FontCloudDateUtil.getNowDateStr();
				//查询今天是否发送过
				String sendSmsMobiles=getStringRedis().opsForValue().get(redisKey);
				//没有发送时进行短信发送
				if(StringUtil.isEmpty(sendSmsMobiles)){
					//查询即将过期的所有字体
					List<Map<String,String>> appFonts =appFontService.findUnauthing(day);
					List<String> mobiles=new ArrayList<>();
					for(Map<String,String> appFont:appFonts){
						mobiles.add(appFont.get("mobile"));
					}
					String mobilesStr="";
					for(String mobile:mobiles){
						mobilesStr+=mobile+",";
						//发送短信
						_log.info("为手机号:"+mobile+"发送授权过期短信");
					}
					if(!StringUtil.isEmpty(mobilesStr)){
						SendSmsTool.sendSms(SendSmsTool.CHECK_FONT_AUTH,mobilesStr,"");
					}
					_log.info("授权过期短信发送完成:"+mobiles.toString());
					if(mobiles.size()>0){
						getStringRedis().opsForValue().set(redisKey, Json.toJson(mobiles));
					}
					result.put("send_"+day, mobilesStr);
				}else{
					_log.info("已经为这些手机发送过授权过期短信了:"+sendSmsMobiles);
					result.put("havedSend_"+day, sendSmsMobiles);
				}
			}
			return success(result);
		}catch(ControllerException e){
			return e.getResult();
		}
	}
}
