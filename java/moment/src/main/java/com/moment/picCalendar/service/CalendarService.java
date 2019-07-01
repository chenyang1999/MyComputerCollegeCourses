package com.moment.picCalendar.service;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.pic.domain.PicVO;
import com.moment.picCalendar.domain.PiccalendarVO;
import com.qiniu.http.Response;

public interface CalendarService {
	public int addPiccalendarVO(MultipartFile file,PiccalendarVO calendar) throws Throwable ;
	//上传摄影日志
	public  Response doUpload(byte[] b,PiccalendarVO calendar) throws Throwable ;
	
	public int deletePiccalendarVO(Integer id) throws Throwable;
	
	public PiccalendarVO getPiccalendarVOById(Integer id) throws Throwable;
	
	
	public PiccalendarVO getPiccalendarVOByDate(Integer id) throws Throwable;
	
	public int updatePiccalendarVO(MultipartFile file,PiccalendarVO calendar) throws Throwable;
	
	/**
	 * 使用datatables的分页查询
	 * @param request
	 * @return
	 */
	public DataTablesResponse<PiccalendarVO> list(DataTablesRequest request) throws Throwable;
}
