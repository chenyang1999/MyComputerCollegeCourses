package com.moment.grade.controller;

import java.util.List;
import java.util.Set;

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
import com.moment.grade.domain.GradeVO;
import com.moment.grade.service.GradeService;


@Controller
@RequestMapping("/grade")
public class GradeController {
	@Autowired
	private GradeService service;
	
	/**
	 * 列表显示
	 * */
	@RequestMapping("/list")
	public String list(HttpSession session,HttpServletRequest request){
		return "permission/list";
	}
	/**
	 * 列表查询结果显示
	 * */
	@RequestMapping("/rest/doSearch")
	public @ResponseBody DataTablesResponse<GradeVO> pageSearch(
			@RequestBody DataTablesRequest request) throws Throwable{
		return service.list(request);
	}
	/**
	 * 编辑权限管理，可添加可修改
	 * */
	@RequestMapping("/edit")
	public ModelAndView add(Integer id) throws Throwable{
		ModelAndView model=new ModelAndView("permission/edit");
		if(id!=null&&id!=0){//修改
			model.addObject("permission",service.getGradeById(id));
		}
		return model;
	}
	/**
	 * 保存修改
	 * */
	@RequestMapping("/doEdit")
	public ModelAndView doAdd(@Valid GradeVO grade,BindingResult bindingResult) throws Throwable{
		ModelAndView model=new ModelAndView("permission/list");
		if(grade.getId()!=null&&service.getGradeById(grade.getId())!=null){//修改
			service.updateGrade(grade);
		}else{//新增
			if(bindingResult.hasErrors()){
				List<ObjectError> allErrors=bindingResult.getAllErrors();
				for(ObjectError objErr:allErrors){
					System.out.println(objErr.getDefaultMessage());
				}
				System.out.println(bindingResult.getFieldErrorCount());
			}else{
				try {
					service.addGrade(grade);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return model;
	}
	/**
	 * 删除权限
	 * */
	@RequestMapping("/doDel")
	public @ResponseBody JsonResult doDel(Integer id) throws Throwable{
		JsonResult rs=new JsonResult();
		service.deleteGrade(id);
		rs.setStatus(1);
		rs.setMsg("删除成功！");
		return rs;
	}
}
