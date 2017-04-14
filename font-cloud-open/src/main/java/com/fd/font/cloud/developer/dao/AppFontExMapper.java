package com.fd.font.cloud.developer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fd.font.cloud.app.entity.App;
import com.fd.font.cloud.developer.vo.AppFontResponse;
import com.fd.font.cloud.developer.vo.UnauthzFont;

public interface AppFontExMapper {

	List<AppFontResponse> selectAppFontByAppId(App app);

	List<UnauthzFont> fontList(Map<String,Object> params);

	List<Map<String,String>> selectUnauthing(Integer day);

	List<UnauthzFont> selectUnauthzFonts(Map<String, Object> params);

	List<Map<String, Object>> selectAppNums(@Param("developerId")Integer developerId);

	List<AppFontResponse> selectAppFontByPage(App app);
}
