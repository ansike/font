package com.fd.font.cloud.developer.vo;

import java.util.Date;

public class AppFontResponse {

	private Integer appFontId;
	private Integer fontId;
	private String code;
	private String name;
	private String picUrl;
	private String h5PicUrl;
	private String ttfDownloadUrl;
	private Date authzDate;
	private Date unauthzDate;
	private String status;
	
	
	public String getTtfDownloadUrl() {
		return ttfDownloadUrl;
	}
	public void setTtfDownloadUrl(String ttfDownloadUrl) {
		this.ttfDownloadUrl = ttfDownloadUrl;
	}
	public String getH5PicUrl() {
		return h5PicUrl;
	}
	public void setH5PicUrl(String h5PicUrl) {
		this.h5PicUrl = h5PicUrl;
	}
	public Integer getAppFontId() {
		return appFontId;
	}
	public void setAppFontId(Integer appFontId) {
		this.appFontId = appFontId;
	}
	public Integer getFontId() {
		return fontId;
	}
	public void setFontId(Integer fontId) {
		this.fontId = fontId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Date getAuthzDate() {
		return authzDate;
	}
	public void setAuthzDate(Date authzDate) {
		this.authzDate = authzDate;
	}
	public Date getUnauthzDate() {
		return unauthzDate;
	}
	public void setUnauthzDate(Date unauthzDate) {
		this.unauthzDate = unauthzDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
