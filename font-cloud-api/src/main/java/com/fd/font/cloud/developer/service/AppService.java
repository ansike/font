package com.fd.font.cloud.developer.service;

import java.util.List;

import com.fd.font.cloud.developer.entity.App;

public interface AppService {

	List<App> findByAppkey(String appkey);
}
