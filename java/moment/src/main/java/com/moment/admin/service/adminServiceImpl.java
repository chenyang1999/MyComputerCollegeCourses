package com.moment.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moment.admin.dao.AdminVOMapper;
import com.moment.admin.domain.AdminVO;
import com.moment.admin.domain.AdminVOExample;


/** * @author 01fang */
@Service
public class adminServiceImpl implements adminService {
	@Autowired
	private AdminVOMapper map;
	@Override
	public AdminVO checkLogin(String name, String password) throws Exception {
		AdminVOExample example=new AdminVOExample();
		List<AdminVO> list=map.selectByExample(example);
		for(AdminVO vo:list){
			if(name.equals(vo.getName())&&password.equals(vo.getPassword())){
				return vo;
			}
		}
		return null;
	
	}

}
