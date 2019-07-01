package com.moment.picCalendar.dao;

import com.moment.picCalendar.domain.PiccalendarVO;
import com.moment.picCalendar.domain.PiccalendarVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PiccalendarVOMapper {
    int countByExample(PiccalendarVOExample example);

    int deleteByExample(PiccalendarVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PiccalendarVO record);

    int insertSelective(PiccalendarVO record);

    List<PiccalendarVO> selectByExample(PiccalendarVOExample example);

    PiccalendarVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PiccalendarVO record, @Param("example") PiccalendarVOExample example);

    int updateByExample(@Param("record") PiccalendarVO record, @Param("example") PiccalendarVOExample example);

    int updateByPrimaryKeySelective(PiccalendarVO record);

    int updateByPrimaryKey(PiccalendarVO record);
}