package com.fd.font.cloud.developer.dao;

import com.fd.font.cloud.developer.entity.Developer;
import com.fd.font.cloud.developer.entity.DeveloperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeveloperMapper {
    int countByExample(DeveloperExample example);

    int deleteByExample(DeveloperExample example);

    int deleteByPrimaryKey(Integer developerId);

    int insert(Developer record);

    int insertSelective(Developer record);

    List<Developer> selectByExample(DeveloperExample example);

    Developer selectByPrimaryKey(Integer developerId);

    int updateByExampleSelective(@Param("record") Developer record, @Param("example") DeveloperExample example);

    int updateByExample(@Param("record") Developer record, @Param("example") DeveloperExample example);

    int updateByPrimaryKeySelective(Developer record);

    int updateByPrimaryKey(Developer record);

    int insertBatchSelective(List<Developer> records);

    int updateBatchByPrimaryKeySelective(List<Developer> records);
}