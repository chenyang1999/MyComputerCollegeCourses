package com.moment.pic.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.common.domain.JsonResult;
import com.moment.common.util.ConfigUtil;
import com.moment.common.util.SearchConditionUtils;
import com.moment.common.util.SpringUtil;
import com.moment.common.util.TransTimestamp;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.pic.dao.PicVOMapper;
import com.moment.pic.domain.PicEX;
import com.moment.pic.domain.PicVO;
import com.moment.pic.domain.PicVOExample;
import com.moment.picCalendar.domain.PiccalendarVO;
import com.moment.user.dao.UserVOMapper;
import com.moment.user.domain.UserVO;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

@Service
public class PicServiceImpl implements PicService {
	@Autowired
	private PicVOMapper mapper;
	
	@Autowired
	private UserVOMapper usermapper ;

	@Override
	public int addPic(PicVO pic) throws Throwable {
		return mapper.insertSelective(pic);
	}

	@Override
	public int deletePic(Integer id) throws Throwable {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public PicEX getPicById(Integer id) throws Throwable {
		return mapper.selectPicDetailById(id);
	}

	@Override
	public int updatePicVO(PicVO pic) throws Throwable {
		return mapper.updateByPrimaryKeySelective(pic);
	}

	@Override
	public DataTablesResponse<PicVO> list(DataTablesRequest request) throws Throwable {
		PicVOExample example = new PicVOExample();
		DataTablesResponse<PicVO> response = new DataTablesResponse<PicVO>();
		SearchConditionUtils.wrapperAndCondition(example, request);// 封装查询条件
		response.setDraw(request.getDraw());
		response.setRecordsTotal(mapper.countByExample(example));
		response.setData(mapper.selectByExample(example));
		return response;
	}

	@Override
	public Response doUpload(byte[] b, PicVO pic) throws Throwable {
	

		String bucketname = "moment";
		String fileName = UUID.randomUUID().toString();
		pic.setPicpath(ConfigUtil.getValue("uri") + fileName);
		Auth auth = Auth.create(ConfigUtil.getValue("AccessKey"), ConfigUtil.getValue("SecretKey"));
		String token = auth.uploadToken(bucketname);
		UploadManager manager = new UploadManager();
		Response response = manager.put(b, fileName, token);
		
		if (response.isOK()) {
			mapper.insertSelective(pic);
		}
		return response;

	}

	@Override
	public int deletePicFromQiniu(Integer id) throws Throwable {

		String accessKey = ConfigUtil.getValue("AccessKey");
		String secretKey = ConfigUtil.getValue("SecretKey");

		String bucket = "moment";
		PicVO pic = mapper.selectByPrimaryKey(id);

		String key = pic.getPicpath().substring(pic.getPicpath().lastIndexOf("/") + 1);
		System.out.println(key);
		Auth auth = Auth.create(accessKey, secretKey);
		BucketManager bucketManager = new BucketManager(auth);
		bucketManager.delete(bucket, key);
		int i = mapper.deleteByPrimaryKey(id);
		return i;
	}

	@Override
	public int getPicnumByDate(Integer id) throws Throwable {
		// 获取当前时间，并将时间数设为00：00：00
		Date date1 = new Date();
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		// 获取时间，并将时间数设为00：00：00
		TransTimestamp ts = new TransTimestamp();
		Date date2 = ts.getTomorrow(date1);
		PicVOExample example = new PicVOExample();
		// 获取在这时间范围内的图片,可以使用链式结构
		example.createCriteria().andTimeBetween(date1, date2).andUseridEqualTo(id);
		List<PicVO> list = mapper.selectByExample(example);
		
		return list.size();
	}

	@Override
	public void updateUserGrade(UserVO user) throws Throwable {
		Integer picnum = user.getPicnum() ;
		if((picnum>=30)&&(picnum<60)){
			user.setGradeid(2);
			usermapper.updateByPrimaryKeySelective(user);
		}else if((picnum>=60)&&(picnum<100)){
			user.setGradeid(3);
			usermapper.updateByPrimaryKeySelective(user);
		}else if((picnum>=100)){
			user.setGradeid(4);
			usermapper.updateByPrimaryKeySelective(user);
		}else{			
			user.setGradeid(1);
			usermapper.updateByPrimaryKeySelective(user);
		}			
	}

	@Override
	public List<PicEX> getPicList(String type) throws Throwable {
		PicVOExample example = new PicVOExample() ;
		example.createCriteria().andTypeLike("%"+type+"%") ;
		example.setOrderByClause("time desc");
		return mapper.selectPicDetailByExample(example);
	}
	

	@Override
	public List<PicVO> getUserPicList(Integer id) throws Throwable {
		PicVOExample example = new PicVOExample() ;
		example.createCriteria().andUseridEqualTo(id) ;
		example.setOrderByClause("time desc");
		return mapper.selectByExample(example);
		
	}
	@Override
	public List<PicEX> getPicListByCondition(String key) throws Throwable {
		PicVOExample example = new PicVOExample();
		example.createCriteria().andDescriptionLike("%" + key + "%") ;
		example.or().andNameLike("%" + key + "%") ;
		example.setOrderByClause("time desc");
		return mapper.selectPicDetailByExample(example);
	}

	
}
