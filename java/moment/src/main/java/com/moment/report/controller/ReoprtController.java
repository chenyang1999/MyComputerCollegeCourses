package com.moment.report.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;






import com.moment.common.domain.CurrentUser;
import com.moment.common.domain.JsonResult;
import com.moment.datatables.domain.DataTablesRequest;
import com.moment.datatables.domain.DataTablesResponse;
import com.moment.dealreport.controller.dealreportController;
import com.moment.report.domain.ReportVO;
import com.moment.report.service.ReportService;
import com.moment.user.service.UserService;



/** * @author 01fang */
@Controller
@RequestMapping("/report")
public class ReoprtController {
	@Autowired
	private ReportService service;
	
	/*举报按钮/链接
	 * 传入picid值到编辑页面*/
	@RequestMapping("/reportlist")
	public String reportlist(){
		return "toReport";/*跳转到举报页面*/
	}
	
	
	
	/*通过异步更新操作执行举报操作
	 *在表report中插入举报的具体信息*/
	@RequestMapping("/doReport")
	public @ResponseBody JsonResult add(ReportVO reportVO) throws Throwable{
		reportVO.setUserid(CurrentUser.getInstance().getUserId());
		if(reportVO.getId()!=null){
			service.update(reportVO);
		}else {
			service.add(reportVO);	
		}
		JsonResult jsonresult=new JsonResult();
		jsonresult.setMsg("举报成功");
		jsonresult.setStatus(1);
		return jsonresult;
	}
	
	
	/*在后台系统显示举报的具体信息*/
	@RequestMapping("/deal")
	public String deal(ModelMap map){
		List<ReportVO> list=service.list("%");
		map.addAttribute("list", list);
		return "admin/dealreport";/*跳转到后台系统页面*/
		
	}
	/*删除已经处理的举报*/
	@RequestMapping("doDelete")
	public String doDelete(Integer id,ModelMap map){
		if(id!=null){
			service.delete(id);
		}
		List<ReportVO> list=service.list("%");
		map.addAttribute("list", list);
	    return "admin/dealreport";
	}
	@RequestMapping("/doSearch")
	public @ResponseBody DataTablesResponse<ReportVO> page(
			@RequestBody DataTablesRequest request) throws Throwable{
		return service.listByPage(request);
		
		
	}
}
