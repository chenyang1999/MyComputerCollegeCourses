package com.moment.user.service;

import java.util.List;

import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.grade.domain.GradeVO;
import com.moment.user.domain.UserVO;
import com.qiniu.http.Response;

public interface UserService {
	public int addUser(UserVO user) throws Throwable;
	
	public int deleteUser(Integer id) throws Throwable;
	
	public UserVO getUserById(Integer id) throws Throwable;
	
	public int updateUser(UserVO user) throws Throwable;
	public UserVO getUserByAccount(String account) throws Throwable;
	public List<UserVO> getUserByName(String name) throws Throwable;
	//登陆检测
	public UserVO checkLogin(String account,String password) throws Throwable;
	//验证密码
	public boolean validate(Integer id,String password) throws Throwable;
	//上传用户头像
	public String doUpload(byte[] b) throws Throwable ;
	/**
	 * 使用datatables的分页查询
	 * @param request
	 * @return
	 */
	public DataTablesResponse<UserVO> list(DataTablesRequest request) throws Throwable;
}
