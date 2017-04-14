package com.fd.font.cloud.developer.vo;

import java.util.List;

import com.fd.font.cloud.app.entity.AppFont;

public class OperateRequest {

	private String appKey;
	
	private Integer appId;
	
	
	private List<AppFont> fonts;


	public String getAppKey() {
		return appKey;
	}


	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}


	public Integer getAppId() {
		return appId;
	}


	public void setAppId(Integer appId) {
		this.appId = appId;
	}


	public List<AppFont> getFonts() {
		return fonts;
	}


	public void setFonts(List<AppFont> fonts) {
		this.fonts = fonts;
	}
	
	
}
