package com.moment.role.dao;

import com.moment.role.domain.RoleVO;
import com.moment.role.domain.RoleVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleVOMapper {
    int countByExample(RoleVOExample example);

    int deleteByExample(RoleVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleVO record);

    int insertSelective(RoleVO record);

    List<RoleVO> selectByExample(RoleVOExample example);

    RoleVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleVO record, @Param("example") RoleVOExample example);

    int updateByExample(@Param("record") RoleVO record, @Param("example") RoleVOExample example);

    int updateByPrimaryKeySelective(RoleVO record);

    int updateByPrimaryKey(RoleVO record);
}