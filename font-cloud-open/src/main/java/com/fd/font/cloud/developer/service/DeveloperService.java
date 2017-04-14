package com.fd.font.cloud.developer.service;

import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.developer.entity.Developer;

public interface DeveloperService {
	Developer findByMobile(String mobile);
	void insert(String mobile,String password) throws ControllerException;
	Developer updateEmailByIdOrMobile(Integer id, String mobile, String email,String address) throws ControllerException;
	void updatePasswordByMobile(String mobile, String password) throws ControllerException;
}
