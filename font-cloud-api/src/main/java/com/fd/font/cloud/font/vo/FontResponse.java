package com.fd.font.cloud.font.vo;

import java.util.List;
import java.util.Map;

public class FontResponse {

	private String id;
	
	private String name;
	
	private String description;
	
	//FIXME　Spring HATEOAS 　
	private FontDetailLinks links;
	
	private List<Map<String, String>> icons;

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

	public FontDetailLinks getLinks() {
		return links;
	}

	public void setLinks(FontDetailLinks links) {
		this.links = links;
	}

	public List<Map<String, String>> getIcons() {
		return icons;
	}

	public void setIcons(List<Map<String, String>> icons) {
		this.icons = icons;
	}

	
}
