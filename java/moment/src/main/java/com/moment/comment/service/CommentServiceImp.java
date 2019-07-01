package com.moment.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moment.comment.dao.CommentVOMapper;
import com.moment.comment.domain.CommentVO;
import com.moment.comment.domain.CommentVOExample;
import com.moment.common.util.SpringUtil;
import com.moment.pic.dao.PicVOMapper;
import com.moment.pic.domain.PicVO;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
	private CommentVOMapper commentmapper ; 
    @Autowired
	private PicVOMapper picmapper;
    
    @Override
    @Transactional
	public int add(CommentVO entity) {
    	PicVO pic1 = picmapper.selectByPrimaryKey(entity.getPicid());
		int picComment = pic1.getComment()+1;
		pic1.setComment(picComment);
		picmapper.updateByPrimaryKey(pic1);
    	return commentmapper.insertSelective(entity);
		
	}

	@Override
	@Transactional
	public int delete(Integer id,Integer picid) {
		PicVO pic1 = picmapper.selectByPrimaryKey(picid);
		int picComment = pic1.getComment()-1;
		pic1.setComment(picComment);
		picmapper.updateByPrimaryKey(pic1);
		return commentmapper.deleteByPrimaryKey(id);
		
	}

	@Override
	@Transactional
	public int update(CommentVO entity) {
		return  commentmapper.updateByPrimaryKeySelective(entity);
		
	}

	@Override
	public List<CommentVO> get(Integer id) {
		CommentVOExample example = new CommentVOExample();
		example.createCriteria().andPicidEqualTo(id);
		List<CommentVO> list=commentmapper.selectByExample(example);
		return list;
	}

	@Override
	public int getCountbyPicid(Integer picid) {
		CommentVOExample example = new CommentVOExample();
		example.createCriteria().andPicidEqualTo(picid);
		return commentmapper.countByExample(example);
	}

	/* public static void main(String[] args) {
	  SpringUtil.init("application.xml");
	CommentService service =SpringUtil.getBean(CommentService.class);
	int id = 1 ;
	System.out.println(service.get(id).toString());

}*/
}
