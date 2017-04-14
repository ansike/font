package com.fd.font.cloud.developer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.font.cloud.developer.dao.AppMapper;
import com.fd.font.cloud.developer.entity.App;
import com.fd.font.cloud.developer.entity.AppExample;
import com.fd.font.cloud.developer.service.AppService;

@Service("accessTokenService")
public class AppServiceImpl implements AppService{
	
	@Autowired
	private AppMapper appMapper;

	@Override
	public List<App> findByAppkey(String appkey) {
		AppExample appExample = new AppExample();
		appExample.createCriteria().andAppKeyEqualTo(appkey);
		List<App> list = appMapper.selectByExample(appExample);
		
		return list ;
	}

}
