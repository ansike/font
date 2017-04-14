package com.fd.font.cloud.font.dao;

import java.util.List;

import com.fd.font.cloud.font.dto.FontDetailDto;
import com.fd.font.cloud.font.dto.FontDto;

public interface FontExMapper {

	public List<FontDto> findAll();

	public List<FontDetailDto> selectByFontCode(String code);
}
