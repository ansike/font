package com.fd.font.cloud.font.vo;

import java.util.List;
import java.util.Map;

public class FontDetailResponse {

	private String id;
	
	private String name;
	
	private String description;
	
	private String author;
	
	private String version;
	
	private String appFontPic;
	
	private List<Map<String, String>> icons;
	
	private FontDownloadLinks links;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Map<String, String>> getIcons() {
		return icons;
	}

	public void setIcons(List<Map<String, String>> icons) {
		this.icons = icons;
	}

	public FontDownloadLinks getLinks() {
		return links;
	}

	public void setLinks(FontDownloadLinks links) {
		this.links = links;
	}

	public String getAppFontPic() {
		return appFontPic;
	}

	public void setAppFontPic(String appFontPic) {
		this.appFontPic = appFontPic;
	}
	
	
}
