package com.moment.grade.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.common.util.SearchConditionUtils;
import com.moment.common.util.MD5Util;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.grade.dao.GradeVOMapper;
import com.moment.grade.domain.GradeVO;
import com.moment.grade.domain.GradeVOExample;

@Service
public class GradeServiceImpl implements GradeService {
	@Autowired
	private GradeVOMapper mapper;
	
	@Override
	public int addGrade(GradeVO grade) throws Throwable {
		return mapper.insertSelective(grade);
	}

	@Override
	public int deleteGrade(Integer id) throws Throwable {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public GradeVO getGradeById(Integer id) throws Throwable {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateGrade(GradeVO grade) throws Throwable {
		return mapper.updateByPrimaryKeySelective(grade);
	}

	@Override
	public GradeVO getGradeByGrade(String grade) throws Throwable {
		GradeVOExample example = new GradeVOExample();
		example.createCriteria().andGradeEqualTo(grade);
		List<GradeVO> list = mapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public DataTablesResponse<GradeVO> list( DataTablesRequest request) throws Throwable {
		GradeVOExample example = new GradeVOExample();
		DataTablesResponse<GradeVO> response = new DataTablesResponse<GradeVO>();
		SearchConditionUtils.wrapperAndCondition(example, request);// 封装查询条件
		response.setDraw(request.getDraw());
		response.setRecordsTotal(mapper.countByExample(example));
		response.setData(mapper.selectByExample(example));
		return response;
	}
	

}
