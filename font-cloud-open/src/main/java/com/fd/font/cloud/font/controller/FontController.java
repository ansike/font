package com.fd.font.cloud.font.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fd.font.cloud.app.entity.App;
import com.fd.font.cloud.app.entity.AppFont;
import com.fd.font.cloud.app.service.AppFontService;
import com.fd.font.cloud.app.service.AppService;
import com.fd.font.cloud.base.BaseController;
import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.base.ControllerResult;
import com.fd.font.cloud.developer.entity.Developer;
import com.fd.font.cloud.font.service.FontService;
import com.fd.font.cloud.font.service.FontTagService;
import com.fd.font.cloud.font.service.FontVersionPicService;
import com.fd.font.cloud.font.vo.FontDetailVo;
import com.fd.font.cloud.font.vo.FontVo;
import com.fd.font.cloud.tag.entity.Tag;
import com.fd.font.cloud.util.HttpUtil;
import com.fd.font.cloud.util.PropertiesUtil;
import com.github.pagehelper.PageInfo;

/**
 * 字体管理中心
 * 
 * @author XiRuiQiang
 *
 */
@Controller
@RequestMapping("/font")
public class FontController extends BaseController {

	@Autowired
	private FontService fontService;
	@Autowired
	private FontTagService fontTagService;
	@Autowired
	private AppService appService;
	@Autowired
	private AppFontService appFontService;

	@Autowired
	private FontVersionPicService fontVersionPicService;

	private static final Logger _log = LoggerFactory.getLogger(FontController.class);

	@RequestMapping("/fontCenter/fontcenter")
	public String fontCenterIndex(Model resultData, Integer pageNum, FontVo params, Tag tag) {
		_log.info(getParams().toString());
		if (pageNum == null) {
			pageNum = 1;
		}
		resultData.addAttribute("tags", fontTagService.findList(tag));
		PageInfo<FontVo> page = fontService.findFontByPage(pageNum, 15, getParams());
		resultData.addAttribute("page", page);
		return "template/fontCenter/fontcenter";
	}
	@RequestMapping("findList")
	@ResponseBody
	public ControllerResult findList(Integer pageNum,Integer pageSize){
		if(pageSize==null||pageSize>15)pageSize=15;
		if(pageNum==null||pageNum<1)pageNum=1;
		return success(fontService.findH5FontByPage(pageNum, pageSize, getParams()));
	}

	@RequestMapping("/fontCenter/fontDetail")
	public String fontCenterDetail(Model resultData) {
		Map<String, String> params = getParams();
		try {
			Integer fontId = Integer.parseInt(getParamNotNull("fontId"));
			Developer d = null;
			try {
				d = getDeveloper();
				// 获取授权详情
				List<AppFont> list = appFontService.findAppFontByDeveloper(d.getDeveloperId(), null, fontId);
				resultData.addAttribute("authzFonts", list);
				resultData.addAttribute("appSize", appService.findAppsByDeveloperId(d.getDeveloperId()).size());
			} catch (Exception e) {
				// 未登录
			}
			FontDetailVo fontDetail = fontService.findFontDetail(params);
			resultData.addAttribute("detail", fontDetail);
			// 查询图片信息
			resultData.addAttribute("pics", fontVersionPicService.findFontPicsByFontId(fontId,(byte)0));
		} catch (ControllerException e) {
		}
		return "template/fontCenter/fontDetail";
	}

	/**
	 * 下载字体前调用保存下载次数接口
	 * 
	 * @author XiRuiQiang
	 * @return
	 */
	@RequestMapping("/downFontAction")
	@ResponseBody
	public ControllerResult downFontAction() {
		String fontVersionId = getParam("fontVersionId");
		String fontId = getParam("fontId");
		if (fontVersionId == null && fontId == null) {
			return faile("fontVersionId或者fontId不能为空!");
		}
		try {
			getDeveloper();
			String url=null;
			if (fontVersionId != null) {
				url=fontService.addDownNumByFontVersionId(Integer.parseInt(fontVersionId)).getTtfDownloadUrl();
			} else if (fontId != null) {
				url=fontService.addDownNumByFontId(Integer.parseInt(fontId)).getTtfDownloadUrl();
			} else {
				return faile();
			}
			url=url.toLowerCase();
			String domain=ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
			String urlPath="/font/download/";
			if(url.startsWith("http")){
				url=url.substring(url.indexOf("download/")+9);
				url=domain+urlPath+url;
			}else if(url.startsWith("/download")){
				url=domain+urlPath+url.substring(10);
			}else if(url.startsWith("download")){
				url=domain+urlPath+url.substring(9);
			}
			_log.info(url);
			return success(url);
		} catch (ControllerException e) {
			return e.getResult();
		}
	}
	@RequestMapping("download/{ttfpath}/{fontname}")
	public String downloadTtf(HttpServletResponse response,@PathVariable String ttfpath,@PathVariable String fontname){
		String path=PropertiesUtil.getContextProperty("ttf.path");
		try {
			getDeveloper();
			HttpUtil.downloadLocal(path+ttfpath+File.separator+fontname+".ttf", response);
			return null;
		} catch (FileNotFoundException e) {
			return "error";
		} catch (ControllerException e) {
			return "redirect:/font/fontCenter/fontcenter";
		}
	}

	/**
	 * 联想查询
	 */
	@RequestMapping("/findThink")
	public @ResponseBody ControllerResult findThink() {
		try {
			String search = getParamNotNull("search");
			Map<String, Set<String>> result = new HashMap<>();
			result.put("names", fontService.findNameThink(search));
			result.put("authors", fontService.findAuthorThink(search));
			// result.put("tags", fontTagService.findThink(search));
			return success(result);
		} catch (ControllerException e) {
			return e.getResult();
		}
	}

	/**
	 * 用户应用列表
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/apps", method = RequestMethod.GET)
	public @ResponseBody ControllerResult Applist() {
		List<App> list;
		List<AppFont> appFontList;
		List<App> appList = new ArrayList<App>();
		List<App> appNoList = new ArrayList<App>();
		Map<String, Object> appMap = new HashMap<>();
		try {
			// 所有应用
			list = appService.findAppsByDeveloperId(getDeveloper().getDeveloperId());
			// 查询字体对应的应用
			appFontList = appFontService.findAppsByFontId(getParamNotNullInteger("fontId"), null);
			List<Integer> havedAppids = new ArrayList<>();
			for (AppFont af : appFontList) {
				havedAppids.add(af.getAppId());
			}
			for (App app : list) {
				if (havedAppids.contains(app.getAppId())) {
					appList.add(app);
				} else {
					appNoList.add(app);
				}
			}
			Integer developerId=getDeveloper().getDeveloperId();
			Map<String,Object> appNums=appFontService.findAppNums(developerId);
			
			appMap.put("authApp", appList);
			appMap.put("unauthApp", appNoList);
			appMap.put("apps", list);
			appMap.put("appNums", appNums);
			return success(appMap);
		} catch (ControllerException e) {
			return e.getResult();
		}
	}

}
