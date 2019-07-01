package com.moment.concern.service;

import java.util.List;

import com.moment.concern.domain.ConcernVO;
import com.moment.dealreport.domain.DealreportVO;

public interface ConcernService {
	public int add(ConcernVO concern) throws Throwable;
	public List<Integer> getConcern(int id) throws Throwable;
	public List<ConcernVO> getFans(int id) throws Throwable;
	public int delete(int watchId,int beWatchId) throws Throwable;
}
