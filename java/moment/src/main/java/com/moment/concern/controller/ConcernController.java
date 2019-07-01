package com.moment.concern.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moment.common.domain.CurrentUser;
import com.moment.common.domain.JsonResult;
import com.moment.concern.domain.ConcernVO;
import com.moment.concern.service.ConcernService;
import com.moment.user.domain.UserVO;
import com.moment.user.service.UserService;

@Controller
@RequestMapping("/concern")
public class ConcernController {
	@Autowired
	private ConcernService service;
	@Autowired
	private UserService uservice;
	/**
	 * 
	 * @param concern
	 * @return JsonResult
	 * @throws Throwable
	 */
	@RequestMapping("/doAdd")
	public @ResponseBody JsonResult doAdd(int watchId,int beWatchId,JsonResult rs,HttpSession session) throws Throwable{
		ConcernVO concern=new ConcernVO();
		concern.setBewatchuserid(beWatchId);
		concern.setWatchuserid(watchId);
		int a=service.add(concern);
		if(a>0){
			rs.setStatus(1);
			rs.setMsg("关注成功！");
			UserVO user=uservice.getUserById(watchId);
			session.setAttribute("user", user);
		}else{
			rs.setStatus(0);
			rs.setMsg("关注成功！");
		}		
		return rs;
	}
	@RequestMapping("/doDel")
	public @ResponseBody JsonResult doDel(int watchId,int beWatchId,JsonResult rs,HttpSession session) throws Throwable{
		int a=service.delete(watchId, beWatchId);
		if(a>0){
			rs.setStatus(1);
			rs.setMsg("取消关注成功！");
			UserVO user=uservice.getUserById(watchId);
			session.setAttribute("user", user);
		}else{
			rs.setStatus(0);
			rs.setMsg("取消关注成功！");
		}		
		return rs;
	}
	/**
	 * 根据用户id去访问用户关注的用户列表
	 * @param watchId
	 * @param beWatchId
	 * @param rs
	 * @param session
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/getListBywatchuserId")
	public @ResponseBody List<Integer> getListBywatchuserId(int watchuserId) throws Throwable{
		 return service.getConcern(watchuserId);
	}
}
