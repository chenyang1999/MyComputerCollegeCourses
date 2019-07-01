package com.moment.admin.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moment.admin.domain.AdminVO;
import com.moment.admin.service.adminService;

/** * @author 01fang */
@Controller
@RequestMapping("/admin")
public class adminLogin {
@Autowired	
private adminService service;
	@RequestMapping("/login")
   public String toLogin(){
	   return "admin/login";
   }
	@RequestMapping("/index")
	public String index(){
		return "admin/index";
	}
	@RequestMapping("/home")
	public String home(){
		return "admin/home";
	}

	@RequestMapping("/dologin")
 	public String login(String name,String password,ModelMap map,HttpSession session) throws Exception{
		AdminVO adminvo=service.checkLogin(name, password);
 		if(adminvo!=null){
 			session.setAttribute("admin",adminvo);
 		   return "redirect:index.action";
 		}
 		return "redirect:login.action";
 	}
}
