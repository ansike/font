package com.fd.font.cloud.font.vo;

public class FontDetailVo extends FontVo {
	private static final long serialVersionUID = 1L;

	private String ttfDownloadUrl;
	/**
	 * 授权状态
	 */
	private Integer authStatus;

	public String getTtfDownloadUrl() {
		return ttfDownloadUrl;
	}

	public void setTtfDownloadUrl(String ttfDownloadUrl) {
		this.ttfDownloadUrl = ttfDownloadUrl;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

}
