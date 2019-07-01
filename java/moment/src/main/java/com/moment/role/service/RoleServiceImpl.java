package com.moment.role.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.common.util.SearchConditionUtils;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.picCalendar.domain.PiccalendarVO;
import com.moment.picCalendar.domain.PiccalendarVOExample;
import com.moment.role.dao.RoleVOMapper;
import com.moment.role.domain.RoleVO;
import com.moment.role.domain.RoleVOExample;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleVOMapper mapper;
	@Override
	public int addRoleVO(RoleVO role) throws Throwable {
		return mapper.insertSelective(role);
	}

	@Override
	public int deleteRoleVO(Integer id) throws Throwable {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public RoleVO getRoleVOById(Integer id) throws Throwable {
		return mapper.selectByPrimaryKey(id);
	}


	@Override
	public int updateRoleVO(RoleVO role) throws Throwable {
		return mapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public DataTablesResponse<RoleVO> list(DataTablesRequest request) throws Throwable {
		RoleVOExample example = new RoleVOExample();
		DataTablesResponse<RoleVO> response = new DataTablesResponse<RoleVO>();
		SearchConditionUtils.wrapperAndCondition(example, request);// 封装查询条件
		response.setDraw(request.getDraw());
		response.setRecordsTotal(mapper.countByExample(example));
		response.setData(mapper.selectByExample(example));
		return response;
	}


}
