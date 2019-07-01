package com.moment.login.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
//这个拦截器只拦截动作发生之前
public class LoginHandlerInterceptor implements HandlerInterceptor{
	//进行日志管理记录
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//这里是进行了spring的属性注入
	private List<String> allowUris;
	public void setAllowUris(List<String> allowUris) {
		this.allowUris = allowUris;
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	//这里涉及到两个登陆角色，管理员和用户，因此这里的拦截器也要相应去识别管理员和用户的登陆
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.debug("LoginStateHandlerInterceptor preHandle");
		HttpServletRequest httpReq=(HttpServletRequest)request;
		HttpServletResponse httpRes=(HttpServletResponse)response;
		if(httpReq.getSession().getAttribute("user")!=null){//已经登录
			logger.debug("用户已经登录");
			return true;
		}else if(httpReq.getSession().getAttribute("admin")!=null){
			logger.debug("管理员已经登录");
			return true;
		}else{//没有登录
			String uri=httpReq.getRequestURI();
			if(isAllowURI(uri)){//本身登录模块不需要 拦截
				return true;
			}else{
				//返回登录页
				httpRes.sendRedirect(httpReq.getContextPath()+"/user/login.action");
				return false;
			}
			
		}
		
		
	}
	/**
	 * 判断是否不需要拦截的URI
	 * @param uri
	 * @return
	 */
	private boolean isAllowURI(String uri){
		if(allowUris!=null){
			for(String str : allowUris){
				if(uri.indexOf(str)>-1){
					return true;
				}
			}
		}
		return false;
	}
	
}
