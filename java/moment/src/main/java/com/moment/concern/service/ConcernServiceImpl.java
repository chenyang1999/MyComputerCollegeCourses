package com.moment.concern.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moment.concern.dao.ConcernVOMapper;
import com.moment.concern.domain.ConcernVO;
import com.moment.concern.domain.ConcernVOExample;
import com.moment.dealreport.domain.DealreportVOExample;
import com.moment.user.dao.UserVOMapper;
import com.moment.user.domain.UserVO;
@Service
public class ConcernServiceImpl implements ConcernService{
	@Autowired
	private ConcernVOMapper cmap;
	@Autowired
	private UserVOMapper umap;
	@Transactional
	@Override
	public int add(ConcernVO concern) throws Throwable {
		//关注者
		UserVO watchuser=umap.selectByPrimaryKey(concern.getWatchuserid());
		//被关注者
		UserVO bewatchuaer=umap.selectByPrimaryKey(concern.getBewatchuserid());
		watchuser.setConcernnum(watchuser.getConcernnum()+1);
		System.out.println("watch:"+watchuser.getConcernnum());
		bewatchuaer.setFansnum(bewatchuaer.getFansnum()+1);
		System.out.println("bewatch:"+bewatchuaer.getFansnum());
		umap.updateByPrimaryKey(watchuser);
		umap.updateByPrimaryKey(bewatchuaer);
		return cmap.insertSelective(concern);
	}

	@Override
	public List<Integer> getConcern(int id) throws Throwable {
		return cmap.getConcern(id);
	}

	@Override
	public List<ConcernVO> getFans(int id) throws Throwable {
		ConcernVOExample example=new ConcernVOExample();
		example.createCriteria().andBewatchuseridEqualTo(id);
		return cmap.selectByExample(example);
	}
	@Transactional
	@Override
	public int delete(int watchId,int beWatchId) throws Throwable {
		ConcernVOExample example=new ConcernVOExample();
		example.createCriteria().andBewatchuseridEqualTo(beWatchId).andWatchuseridEqualTo(watchId);
		List<ConcernVO> list=cmap.selectByExample(example);
		ConcernVO concern=list.get(0);
		//关注者
		UserVO watchuser=umap.selectByPrimaryKey(concern.getWatchuserid());
		//被关注者
		UserVO bewatchuaer=umap.selectByPrimaryKey(concern.getBewatchuserid());
		watchuser.setConcernnum(watchuser.getConcernnum()-1);
		bewatchuaer.setFansnum(bewatchuaer.getFansnum()-1);
		umap.updateByPrimaryKey(watchuser);
		umap.updateByPrimaryKey(bewatchuaer);
		return cmap.deleteByPrimaryKey(concern.getId());
	}
	
}
