package com.fd.font.cloud.font.dao;

import com.fd.font.cloud.font.entity.FontVersionDownload;
import com.fd.font.cloud.font.entity.FontVersionDownloadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FontVersionDownloadMapper {
    int countByExample(FontVersionDownloadExample example);

    int deleteByExample(FontVersionDownloadExample example);

    int deleteByPrimaryKey(Integer downloadId);

    int insert(FontVersionDownload record);

    int insertSelective(FontVersionDownload record);

    List<FontVersionDownload> selectByExample(FontVersionDownloadExample example);

    FontVersionDownload selectByPrimaryKey(Integer downloadId);

    int updateByExampleSelective(@Param("record") FontVersionDownload record, @Param("example") FontVersionDownloadExample example);

    int updateByExample(@Param("record") FontVersionDownload record, @Param("example") FontVersionDownloadExample example);

    int updateByPrimaryKeySelective(FontVersionDownload record);

    int updateByPrimaryKey(FontVersionDownload record);

    int insertBatchSelective(List<FontVersionDownload> records);

    int updateBatchByPrimaryKeySelective(List<FontVersionDownload> records);
}