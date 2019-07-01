package com.moment.role.service;

import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.role.domain.RoleVO;

public interface RoleService {
public int addRoleVO(RoleVO role) throws Throwable;
	
	public int deleteRoleVO(Integer id) throws Throwable;
	
	public RoleVO getRoleVOById(Integer id) throws Throwable;
	
	
	public int updateRoleVO(RoleVO role) throws Throwable;
	
	/**
	 * 使用datatables的分页查询
	 * @param request
	 * @return
	 */
	public DataTablesResponse<RoleVO> list(DataTablesRequest request) throws Throwable;
}
