package com.fd.font.cloud.developer.dao;

import com.fd.font.cloud.developer.entity.AppFont;
import com.fd.font.cloud.developer.entity.AppFontExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppFontMapper {
    long countByExample(AppFontExample example);

    int deleteByExample(AppFontExample example);

    int deleteByPrimaryKey(Integer appFontId);

    int insert(AppFont record);

    int insertSelective(AppFont record);

    List<AppFont> selectByExample(AppFontExample example);

    AppFont selectByPrimaryKey(Integer appFontId);

    int updateByExampleSelective(@Param("record") AppFont record, @Param("example") AppFontExample example);

    int updateByExample(@Param("record") AppFont record, @Param("example") AppFontExample example);

    int updateByPrimaryKeySelective(AppFont record);

    int updateByPrimaryKey(AppFont record);
}