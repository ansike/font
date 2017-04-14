package com.fd.font.cloud.font.vo;

import java.util.ArrayList;
import java.util.List;

public class FontsResponse {
	
	private List<FontResponse> fonts = new ArrayList<>();

	public List<FontResponse> getFonts() {
		return fonts;
	}

	public void setFonts(List<FontResponse> fonts) {
		this.fonts = fonts;
	}
	
	
}
