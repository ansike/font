package com.fd.font.cloud.developer.vo;

public class AccessTokenRequest {

	private String appkey;
	
	private String time;
	
	private String mac;
	
	private String partnerUserId;

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	

	public String getPartnerUserId() {
		return partnerUserId;
	}

	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}

	@Override
	public String toString() {
		return "AccessTokenRequest [appkey=" + appkey + ", time=" + time + ", mac=" + mac + ", partnerUserId="
				+ partnerUserId + "]";
	}

}
