package com.moment.comment.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moment.common.domain.CurrentUser;
import com.moment.common.domain.JsonResult;
import com.moment.user.service.UserService;
import com.moment.user.service.UserServiceImp;
import com.moment.comment.domain.CommentVO;
import com.moment.comment.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private UserService userservice;
	@Autowired
	private CommentService service;
	/**
	 * 查看评论
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/checkcomment")
	public @ResponseBody List<CommentVO> edit(Integer id){
		List<CommentVO> comment = new ArrayList<CommentVO>(); 
		if(id!=null){
			comment= service.get(id);
		}
		return comment;//跳转到主页面
	}
	
	@RequestMapping("/doEdit")
	public @ResponseBody int  doEdit(Integer picid,String content,Integer userid) throws Throwable{
		CurrentUser user=CurrentUser.getInstance();
		CommentVO commentVo = new CommentVO();
		String userimg =userservice.getUserById(userid).getImg();
		System.out.println("图片id"+picid);
		 commentVo.setUserid(Integer.valueOf(user.getUserId()));
		 commentVo.setUsername(user.getUserName());
		 commentVo.setUserimg(userimg);
		 commentVo.setPicid(picid);
		 commentVo.setContent(content);
		 service.add(commentVo);
		return 1;
	}
	
	@RequestMapping("/dorecomment")
	public @ResponseBody int  dorecomment(Integer picid,String content,Integer userid,Integer commentid,String commentname) throws Throwable{
		CurrentUser user=CurrentUser.getInstance();
		CommentVO commentVo = new CommentVO();
		
		String userimg =userservice.getUserById(userid).getImg();
		 commentVo.setUserid(Integer.valueOf(user.getUserId()));
		 commentVo.setUsername(user.getUserName());
		 commentVo.setUserimg(userimg);
		 commentVo.setPicid(picid);
		 commentVo.setContent(content);
		 commentVo.setCommentid(commentid);
		 commentVo.setCommentname(commentname);
		 service.add(commentVo);
		return 1;
	}
	
	@RequestMapping("/doDelete")
	public @ResponseBody JsonResult doDelete(Integer id,Integer picid){
		JsonResult rs=new JsonResult();
		if(id!=null){
		service.delete(id,picid);
		rs.setStatus(1);
		rs.setMsg("删除成功！");
		System.out.println("删除成功！");
		}
		return rs;
	}
	
	@RequestMapping("/list")
	public String list(){
		return "demo/list";//跳转到分页查询页面
	}
}
