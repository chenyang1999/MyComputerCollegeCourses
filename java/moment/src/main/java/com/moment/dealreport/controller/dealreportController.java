package com.moment.dealreport.controller;

import java.util.List;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moment.dealreport.domain.DealreportVO;
import com.moment.dealreport.service.dealreportService;
import com.moment.pic.service.PicService;
import com.moment.report.domain.ReportVO;
import com.moment.report.service.ReportService;

/** * @author 01fang */
@Controller
@RequestMapping("/dealreportController")
public class dealreportController {
	@Autowired
	private dealreportService dealService;
	@Autowired
	private ReportService reportService;
	
	private PicService picService;
	
	@RequestMapping("/edit")
	public String edit(Integer id,ModelMap map){
		map.addAttribute("reportid", reportService.get(id).getUserid());
		map.addAttribute("picid", reportService.get(id).getPicid());
		/*map.addAttribute("adminid",);获取登录者（处理者）的id*/
		return "admin/deal";/*跳转到处理举报页面*/
	}
	/*处理举报
	 * 经过调查，对举报图片做出处理
	 * 若核实无误则删除该图片，并将结果发送给举报者和图片发布者
	 * 若核实有误则不删除，将结果发送给举报者*/
	@RequestMapping("/addDetail")
	public String add(DealreportVO dealVo){
		dealService.add(dealVo);
		return "admin/test";
	}
	@RequestMapping("list")
	public String list(ModelMap map){
		List<DealreportVO> list=dealService.get("%");
		map.addAttribute("list", list);
		return "admin/listDeal";
	}
	@RequestMapping("deletePic")
	public void delete(Integer id){
		try {
			picService.deletePic(id);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
