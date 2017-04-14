package com.fd.font.cloud.developer.vo;

import java.util.List;

public class AppDetailResponse {
	
	
	private String iconUrl;
	private String appName;
	private String appKey;
	private String appSecret;
	private Byte appType ;
	private String appTypeValue;
	private Integer maxFontNum ;
	private Integer appId;
	private Integer updateFrequency;

	private List<AppFontResponse> fonts;

	public List<AppFontResponse> getFonts() {
		return fonts;
	}

	public void setFonts(List<AppFontResponse> fonts) {
		this.fonts = fonts;
	}

	public Integer getUpdateFrequency() {
		return updateFrequency;
	}

	public void setUpdateFrequency(Integer updateFrequency) {
		this.updateFrequency = updateFrequency;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public Byte getAppType() {
		return appType;
	}

	public void setAppType(Byte appType) {
		this.appType = appType;
	}

	public String getAppTypeValue() {
		return appTypeValue;
	}

	public void setAppTypeValue(String appTypeValue) {
		this.appTypeValue = appTypeValue;
	}

	public Integer getMaxFontNum() {
		return maxFontNum;
	}

	public void setMaxFontNum(Integer maxFontNum) {
		this.maxFontNum = maxFontNum;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
}
