package com.fd.font.cloud.developer.vo;

import java.io.Serializable;
import java.util.Date;

public class UnauthzFont implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer appFontId;
	private Integer fontId;
	private String code;
	private String name;
	private String picUrl;
	private Date authzDate;
	private Date ubauthzDate;
	private Integer fontSize;
	private Integer status;
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
	public Date getUbauthzDate() {
		return ubauthzDate;
	}
	public void setUbauthzDate(Date ubauthzDate) {
		this.ubauthzDate = ubauthzDate;
	}
	public Integer getFontSize() {
		return fontSize;
	}
	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
