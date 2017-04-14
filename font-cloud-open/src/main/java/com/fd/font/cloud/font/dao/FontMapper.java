package com.fd.font.cloud.font.dao;

import com.fd.font.cloud.font.entity.Font;
import com.fd.font.cloud.font.entity.FontExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FontMapper {
    int countByExample(FontExample example);

    int deleteByExample(FontExample example);

    int deleteByPrimaryKey(Integer fontId);

    int insert(Font record);

    int insertSelective(Font record);

    List<Font> selectByExample(FontExample example);

    Font selectByPrimaryKey(Integer fontId);

    int updateByExampleSelective(@Param("record") Font record, @Param("example") FontExample example);

    int updateByExample(@Param("record") Font record, @Param("example") FontExample example);

    int updateByPrimaryKeySelective(Font record);

    int updateByPrimaryKey(Font record);

    int insertBatchSelective(List<Font> records);

    int updateBatchByPrimaryKeySelective(List<Font> records);
}