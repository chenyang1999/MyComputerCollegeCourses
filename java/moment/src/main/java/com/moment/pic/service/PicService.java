package com.moment.pic.service;


import java.io.File;
import java.util.List;

import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.pic.domain.PicEX;
import com.moment.pic.domain.PicVO;
import com.moment.user.domain.UserVO;
import com.qiniu.http.Response;

public interface PicService {
	public int addPic(PicVO pic) throws Throwable;
	
	public int deletePic(Integer id) throws Throwable;
	
	public PicEX getPicById(Integer id) throws Throwable;
	
	public int updatePicVO(PicVO pic) throws Throwable;
	public DataTablesResponse<PicVO> list(DataTablesRequest request) throws Throwable;
	public  Response doUpload(byte[] b,PicVO pic) throws Throwable ;
	public int deletePicFromQiniu(Integer id) throws Throwable;
	public int getPicnumByDate(Integer id) throws Throwable ;
	public void updateUserGrade(UserVO user) throws Throwable ;
	public List<PicEX> getPicList(String style) throws Throwable ;
	public List<PicVO> getUserPicList(Integer id) throws Throwable ;
	public List<PicEX> getPicListByCondition(String key) throws Throwable ;
}
