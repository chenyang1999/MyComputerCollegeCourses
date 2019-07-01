package com.moment.admin.dao;

import com.moment.admin.domain.AdminVO;
import com.moment.admin.domain.AdminVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminVOMapper {
    int countByExample(AdminVOExample example);

    int deleteByExample(AdminVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminVO record);

    int insertSelective(AdminVO record);

    List<AdminVO> selectByExample(AdminVOExample example);

    AdminVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminVO record, @Param("example") AdminVOExample example);

    int updateByExample(@Param("record") AdminVO record, @Param("example") AdminVOExample example);

    int updateByPrimaryKeySelective(AdminVO record);

    int updateByPrimaryKey(AdminVO record);
}