package com.moment.comment.service;

import java.util.List;

import com.moment.comment.domain.CommentVO;

public interface CommentService {
      public int add(CommentVO entity);
      public int delete(Integer id,Integer picid);
      public int update(CommentVO entity);
      public List<CommentVO> get(Integer id);
      public int getCountbyPicid(Integer picid);
}
