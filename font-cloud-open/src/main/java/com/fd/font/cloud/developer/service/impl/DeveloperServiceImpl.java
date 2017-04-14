package com.fd.font.cloud.developer.service.impl;

import java.util.List;

import com.fd.font.cloud.base.FontTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.font.cloud.base.ControllerException;
import com.fd.font.cloud.base.ReturnCode;
import com.fd.font.cloud.developer.dao.DeveloperMapper;
import com.fd.font.cloud.developer.entity.Developer;
import com.fd.font.cloud.developer.entity.DeveloperExample;
import com.fd.font.cloud.developer.service.DeveloperService;
import com.fd.font.cloud.util.MD5Util;

@Service
public class DeveloperServiceImpl implements DeveloperService {
	@Autowired
	private DeveloperMapper developerMapper;

	@Override
	public Developer findByMobile(String mobile) {
		DeveloperExample example = new DeveloperExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<Developer> list = developerMapper.selectByExample(example);
		if (list.size() == 0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public void insert(String mobile, String password) throws  ControllerException {
		DeveloperExample example=new DeveloperExample();
		example.createCriteria().andMobileEqualTo(mobile).andIsDeletedEqualTo(FontTypeEnum.NOT_DELETED.getType());
		List<Developer> list=developerMapper.selectByExample(example);
		if(list.size()>0)throw new ControllerException(ReturnCode.MOBILE_EXIST);
		Developer developer=new Developer();
		developer.setMobile(mobile);
		developer.setPassword(MD5Util.string2MD5(password));
		developerMapper.insertSelective(developer);
	}

	@Override
	public Developer updateEmailByIdOrMobile(Integer id, String mobile, String email,String address) throws ControllerException {
		Developer developer=new Developer();
		developer.setEmail(email);
		developer.setAddress(address);
		DeveloperExample example=new DeveloperExample();
		DeveloperExample.Criteria criteria=example.createCriteria();
		if(id!=null)criteria.andDeveloperIdEqualTo(id);
		criteria.andMobileEqualTo(mobile);
		int num=developerMapper.updateByExampleSelective(developer, example);
		if(num==0||num>1)throw new ControllerException(ReturnCode.UPDATE_EMAIL_ERROR);
		
		return developerMapper.selectByExample(example).get(0);
	}

	@Override
	public void updatePasswordByMobile(String mobile, String password)throws ControllerException {
		Developer developer=new Developer();
		developer.setPassword(password);
		DeveloperExample example=new DeveloperExample();
		DeveloperExample.Criteria criteria=example.createCriteria();
		criteria.andMobileEqualTo(mobile);
		int num=developerMapper.updateByExampleSelective(developer, example);
		if(num==0||num>1)throw new ControllerException(ReturnCode.UPDATE_EMAIL_ERROR);
	}

}
