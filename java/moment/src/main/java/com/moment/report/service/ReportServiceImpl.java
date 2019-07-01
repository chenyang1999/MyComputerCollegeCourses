package com.moment.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.common.util.SearchConditionUtils;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.report.dao.ReportVOMapper;
import com.moment.report.domain.ReportVO;
import com.moment.report.domain.ReportVOExample;
@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportVOMapper map;
	@Override
	public int add(ReportVO report) {
		
		return map.insertSelective(report);
	}

	@Override
	public ReportVO get(Integer id) {
		
		return map.selectByPrimaryKey(id);
	}

	@Override
	public int update(ReportVO report) {
		
		return map.updateByPrimaryKeySelective(report);
	}

	@Override
	public DataTablesResponse<ReportVO> listByPage(DataTablesRequest request)
			throws Throwable {
		ReportVOExample example=new ReportVOExample();
		DataTablesResponse<ReportVO> response = new DataTablesResponse<ReportVO>();
		SearchConditionUtils.wrapperAndCondition(example, request);
		response.setDraw(request.getDraw());
		response.setRecordsTotal(map.countByExample(example));
		response.setData(map.selectByExample(example));
		return response;
		
	}

	@Override
	public int delete(Integer id) {
		
		return map.deleteByPrimaryKey(id);
	}

	@Override
	public List<ReportVO> list(String descp) {
		ReportVOExample example=new ReportVOExample();
		example.createCriteria().andDescriptionLike("%");
		return map.selectByExample(example);
	}

}
