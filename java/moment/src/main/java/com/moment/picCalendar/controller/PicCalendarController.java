package com.moment.picCalendar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.moment.common.domain.CurrentUser;
import com.moment.common.domain.JsonResult;
import com.moment.picCalendar.domain.PiccalendarVO;
import com.moment.picCalendar.service.CalendarService;


@Controller
@RequestMapping("/calendar")
public class PicCalendarController {
	@Autowired
	private CalendarService service;
	
	/**
	 * 前台操作上传摄影日志，返回结果到前端（判断今日是否已经上传，若未上传，则添加数据；若已经上传，则修改数据）
	 * */
	@RequestMapping("/upload")
	public @ResponseBody JsonResult upload(MultipartFile file,@Valid PiccalendarVO calendar,BindingResult bindingResult,JsonResult jsonResult) throws Exception{
		
		int flag=0;
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			for(ObjectError objErr:allErrors){
				System.out.println(objErr);
			}
			System.out.println(bindingResult.getFieldErrorCount());
			jsonResult.setMsg("添加日志失败");
			jsonResult.setStatus(0);
		}else{
			try {
				//获取当前用户的id
				CurrentUser cuser = CurrentUser.getInstance();
				calendar.setUserid(cuser.getUserId());
				//今日未上传日志，则上传日志
				System.out.println(service.getPiccalendarVOByDate(cuser.getUserId()));
				if(service.getPiccalendarVOByDate(cuser.getUserId())==null){
					flag=service.addPiccalendarVO(file, calendar);
					if(flag!=0){
						jsonResult.setMsg("成功创建今日日志");
						 jsonResult.setStatus(1);
					}else{
						jsonResult.setMsg("创建日志失败");
						 jsonResult.setStatus(0);
					}
				}else{//近日已上传日志，则修改日志
					//获取这条记录的id
					if(calendar.getId()==null){
						calendar.setId(service.getPiccalendarVOByDate(cuser.getUserId()).getId());
					}
					flag=service.updatePiccalendarVO(file, calendar);
					if(flag!=0){
						jsonResult.setMsg("已修改今日日志");
						 jsonResult.setStatus(1);
					}else{
						jsonResult.setMsg("修改日志失败");
						 jsonResult.setStatus(0);
					}
				}
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jsonResult.setMsg("上传日志失败");
				 jsonResult.setStatus(0);
			}
		}
		return jsonResult;
	}
	//获取用户的今日日志
	@RequestMapping("/getCalendar")
	public @ResponseBody PiccalendarVO getCalendar(){
		CurrentUser cuser=CurrentUser.getInstance();
		PiccalendarVO calendar=null;
		try {
			calendar=service.getPiccalendarVOByDate(cuser.getUserId());
			System.out.println(calendar);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return calendar;
	}
	
	/**
	 * 删除日志
	 * */
	@RequestMapping("/doDel")
	public @ResponseBody JsonResult doDel(Integer id) throws Throwable{
		JsonResult rs=new JsonResult();
		service.deletePiccalendarVO(id);
		rs.setStatus(1);
		rs.setMsg("删除成功！");
		return rs;
	}
}
