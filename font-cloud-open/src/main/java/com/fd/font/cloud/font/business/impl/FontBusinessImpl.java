package com.fd.font.cloud.font.business.impl;

import org.springframework.stereotype.Service;

import com.fd.font.cloud.font.business.FontBusiness;
import com.fd.font.cloud.font.vo.FontVo;

@Service(value="fontBusiness")
public class FontBusinessImpl implements FontBusiness{
	
	@Override
	public FontVo findFontByCode(String code) {
		return null;
	}

	
}
