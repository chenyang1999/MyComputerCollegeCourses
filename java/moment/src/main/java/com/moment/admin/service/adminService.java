package com.moment.admin.service;

import com.moment.admin.domain.AdminVO;

/** * @author 01fang */
public interface adminService {
	public AdminVO checkLogin(String name,String password)throws Exception;
}
