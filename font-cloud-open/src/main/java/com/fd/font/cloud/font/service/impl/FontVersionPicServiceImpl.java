package com.fd.font.cloud.font.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.font.cloud.font.dao.FontMapper;
import com.fd.font.cloud.font.dao.FontVersionPicMapper;
import com.fd.font.cloud.font.entity.Font;
import com.fd.font.cloud.font.entity.FontVersionPic;
import com.fd.font.cloud.font.entity.FontVersionPicExample;
import com.fd.font.cloud.font.service.FontVersionPicService;
@Service
public class FontVersionPicServiceImpl implements FontVersionPicService {
	@Autowired
	private FontVersionPicMapper fontVersionPicMapper;
	@Autowired
	private FontMapper fontMapper;
	@Override
	public List<FontVersionPic> findFontPicsByFontId(Integer fontId,Byte type) {
		Font font=fontMapper.selectByPrimaryKey(fontId);
		FontVersionPicExample example=new FontVersionPicExample();
		example.setOrderByClause("pic_url asc");
		FontVersionPicExample.Criteria criteria=example.createCriteria();
		criteria.andIsDeletedEqualTo((byte)0).andFontVersionIdEqualTo(font.getCurrFontVersionId());
		if(type!=null)criteria.andPicTypeEqualTo(type);
		return fontVersionPicMapper.selectByExample(example);
	}

}
