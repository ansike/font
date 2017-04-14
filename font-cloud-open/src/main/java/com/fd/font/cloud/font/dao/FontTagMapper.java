package com.fd.font.cloud.font.dao;

import com.fd.font.cloud.font.entity.FontTag;
import com.fd.font.cloud.font.entity.FontTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FontTagMapper {
    int countByExample(FontTagExample example);

    int deleteByExample(FontTagExample example);

    int deleteByPrimaryKey(Integer fontTagId);

    int insert(FontTag record);

    int insertSelective(FontTag record);

    List<FontTag> selectByExample(FontTagExample example);

    FontTag selectByPrimaryKey(Integer fontTagId);

    int updateByExampleSelective(@Param("record") FontTag record, @Param("example") FontTagExample example);

    int updateByExample(@Param("record") FontTag record, @Param("example") FontTagExample example);

    int updateByPrimaryKeySelective(FontTag record);

    int updateByPrimaryKey(FontTag record);

    int insertBatchSelective(List<FontTag> records);

    int updateBatchByPrimaryKeySelective(List<FontTag> records);
}