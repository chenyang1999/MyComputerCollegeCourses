package com.moment.piclike.service;

import java.util.List;
import com.moment.piclike.domain.PiclikeVO;

public interface PiclikeService {
	public int addPiclike(PiclikeVO pic) throws Throwable;
	public int deletePiclike(PiclikeVO pic) throws Throwable;
	public List<PiclikeVO> getPiclikeById(Integer picid) throws Throwable;
	public List<PiclikeVO> getPiclikeByUserId(Integer userid) throws Throwable;
	public int getlikeNumberByPicId(Integer picid) throws Throwable;
	public boolean getifPiclikeById(Integer picid,Integer userid) throws Throwable;
}
