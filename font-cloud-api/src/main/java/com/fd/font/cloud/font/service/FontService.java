package com.fd.font.cloud.font.service;

import java.util.List;

import com.fd.font.cloud.developer.entity.AppFont;
import com.fd.font.cloud.developer.entity.FontDownloadLog;
import com.fd.font.cloud.font.dto.FontDetailDto;
import com.fd.font.cloud.font.dto.FontDto;

public interface FontService {

	List<FontDetailDto> findFontByCode(String code);

	List<FontDto> findAll();

	AppFont getAppFontByAppkeyAndFontCode(String appkey, String code);

	int addFontDownloadLog(FontDownloadLog fontDownloadLog);
}
