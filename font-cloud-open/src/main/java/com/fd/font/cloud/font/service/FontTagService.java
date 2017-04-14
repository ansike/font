package com.fd.font.cloud.font.service;

import java.util.List;
import java.util.Set;

import com.fd.font.cloud.tag.entity.Tag;

public interface FontTagService {

	List<Tag> findList(Tag params);

	Set<String> findThink(String search);
}
