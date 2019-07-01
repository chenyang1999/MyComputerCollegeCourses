package com.moment.activity.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.activity.dao.ActivityVOMapper;
import com.moment.activity.domain.ActivityVO;
import com.moment.activity.domain.ActivityVOExample;

/** * @author 01fang */
@Service
public class activityServiceImpl implements activityService {
	@Autowired
	private ActivityVOMapper mapper;
	@Override
	public List<ActivityVO> get() {
		ActivityVOExample example=new ActivityVOExample();
		example.createCriteria();
		return mapper.selectByExample(example);
	}

}
