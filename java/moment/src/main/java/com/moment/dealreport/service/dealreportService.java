package com.moment.dealreport.service;

import java.util.List;

import com.moment.dealreport.domain.DealreportVO;

/** * @author 01fang */
public interface dealreportService {
public int add(DealreportVO vo);
public List<DealreportVO> get(String deal);
}
