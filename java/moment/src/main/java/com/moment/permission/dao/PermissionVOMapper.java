package com.moment.permission.dao;

import com.moment.permission.domain.PermissionVO;
import com.moment.permission.domain.PermissionVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionVOMapper {
    int countByExample(PermissionVOExample example);

    int deleteByExample(PermissionVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionVO record);

    int insertSelective(PermissionVO record);

    List<PermissionVO> selectByExample(PermissionVOExample example);

    PermissionVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PermissionVO record, @Param("example") PermissionVOExample example);

    int updateByExample(@Param("record") PermissionVO record, @Param("example") PermissionVOExample example);

    int updateByPrimaryKeySelective(PermissionVO record);

    int updateByPrimaryKey(PermissionVO record);
}