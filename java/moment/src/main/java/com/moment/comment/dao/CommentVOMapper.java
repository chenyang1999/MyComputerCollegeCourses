package com.moment.comment.dao;

import com.moment.comment.domain.CommentVO;
import com.moment.comment.domain.CommentVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentVOMapper {
    int countByExample(CommentVOExample example);

    int deleteByExample(CommentVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentVO record);

    int insertSelective(CommentVO record);

    List<CommentVO> selectByExample(CommentVOExample example);

    CommentVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentVO record, @Param("example") CommentVOExample example);

    int updateByExample(@Param("record") CommentVO record, @Param("example") CommentVOExample example);

    int updateByPrimaryKeySelective(CommentVO record);

    int updateByPrimaryKey(CommentVO record);
}