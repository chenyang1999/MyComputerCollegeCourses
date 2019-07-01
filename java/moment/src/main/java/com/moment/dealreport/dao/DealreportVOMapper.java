package com.moment.dealreport.dao;

import com.moment.dealreport.domain.DealreportVO;
import com.moment.dealreport.domain.DealreportVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DealreportVOMapper {
    int countByExample(DealreportVOExample example);

    int deleteByExample(DealreportVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DealreportVO record);

    int insertSelective(DealreportVO record);

    List<DealreportVO> selectByExample(DealreportVOExample example);

    DealreportVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DealreportVO record, @Param("example") DealreportVOExample example);

    int updateByExample(@Param("record") DealreportVO record, @Param("example") DealreportVOExample example);

    int updateByPrimaryKeySelective(DealreportVO record);

    int updateByPrimaryKey(DealreportVO record);
}