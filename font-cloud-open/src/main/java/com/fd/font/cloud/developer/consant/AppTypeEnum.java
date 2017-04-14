package com.fd.font.cloud.developer.consant;

public enum AppTypeEnum {

	LIFE(1,"生活"),
	TOOL(2,"工具"),
	SOCIAL(3,"社交"),
	TRAVEL(4,"旅游"),
	MUSIC(5,"音乐"),
	READ(6,"阅读"),
	NEWS(7,"新闻"),
	EDU(8,"教育"),
	NAV(9,"导航"),
	AMUSEMENT(10,"娱乐")	,
	THOER(11,"其他")
	;
	
	private Integer key;
	
	private String value;

	private AppTypeEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
