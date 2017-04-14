package com.fd.font.cloud.font.service;

import java.util.List;

import com.fd.font.cloud.font.entity.FontVersionPic;

public interface FontVersionPicService {
	List<FontVersionPic> findFontPicsByFontId(Integer fontId,Byte type);
}
