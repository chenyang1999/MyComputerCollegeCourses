package com.moment.activity.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moment.activity.domain.ActivityVO;
import com.moment.activity.service.activityService;

/** * @author 01fang */
@Controller
@RequestMapping("/activity")
public class activityController {
@Autowired	
private activityService service;
@RequestMapping("/activity")
public String get(){
	return "redirect:user/activity";
}
@RequestMapping("/get")
public String get(ModelMap map){
	List<ActivityVO> list=service.get();
	map.addAttribute("list", list);
	return "user/activity";
}
}
