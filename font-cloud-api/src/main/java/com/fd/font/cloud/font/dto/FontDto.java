package com.fd.font.cloud.font.dto;

public class FontDto {

	private String code;
    private String name;
    private String introduction;
    private String ttfDownloadUrl;
    private String versionName;
    private Integer width;
    private Integer height;
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
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getTtfDownloadUrl() {
		return ttfDownloadUrl;
	}
	public void setTtfDownloadUrl(String ttfDownloadUrl) {
		this.ttfDownloadUrl = ttfDownloadUrl;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	private String picUrl;
}
