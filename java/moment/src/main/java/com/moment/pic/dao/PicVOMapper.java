package com.moment.pic.dao;

import com.moment.pic.domain.PicEX;
import com.moment.pic.domain.PicVO;
import com.moment.pic.domain.PicVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PicVOMapper {
    int countByExample(PicVOExample example);

    int deleteByExample(PicVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PicVO record);

    int insertSelective(PicVO record);

    List<PicEX> selectPicDetailByExample(PicVOExample example);
    
    List<PicVO> selectByExample(PicVOExample example);

    PicVO selectByPrimaryKey(Integer id);
    
    PicEX selectPicDetailById(Integer id);

    int updateByExampleSelective(@Param("record") PicVO record, @Param("example") PicVOExample example);

    int updateByExample(@Param("record") PicVO record, @Param("example") PicVOExample example);

    int updateByPrimaryKeySelective(PicVO record);

    int updateByPrimaryKey(PicVO record);
}