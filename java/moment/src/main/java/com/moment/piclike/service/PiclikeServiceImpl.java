package com.moment.piclike.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.pic.dao.PicVOMapper;
import com.moment.pic.domain.PicVO;
import com.moment.piclike.dao.PiclikeVOMapper;
import com.moment.piclike.domain.PiclikeVO;
import com.moment.piclike.domain.PiclikeVOExample;


@Service
public class PiclikeServiceImpl implements PiclikeService {
	@Autowired   
	private PiclikeVOMapper mapper;
	@Autowired
	private PicVOMapper picmapper;
	
	@Override
	public int addPiclike(PiclikeVO pic) throws Throwable {
		PicVO pic1 = picmapper.selectByPrimaryKey(pic.getPicid());
		int piclike = pic1.getPiclike()+1;
		pic1.setPiclike(piclike);
		picmapper.updateByPrimaryKey(pic1);
		return mapper.insertSelective(pic);
	}

	@Override
	public int deletePiclike(PiclikeVO pic) throws Throwable {
		PicVO pic1 = picmapper.selectByPrimaryKey(pic.getPicid());
		int piclike = pic1.getPiclike()-1;
		pic1.setPiclike(piclike);
		picmapper.updateByPrimaryKey(pic1);
		PiclikeVOExample example = new PiclikeVOExample();
		example.createCriteria().andPicidEqualTo(pic.getPicid());
		return mapper.deleteByExample(example);
	}

	@Override
	public List<PiclikeVO> getPiclikeById(Integer picid) throws Throwable {
		PiclikeVOExample example = new PiclikeVOExample();
		example.createCriteria().andPicidEqualTo(picid);
		return mapper.selectByExample(example);
	}
	
	@Override
	public List<PiclikeVO> getPiclikeByUserId(Integer userid) throws Throwable {
		PiclikeVOExample example = new PiclikeVOExample();
		example.createCriteria().andUseridEqualTo(userid);
		return mapper.selectByExample(example);
	}
	@Override
	public boolean getifPiclikeById(Integer picid,Integer userid) throws Throwable {
		PiclikeVOExample example = new PiclikeVOExample();
		example.createCriteria().andPicidEqualTo(picid).andUseridEqualTo(userid);
		if(mapper.selectByExample(example).isEmpty()){
		         return true;
		}else{
			return false;
		}
	}
	@Override
	public int getlikeNumberByPicId(Integer picid) throws Throwable {
		PiclikeVOExample example = new PiclikeVOExample();
		example.createCriteria().andPicidEqualTo(picid);
		return mapper.countByExample(example);
	}

}
