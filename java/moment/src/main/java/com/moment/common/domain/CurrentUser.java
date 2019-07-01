package com.moment.common.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.moment.common.util.SpringUtil;
import com.moment.grade.domain.GradeVO;
import com.moment.grade.service.GradeService;
import com.moment.user.domain.UserVO;


@Component
@Scope("session")
//作用范围在session中
public class CurrentUser {
	public static CurrentUser getInstance(){
		return SpringUtil.getBean(CurrentUser.class);
	}
	//通过获取session中的用户，来进行操作
	@Autowired
	private HttpSession session;
	@Autowired
	private GradeService service;
	
	/**
	 * 获取当前用户对象，登录成功或设置到session
	 * @return
	 */
	public UserVO getCurrentUser(){
		UserVO user=(UserVO)session.getAttribute("user");
		return user;
	}
	/**
	 * 获取当前登录用户ID
	 * @return
	 */
	public Integer getUserId(){
		UserVO user=getCurrentUser();
		if(user!=null){
			return user.getId();
		}
		return null;
	}
	
	/**
	 * 获取当前登录用户name
	 * @return
	 */
	public String getUserName(){
		UserVO user=getCurrentUser();
		if(user!=null){
			return user.getName();
		}
		return null;
	}
	/**
	 * 获取当前登录用户等级
	 * @return
	 */
	public GradeVO getGrade(){
		UserVO user=getCurrentUser();
		if(user!=null){
			try {
				return service.getGradeById(user.getGradeid());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获取当前登录账号
	 * @return
	 */
	public String getAccount(){
		UserVO user=getCurrentUser();
		if(user!=null){
			return user.getAccount();
		}
		return null;
	}
	
}
