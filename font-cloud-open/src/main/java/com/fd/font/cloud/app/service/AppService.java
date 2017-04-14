package com.fd.font.cloud.app.service;

import java.util.List;

import com.fd.font.cloud.app.entity.App;
import com.fd.font.cloud.base.ApplicationException;
import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.developer.vo.AppDetailResponse;

public interface AppService {

	public void upsert(App app) throws ApplicationException;

	public List<App> findAppsByDeveloperId(Integer developerId);

	public AppDetailResponse findAppDetail(Integer developerId,String appKey);

	public void deleteApp(Integer developerId, Integer appid) throws ControllerException;
}
