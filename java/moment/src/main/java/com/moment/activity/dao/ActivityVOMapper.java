package com.moment.activity.dao;

import com.moment.activity.domain.ActivityVO;
import com.moment.activity.domain.ActivityVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityVOMapper {
    int countByExample(ActivityVOExample example);

    int deleteByExample(ActivityVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityVO record);

    int insertSelective(ActivityVO record);

    List<ActivityVO> selectByExample(ActivityVOExample example);

    ActivityVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityVO record, @Param("example") ActivityVOExample example);

    int updateByExample(@Param("record") ActivityVO record, @Param("example") ActivityVOExample example);

    int updateByPrimaryKeySelective(ActivityVO record);

    int updateByPrimaryKey(ActivityVO record);
}