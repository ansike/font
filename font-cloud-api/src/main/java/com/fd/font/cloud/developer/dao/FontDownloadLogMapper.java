package com.fd.font.cloud.developer.dao;

import com.fd.font.cloud.developer.entity.FontDownloadLog;
import com.fd.font.cloud.developer.entity.FontDownloadLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FontDownloadLogMapper {
    long countByExample(FontDownloadLogExample example);

    int deleteByExample(FontDownloadLogExample example);

    int deleteByPrimaryKey(Integer downloadId);

    int insert(FontDownloadLog record);

    int insertSelective(FontDownloadLog record);

    List<FontDownloadLog> selectByExample(FontDownloadLogExample example);

    FontDownloadLog selectByPrimaryKey(Integer downloadId);

    int updateByExampleSelective(@Param("record") FontDownloadLog record, @Param("example") FontDownloadLogExample example);

    int updateByExample(@Param("record") FontDownloadLog record, @Param("example") FontDownloadLogExample example);

    int updateByPrimaryKeySelective(FontDownloadLog record);

    int updateByPrimaryKey(FontDownloadLog record);
}