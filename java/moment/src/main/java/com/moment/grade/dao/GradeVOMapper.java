package com.moment.grade.dao;

import com.moment.grade.domain.GradeVO;
import com.moment.grade.domain.GradeVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GradeVOMapper {
    int countByExample(GradeVOExample example);

    int deleteByExample(GradeVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GradeVO record);

    int insertSelective(GradeVO record);

    List<GradeVO> selectByExample(GradeVOExample example);

    GradeVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GradeVO record, @Param("example") GradeVOExample example);

    int updateByExample(@Param("record") GradeVO record, @Param("example") GradeVOExample example);

    int updateByPrimaryKeySelective(GradeVO record);

    int updateByPrimaryKey(GradeVO record);
}