package com.fd.font.cloud.font.dao;

import com.fd.font.cloud.font.entity.FontVersionPic;
import com.fd.font.cloud.font.entity.FontVersionPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FontVersionPicMapper {
    long countByExample(FontVersionPicExample example);

    int deleteByExample(FontVersionPicExample example);

    int deleteByPrimaryKey(Integer picId);

    int insert(FontVersionPic record);

    int insertSelective(FontVersionPic record);

    List<FontVersionPic> selectByExample(FontVersionPicExample example);

    FontVersionPic selectByPrimaryKey(Integer picId);

    int updateByExampleSelective(@Param("record") FontVersionPic record, @Param("example") FontVersionPicExample example);

    int updateByExample(@Param("record") FontVersionPic record, @Param("example") FontVersionPicExample example);

    int updateByPrimaryKeySelective(FontVersionPic record);

    int updateByPrimaryKey(FontVersionPic record);
}