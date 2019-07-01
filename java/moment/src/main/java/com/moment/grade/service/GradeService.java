package com.moment.grade.service;

import java.util.List;

import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.grade.domain.GradeVO;

public interface GradeService {
	public int addGrade(GradeVO grade) throws Throwable;
	
	public int deleteGrade(Integer id) throws Throwable;
	
	public GradeVO getGradeById(Integer id) throws Throwable;
	
	public int updateGrade(GradeVO grade) throws Throwable;
	
	public GradeVO getGradeByGrade(String grade) throws Throwable;
	/**
	 * 使用datatables的分页查询
	 * @param request
	 * @return
	 */
	public DataTablesResponse<GradeVO> list(DataTablesRequest request) throws Throwable;
}
