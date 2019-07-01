package com.moment.dealreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.dealreport.dao.DealreportVOMapper;
import com.moment.dealreport.domain.DealreportVO;
import com.moment.dealreport.domain.DealreportVOExample;

/** * @author 01fang */
@Service
public class dealreportServiceImpl implements dealreportService {
	@Autowired
	private DealreportVOMapper map;
	@Override
	public int add(DealreportVO vo) {
		
		return map.insertSelective(vo);
	}
	@Override
	public List<DealreportVO> get(String deal) {
		DealreportVOExample example=new DealreportVOExample();
		example.createCriteria().andDealdetailLike("%");
		return map.selectByExample(example);
		
	}

}
