package com.fd.font.cloud.font.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.fd.font.cloud.font.vo.FontDetailVo;
import com.fd.font.cloud.font.vo.FontVo;

public interface FontExMapper {

	public List<FontVo> findList(Map<String,String> font);
	public List<FontDetailVo> findH5List(Map<String,String> font);
	public FontDetailVo findFontDetail(Map<String, String> params);
	public List<FontDetailVo> findFontDetailAll();
	public Set<String> findNameThink(@Param("search")String search);
	public Set<String> findAuthorThink(@Param("author")String search);
}
