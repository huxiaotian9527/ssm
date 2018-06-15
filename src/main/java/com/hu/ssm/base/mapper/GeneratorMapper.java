package com.hu.ssm.base.mapper;

import com.hu.ssm.base.domain.Generator;
import com.hu.ssm.base.domain.GeneratorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GeneratorMapper {
    int countByExample(GeneratorExample example);

    int deleteByExample(GeneratorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Generator record);

    int insertSelective(Generator record);

    List<Generator> selectByExample(GeneratorExample example);

    Generator selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Generator record, @Param("example") GeneratorExample example);

    int updateByExample(@Param("record") Generator record, @Param("example") GeneratorExample example);

    int updateByPrimaryKeySelective(Generator record);

    int updateByPrimaryKey(Generator record);
}