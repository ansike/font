package com.fd.font.cloud.font.dao;

import com.fd.font.cloud.font.entity.FontVersion;
import com.fd.font.cloud.font.entity.FontVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FontVersionMapper {
    int countByExample(FontVersionExample example);

    int deleteByExample(FontVersionExample example);

    int deleteByPrimaryKey(Integer fontVersionId);

    int insert(FontVersion record);

    int insertSelective(FontVersion record);

    List<FontVersion> selectByExample(FontVersionExample example);

    FontVersion selectByPrimaryKey(Integer fontVersionId);

    int updateByExampleSelective(@Param("record") FontVersion record, @Param("example") FontVersionExample example);

    int updateByExample(@Param("record") FontVersion record, @Param("example") FontVersionExample example);

    int updateByPrimaryKeySelective(FontVersion record);

    int updateByPrimaryKey(FontVersion record);

    int insertBatchSelective(List<FontVersion> records);

    int updateBatchByPrimaryKeySelective(List<FontVersion> records);
}