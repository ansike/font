package com.fd.font.cloud.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fd.font.cloud.app.entity.App;
import com.fd.font.cloud.app.service.AppService;
import com.fd.font.cloud.base.ApplicationException;
import com.fd.font.cloud.base.BaseController;
import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.base.ControllerResult;
import com.fd.font.cloud.base.ReturnCode;
import com.fd.font.cloud.developer.consant.AppTypeEnum;
import com.fd.font.cloud.developer.vo.AppDetailResponse;
import com.fd.font.cloud.developer.vo.AppTypeResponse;

@RestController
public class AppController extends BaseController {

	Logger _log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AppService appService;

	/**
	 * 创建|修改应用
	 * 
	 * @param app
	 * @return
	 */
	@RequestMapping(value = "/app", method = RequestMethod.POST)
	public ControllerResult upsert(App app) {
		try {
			app.setDeveloperId(getDeveloper().getDeveloperId());
			try {
				appService.upsert(app);
			} catch (ApplicationException ae) {
				_log.error(ae.getMessage());
				return ae.getResult();
			} catch (Exception e) {
				_log.error(e.getMessage(), e);
				return faile();
			}
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}

	}
	@RequestMapping(value = "/app/deleteApp", method = RequestMethod.POST)
	public ControllerResult deleteApp() {
		try {
			appService.deleteApp(getDeveloper().getDeveloperId(),getParamNotNullInteger("appid"));
			return success();
		} catch (ControllerException e) {
			return e.getResult();
		}

	}

	/**
	 * 应用类型列表(字典数据)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/app/appTypes", method = RequestMethod.GET)
	public ControllerResult appTypes() {
		ControllerResult result = new ControllerResult(ReturnCode.SUCCESS);
		AppTypeEnum[] vals = AppTypeEnum.values();
		List<AppTypeResponse> list = new ArrayList<>(vals.length);
		for (AppTypeEnum e : vals) {
			AppTypeResponse resp = new AppTypeResponse();
			resp.setKey(e.getKey());
			resp.setValue(e.getValue());
			list.add(resp);
		}
		result.setData(list);

		return result;
	}

	/**
	 * 用户应用列表
	 * 
	 * @param file
	 * @return
	 */
    @RequestMapping(value = "/apps", method = RequestMethod.GET)
    public ControllerResult list() {
        List<App> list;
        try {
            list = appService.findAppsByDeveloperId(getDeveloper().getDeveloperId());
            return success(list);
        } catch (ControllerException e) {
            return e.getResult();
        }
    }


    /**
	 * 应用详情
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/app/appDetail", method = RequestMethod.GET)
	public ControllerResult detail() {
		try {
			AppDetailResponse resp = appService.findAppDetail(getDeveloper().getDeveloperId(), getParamNotNull("appKey"));
			AppTypeEnum[] vals = AppTypeEnum.values();
			for (AppTypeEnum e : vals) {
				if(e.getKey().byteValue()==resp.getAppType()){
					resp.setAppTypeValue(e.getValue());
					break;
				}
			}
			return success(resp);
		} catch (ControllerException e) {
			return e.getResult();
		}
	}

}
