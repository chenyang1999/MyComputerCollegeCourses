package com.moment.concern.dao;

import com.moment.concern.domain.ConcernVO;
import com.moment.concern.domain.ConcernVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConcernVOMapper {
    int countByExample(ConcernVOExample example);

    int deleteByExample(ConcernVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConcernVO record);

    int insertSelective(ConcernVO record);

    List<ConcernVO> selectByExample(ConcernVOExample example);
    
    List<Integer> getConcern(Integer watchuserId);

    ConcernVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConcernVO record, @Param("example") ConcernVOExample example);

    int updateByExample(@Param("record") ConcernVO record, @Param("example") ConcernVOExample example);

    int updateByPrimaryKeySelective(ConcernVO record);

    int updateByPrimaryKey(ConcernVO record);
}