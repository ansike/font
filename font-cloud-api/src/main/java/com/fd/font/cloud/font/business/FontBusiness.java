package com.fd.font.cloud.font.business;

import java.io.InputStream;

import com.fd.font.cloud.font.vo.FontDetailResponse;
import com.fd.font.cloud.font.vo.FontsResponse;

public interface FontBusiness {

	FontDetailResponse findFontByCode(String code, String accessToken);

	FontsResponse findAll(String accessToken);

	InputStream fontDownload(String code, String accessToken);

	
}

