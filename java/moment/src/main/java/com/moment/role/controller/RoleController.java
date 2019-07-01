package com.moment.role.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.moment.common.domain.JsonResult;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.role.domain.RoleVO;
import com.moment.role.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService service;
	/**
	 * 跳转到列表页面
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpSession session,HttpServletRequest request){
		System.out.println("uri:"+request.getRequestURI());
		return "application/list";
	}
	/**
	 * 分页查接口
	 * @param pageVo
	 * @param userVo
	 * @return
	 * @throws Throwable 
	 */
	@RequestMapping("/rest/doSearch")
	public @ResponseBody DataTablesResponse<RoleVO> pageSearch(
			@RequestBody DataTablesRequest request) throws Throwable{
		return service.list(request);
	}
	/**
	 * 跳转到编辑页面,保留功能，不做接口
	 * @return
	 * @throws Throwable 
	 */
	@RequestMapping("/edit")
	public ModelAndView add(Integer id) throws Throwable{
		ModelAndView model=new ModelAndView("application/edit");
		if(id!=0&&id!=null){//修改
			model.addObject("role",service.getRoleVOById(id));
		}
		return model;
	}
	/**
	 * 保存编辑,保留功能，不做接口
	 * @param user
	 * @return
	 * @throws Throwable 
	 */
	@RequestMapping("/doEdit")
	public ModelAndView doAdd(@Valid RoleVO role,BindingResult bindingResult) throws Throwable{
		ModelAndView model=new ModelAndView("application/list");
		if(role.getId()!=null&&role.getId()!=0&&service.getRoleVOById(role.getId())!=null){//修改
			service.updateRoleVO(role);
		}else{//新增
			if(bindingResult.hasErrors()){
				List<ObjectError> allErrors=bindingResult.getAllErrors();
				for(ObjectError objErr:allErrors){
					System.out.println(objErr.getDefaultMessage());
				}
				System.out.println(bindingResult.getFieldErrorCount());
			}else{
				try {
					service.addRoleVO(role);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return model;
	}
	/**
	 * 保存编辑,保留功能，不做接口
	 * @param user
	 * @return
	 * @throws Throwable 
	 */
	@RequestMapping("/doDel")
	public @ResponseBody JsonResult doDel(int id) throws Throwable{
		JsonResult rs=new JsonResult();
		
		service.deleteRoleVO(id);
		rs.setStatus(1);
		rs.setMsg("删除成功！");
		return rs;
	}
}
