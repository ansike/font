package com.fd.font.cloud.font.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.font.cloud.font.service.FontTagService;
import com.fd.font.cloud.tag.dao.TagMapper;
import com.fd.font.cloud.tag.entity.Tag;
import com.fd.font.cloud.tag.entity.TagExample;

@Service
public class FontTagServiceImpl implements FontTagService {

	@Autowired
	private TagMapper tagMapper;

	@Override
	public List<Tag> findList(Tag params) {
		TagExample example=new TagExample();
		if(params.getTagValue()!=null){
		}
		return tagMapper.selectByExample(example);
	}

	@Override
	public Set<String> findThink(String search) {
		Set<String> tags=new HashSet<>();
		TagExample example=new TagExample();
		example.createCriteria().andTagValueLike(search+"%");
		for(Tag t:tagMapper.selectByExample(example)){
			tags.add(t.getTagValue());
		}
		return tags;
	}

}
