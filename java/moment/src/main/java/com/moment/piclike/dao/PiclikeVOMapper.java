package com.moment.piclike.dao;

import com.moment.piclike.domain.PiclikeVO;
import com.moment.piclike.domain.PiclikeVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PiclikeVOMapper {
    int countByExample(PiclikeVOExample example);

    int deleteByExample(PiclikeVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PiclikeVO record);

    int insertSelective(PiclikeVO record);

    List<PiclikeVO> selectByExample(PiclikeVOExample example);

    PiclikeVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PiclikeVO record, @Param("example") PiclikeVOExample example);

    int updateByExample(@Param("record") PiclikeVO record, @Param("example") PiclikeVOExample example);

    int updateByPrimaryKeySelective(PiclikeVO record);

    int updateByPrimaryKey(PiclikeVO record);
}